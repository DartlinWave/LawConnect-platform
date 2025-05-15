package com.dartlinwave.platform.lawconnectplatform.iam.application.internal.queryservices;

import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.aggregates.User;
import com.dartlinwave.platform.lawconnectplatform.iam.domain.services.UserQueryService;
import com.dartlinwave.platform.lawconnectplatform.iam.domain.model.queries.GetUserByIdQuery;
import com.dartlinwave.platform.lawconnectplatform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service implementation for handling user query operations.
 * <p>
 * Provides logic to retrieve user information based on queries.
 * </p>
 */
@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    /**
     * Constructs a new {@code UserQueryServiceImpl} with the given {@link UserRepository}.
     *
     * @param userRepository the repository for user persistence
     */
    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param query the query containing the user ID
     * @return an {@link Optional} containing the found {@link User}, or empty if not found
     */
    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.userId());
    }
}