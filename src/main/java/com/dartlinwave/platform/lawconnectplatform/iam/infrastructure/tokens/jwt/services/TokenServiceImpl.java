package com.dartlinwave.platform.lawconnectplatform.iam.infrastructure.tokens.jwt.services;

import org.slf4j.Logger;
import io.jsonwebtoken.*;
import org.slf4j.LoggerFactory;
import io.jsonwebtoken.security.Keys;
import org.springframework.util.StringUtils;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.time.DateUtils;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Value;
import com.dartlinwave.platform.lawconnectplatform.iam.infrastructure.tokens.jwt.BearerTokenService;

import javax.crypto.SecretKey;

import java.util.Date;
import java.util.function.Function;
import java.nio.charset.StandardCharsets;

/**
 * Service implementation for handling JWT token operations such as generation, validation,
 * extraction of claims, and retrieval from HTTP requests.
 * <p>
 * This service provides methods to generate JWT tokens for authenticated users,
 * validate tokens, extract user information, and retrieve tokens from HTTP headers.
 * </p>
 */
@Service
public class TokenServiceImpl implements BearerTokenService {

    private final Logger LOGGER = LoggerFactory
            .getLogger(TokenServiceImpl.class);

    private static final int TOKEN_BEGIN_INDEX = 7;
    private static final String BEARER_TOKEN_PREFIX = "Bearer ";
    private static final String AUTHORIZATION_PARAMETER_NAME = "Authorization";

    @Value("${authorization.jwt.secret}")
    private String secret;

    @Value("${authorization.jwt.expiration.days}")
    private int expirationDays;

    /**
     * Generates a JWT token for the authenticated user.
     *
     * @param authentication the authentication object containing user details
     * @return a JWT token as a String
     */
    @Override
    public String generateToken(Authentication authentication) {
        return buildTokenWithDefaultParameters(authentication.getName());
    }

    /**
     * Generates a JWT token for the specified username.
     *
     * @param username the username for which the token is to be generated
     * @return a JWT token as a String
     */
    public String generateToken(String username) {
        return buildTokenWithDefaultParameters(username);
    }

    /**
     * Builds a JWT token with the default expiration time for the given username.
     *
     * @param username the username to be included in the token
     * @return a JWT token as a String
     */
    private String buildTokenWithDefaultParameters(String username) {

        var issuedAt = new Date();
        var key = getSigningKey();
        var expiration = DateUtils.addDays(issuedAt, expirationDays);

        return Jwts.builder()
                .subject(username)
                .issuedAt(issuedAt)
                .expiration(expiration)
                .signWith(key)
                .compact();
    }

    /**
     * Extracts the username (subject) from the given JWT token.
     *
     * @param token the JWT token from which to extract the username
     * @return the username as a String
     */
    @Override
    public String getUsernameFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Validates the given JWT token for integrity and expiration.
     *
     * @param token the JWT token to be validated
     * @return {@code true} if the token is valid, {@code false} otherwise
     */
    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token);
            LOGGER.info("Token is valid");
            return true;
        } catch (SignatureException e) {
            LOGGER.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            LOGGER.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            LOGGER.error("Expired JWT token: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            LOGGER.error("Unsupported JWT token: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            LOGGER.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    /**
     * Extracts a specific claim from the given JWT token using the provided resolver function.
     *
     * @param <T>            the type of the claim
     * @param token          the JWT token from which to extract the claim
     * @param claimsResolver a function to extract the claim from the Claims object
     * @return the extracted claim
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extracts all claims from the given JWT token.
     *
     * @param token the JWT token from which to extract the claims
     * @return the Claims object containing all claims
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
    }

    /**
     * Retrieves the signing key from the application properties.
     *
     * @return the SecretKey used for signing the JWT token
     */
    private SecretKey getSigningKey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Checks if the authorization parameter contains a non-empty token.
     *
     * @param authorizationParameter the authorization parameter string
     * @return {@code true} if the token is present, {@code false} otherwise
     */
    private boolean isTokenPresentIn(String authorizationParameter) {
        return StringUtils.hasText(authorizationParameter);
    }

    /**
     * Checks if the authorization parameter contains a Bearer token.
     *
     * @param authorizationParameter the authorization parameter string
     * @return {@code true} if the parameter starts with "Bearer ", {@code false} otherwise
     */
    private boolean isBearerTokenIn(String authorizationParameter) {
        return authorizationParameter.startsWith(BEARER_TOKEN_PREFIX);
    }

    /**
     * Extracts the JWT token from the authorization header parameter.
     *
     * @param authorizationHeaderParameter the authorization header string
     * @return the JWT token as a String
     */
    private String extractTokenFrom(String authorizationHeaderParameter) {
        return authorizationHeaderParameter.substring(TOKEN_BEGIN_INDEX);
    }

    /**
     * Retrieves the authorization parameter from the HTTP request headers.
     *
     * @param request the HttpServletRequest object
     * @return the authorization parameter as a String, or {@code null} if not present
     */
    private String getAuthorizationParameterFrom(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION_PARAMETER_NAME);
    }

    /**
     * Extracts the Bearer token from the HTTP request's Authorization header.
     *
     * @param request the HttpServletRequest object
     * @return the JWT token as a String, or {@code null} if not present or not a Bearer token
     */
    @Override
    public String getBearerTokenFrom(HttpServletRequest request) {
        String parameter = getAuthorizationParameterFrom(request);

        if (isTokenPresentIn(parameter) && isBearerTokenIn(parameter)) {
            return extractTokenFrom(parameter);
        }
        return null;
    }
}