package com.dartlinwave.platform.lawconnectplatform.profiles.application.internal.commandservices;

import org.springframework.stereotype.Service;
import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.entities.Specialty;
import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.valueobjects.LawyerSpecialties;
import com.dartlinwave.platform.lawconnectplatform.profiles.domain.services.LawyerSpecialtyCommandService;
import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.commands.SeedLawyerSpecialtiesCommand;
import com.dartlinwave.platform.lawconnectplatform.profiles.infrastructure.persistence.jpa.repositories.LawyerSpecialtiesRepository;

import java.util.Arrays;

/**
 * Service implementation for handling lawyer specialty-related commands.
 * <p>
 * Provides logic to seed lawyer specialties into the persistence layer if they do not already exist.
 * </p>
 */
@Service
public class LawyerSpecialtyCommandServiceImpl implements LawyerSpecialtyCommandService {

    private final LawyerSpecialtiesRepository lawyerSpecialtiesRepository;

    /**
     * Constructs a new {@code LawyerSpecialtyCommandServiceImpl} with the given {@link LawyerSpecialtiesRepository}.
     *
     * @param lawyerSpecialtiesRepository the repository used to access and persist lawyer specialties
     */
    public LawyerSpecialtyCommandServiceImpl(LawyerSpecialtiesRepository lawyerSpecialtiesRepository) {
        this.lawyerSpecialtiesRepository = lawyerSpecialtiesRepository;
    }

    /**
     * Handles the {@link SeedLawyerSpecialtiesCommand} by seeding all specialties defined in {@link LawyerSpecialties}
     * into the repository if they do not already exist.
     *
     * @param command the command containing the seeding request
     */
    @Override
    public void handle(SeedLawyerSpecialtiesCommand command) {
        Arrays.stream(LawyerSpecialties.values()).forEach(specialty -> {
            if (!lawyerSpecialtiesRepository.existsByName(specialty)) {
                lawyerSpecialtiesRepository.save(new Specialty(specialty));
            }
        });
    }
}