package com.dartlinwave.platform.lawconnectplatform.iam.infrastructure.authorization.sfs.model;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * Utility class for building {@link UsernamePasswordAuthenticationToken} instances
 * with user details and request-specific authentication details.
 */
public class UsernamePasswordAuthenticationTokenBuilder {

    /**
     * Builds a {@link UsernamePasswordAuthenticationToken} using the provided principal and HTTP request.
     * The token will include the user's authorities and authentication details from the request.
     *
     * @param principal the authenticated user details
     * @param request   the HTTP servlet request containing authentication details
     * @return a fully constructed {@link UsernamePasswordAuthenticationToken}
     */
    public static UsernamePasswordAuthenticationToken build(UserDetails principal, HttpServletRequest request) {

        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                principal, null, principal.getAuthorities());

        usernamePasswordAuthenticationToken.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request));

        return usernamePasswordAuthenticationToken;
    }
}