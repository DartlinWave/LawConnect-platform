package com.dartlinwave.platform.lawconnectplatform.iam.infrastructure.hashing.bcrypt.services;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.dartlinwave.platform.lawconnectplatform.iam.infrastructure.hashing.bcrypt.BCryptHashingService;

/**
 * Service implementation for password hashing using the BCrypt algorithm.
 * <p>
 * Provides methods to encode passwords and verify password matches.
 * </p>
 */
@Service
public class HashingServiceImpl implements BCryptHashingService {
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * Constructs a new {@code HashingServiceImpl} with a default {@link BCryptPasswordEncoder}.
     */
    HashingServiceImpl() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * Hashes a password using the BCrypt algorithm.
     *
     * @param rawPassword the password to hash
     * @return the hashed password as a String
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    /**
     * Checks if a raw password matches a previously hashed password.
     *
     * @param rawPassword     the raw password to verify
     * @param encodedPassword the previously hashed password
     * @return {@code true} if the raw password matches the hashed password, {@code false} otherwise
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}