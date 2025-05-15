package com.dartlinwave.platform.lawconnectplatform.profiles.application.internal.queryservices;

import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.aggregates.Lawyer;
import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.queries.GetLawyerByUserIdQuery;
import com.dartlinwave.platform.lawconnectplatform.profiles.domain.services.LawyerQueryService;
import com.dartlinwave.platform.lawconnectplatform.profiles.infrastructure.persistence.jpa.repositories.LawyerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service implementation for handling lawyer query operations.
 * <p>
 * Provides logic to retrieve lawyer profiles based on queries.
 * </p>
 */
@Service
public class LawyerQueryServiceImpl implements LawyerQueryService {

    /**
     * Repository for lawyer persistence operations.
     */
    private final LawyerRepository lawyerRepository;

    /**
     * Constructs a new {@code LawyerQueryServiceImpl} with the given {@link LawyerRepository}.
     *
     * @param lawyerRepository the repository for lawyer persistence
     */
    public LawyerQueryServiceImpl(LawyerRepository lawyerRepository) {
        this.lawyerRepository = lawyerRepository;
    }

    /**
     * Retrieves a lawyer by the associated user ID.
     *
     * @param query the query object containing the user ID
     * @return an {@link Optional} containing the {@link Lawyer} if found, or empty if not found
     */
    @Override
    public Optional<Lawyer> handle(GetLawyerByUserIdQuery query) {
        return lawyerRepository.findLawyerByUserId(query.userId());
    }
}