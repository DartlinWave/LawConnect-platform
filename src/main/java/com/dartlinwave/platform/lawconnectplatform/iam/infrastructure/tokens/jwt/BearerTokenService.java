package com.dartlinwave.platform.lawconnectplatform.iam.infrastructure.tokens.jwt;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import com.dartlinwave.platform.lawconnectplatform.iam.application.internal.outboundservices.tokens.TokenService;

/**
 * Service interface for handling Bearer token operations.
 * <p>
 * Extends {@link TokenService} to provide methods for extracting and generating Bearer tokens,
 * typically used for JWT-based authentication.
 * </p>
 */
public interface BearerTokenService extends TokenService {

    /**
     * Extracts the Bearer token from the given HTTP servlet request.
     *
     * @param request the HTTP servlet request containing the Authorization header
     * @return the Bearer token as a String, or {@code null} if not present
     */
    String getBearerTokenFrom(HttpServletRequest request);

    /**
     * Generates a Bearer token for the authenticated user.
     *
     * @param authentication the authentication object containing user details
     * @return a Bearer token as a String
     */
    String generateToken(Authentication authentication);
}