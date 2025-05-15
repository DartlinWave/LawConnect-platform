package com.dartlinwave.platform.lawconnectplatform.iam.infrastructure.authorization.sfs.pipeline;

import com.dartlinwave.platform.lawconnectplatform.iam.infrastructure.tokens.jwt.BearerTokenService;
import com.dartlinwave.platform.lawconnectplatform.iam.infrastructure.authorization.sfs.model.UsernamePasswordAuthenticationTokenBuilder;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

/**
 * Servlet filter that processes Bearer token authentication for incoming HTTP requests.
 * <p>
 * This filter extracts the Bearer token from the request, validates it, and sets the
 * authentication in the {@link SecurityContextHolder} if the token is valid.
 * </p>
 */
public class BearerAuthorizationRequestFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(BearerAuthorizationRequestFilter.class);

    private final BearerTokenService tokenService;
    private final UserDetailsService userDetailsService;

    /**
     * Constructs a new {@code BearerAuthorizationRequestFilter} with the required dependencies.
     *
     * @param tokenService       the service to handle Bearer token operations
     * @param userDetailsService the service to load user details by username
     */
    public BearerAuthorizationRequestFilter(BearerTokenService tokenService, UserDetailsService userDetailsService) {
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Processes each HTTP request to authenticate users based on Bearer tokens.
     * <p>
     * If a valid Bearer token is found in the request, the corresponding user is loaded and
     * the authentication is set in the security context. Otherwise, the request proceeds without authentication.
     * </p>
     *
     * @param request     the HTTP servlet request
     * @param response    the HTTP servlet response
     * @param filterChain the filter chain
     * @throws ServletException if a servlet error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        try {
            String token = tokenService.getBearerTokenFrom(request);
            LOGGER.info("Token: {}", token);

            if (token != null && tokenService.validateToken(token)) {
                String username = tokenService.getUsernameFromToken(token);

                var userDetails = userDetailsService.loadUserByUsername(username);
                var authentication = UsernamePasswordAuthenticationTokenBuilder.build(userDetails, request);

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                LOGGER.info("Token is not valid");
            }
        } catch (Exception e) {
            LOGGER.error("Cannot set user authentication: {}", e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}