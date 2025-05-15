package com.dartlinwave.platform.lawconnectplatform.iam.infrastructure.hashing.bcrypt;

import org.springframework.security.crypto.password.PasswordEncoder;
import com.dartlinwave.platform.lawconnectplatform.iam.application.internal.outboundservices.hashing.HashingService;

/**
 * Interface for password hashing services using the BCrypt algorithm.
 * <p>
 * Extends both {@link HashingService} and {@link PasswordEncoder} to provide
 * password encoding and verification functionalities.
 * </p>
 */
public interface BCryptHashingService extends HashingService, PasswordEncoder {
}