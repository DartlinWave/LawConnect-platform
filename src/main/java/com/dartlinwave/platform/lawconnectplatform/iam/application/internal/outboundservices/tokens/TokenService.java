package com.dartlinwave.platform.lawconnectplatform.iam.application.internal.outboundservices.tokens;

/**
 * Service interface for handling token operations such as validation,
 * generation, and extraction of user information from tokens.
 */
public interface TokenService {

    /**
     * Validates the given token.
     *
     * @param token the token to validate
     * @return {@code true} if the token is valid, {@code false} otherwise
     */
    boolean validateToken(String token);

    /**
     * Generates a token for the specified username.
     *
     * @param username the username for which to generate the token
     * @return the generated token as a {@link String}
     */
    String generateToken(String username);

    /**
     * Extracts the username from the given token.
     *
     * @param token the token from which to extract the username
     * @return the username as a {@link String}
     */
    String getUsernameFromToken(String token);
}