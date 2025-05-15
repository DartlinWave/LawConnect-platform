package com.dartlinwave.platform.lawconnectplatform.profiles.application.internal.commandservices;

import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.valueobjects.LawyerSpecialties;
import org.springframework.stereotype.Service;
import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.aggregates.Lawyer;
import com.dartlinwave.platform.lawconnectplatform.profiles.domain.services.LawyerCommandService;
import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.commands.CreateLawyerCommand;
import com.dartlinwave.platform.lawconnectplatform.profiles.infrastructure.persistence.jpa.repositories.LawyerRepository;
import com.dartlinwave.platform.lawconnectplatform.profiles.infrastructure.persistence.jpa.repositories.LawyerSpecialtiesRepository;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service implementation for handling lawyer-related commands.
 * <p>
 * Provides business logic for creating and managing lawyer profiles.
 * </p>
 */
@Service
public class LawyerCommandServiceImpl implements LawyerCommandService {

    private final LawyerRepository lawyerRepository;
    private final LawyerSpecialtiesRepository lawyerSpecialtiesRepository;

    /**
     * Constructs a new {@code LawyerCommandServiceImpl} with the required dependencies.
     *
     * @param lawyerRepository            the repository for lawyer persistence
     * @param lawyerSpecialtiesRepository the repository for lawyer specialties persistence
     */
    public LawyerCommandServiceImpl(
            LawyerRepository lawyerRepository,
            LawyerSpecialtiesRepository lawyerSpecialtiesRepository
    ) {
        this.lawyerRepository = lawyerRepository;
        this.lawyerSpecialtiesRepository = lawyerSpecialtiesRepository;
    }

    /**
     * Handles the creation of a new lawyer profile.
     * <p>
     * Validates the uniqueness of the lawyer's DNI, retrieves the specified specialties,
     * and persists the new lawyer entity.
     * </p>
     *
     * @param command the command containing lawyer creation details
     * @param userId  the user ID associated with the lawyer
     * @return an {@link Optional} containing the created {@link Lawyer}, or empty if creation fails
     * @throws IllegalArgumentException if the DNI already exists or a specialty is not found
     */
    @Override
    public Optional<Lawyer> handle(CreateLawyerCommand command, Long userId) {

        if (lawyerRepository.existsByDni(command.dni()))
            throw new IllegalArgumentException("DNI already exists");

        var specialties = command.specialties().stream()
                .map(name -> lawyerSpecialtiesRepository.findByName(LawyerSpecialties.valueOf(name))
                        .orElseThrow(() -> new IllegalArgumentException(
                                "Specialty not found: " + name)))
                .collect(Collectors.toSet());

        var lawyer = new Lawyer(command, specialties, userId);

        var savedLawyer = lawyerRepository.save(lawyer);

        return Optional.of(savedLawyer);
    }
}