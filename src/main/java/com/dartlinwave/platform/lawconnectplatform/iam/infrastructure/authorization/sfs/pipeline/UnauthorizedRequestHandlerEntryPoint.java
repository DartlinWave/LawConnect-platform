package com.dartlinwave.platform.lawconnectplatform.iam.infrastructure.authorization.sfs.pipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.core.AuthenticationException;

import java.io.IOException;

/**
 * Authentication entry point that handles unauthorized requests.
 * <p>
 * This component is triggered whenever an unauthenticated user attempts to access
 * a secured resource. It logs the unauthorized access attempt and sends a 401 Unauthorized
 * HTTP response to the client.
 * </p>
 */
@Component
public class UnauthorizedRequestHandlerEntryPoint implements AuthenticationEntryPoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(UnauthorizedRequestHandlerEntryPoint.class);

    /**
     * Handles unauthorized access attempts by logging the event and sending a 401 Unauthorized response.
     *
     * @param request   the HTTP servlet request
     * @param response  the HTTP servlet response
     * @param authenticationException the exception that caused the authentication failure
     * @throws IOException      if an input or output error occurs
     * @throws ServletException if a servlet error occurs
     */
    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authenticationException) throws IOException, ServletException {

        LOGGER.error("Unauthorized request: {}", authenticationException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized request detected");
    }
}