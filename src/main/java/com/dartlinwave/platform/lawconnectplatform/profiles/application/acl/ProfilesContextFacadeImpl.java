package com.dartlinwave.platform.lawconnectplatform.profiles.application.acl;

import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.commands.CreateClientCommand;
import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.commands.CreateLawyerCommand;
import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.queries.GetClientByUserIdQuery;
import com.dartlinwave.platform.lawconnectplatform.profiles.domain.model.queries.GetLawyerByUserIdQuery;
import com.dartlinwave.platform.lawconnectplatform.profiles.domain.services.ClientCommandService;
import com.dartlinwave.platform.lawconnectplatform.profiles.domain.services.ClientQueryService;
import com.dartlinwave.platform.lawconnectplatform.profiles.domain.services.LawyerCommandService;
import com.dartlinwave.platform.lawconnectplatform.profiles.domain.services.LawyerQueryService;
import com.dartlinwave.platform.lawconnectplatform.profiles.interfaces.acl.ProfilesContextFacade;
import com.dartlinwave.platform.lawconnectplatform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Implementation of the {@link ProfilesContextFacade} interface.
 * <p>
 * Provides methods to manage lawyer and client profiles by delegating to the appropriate domain services.
 * </p>
 */
@Service
public class ProfilesContextFacadeImpl implements ProfilesContextFacade {

    private final LawyerQueryService lawyerQueryService;
    private final LawyerCommandService lawyerCommandService;

    private final ClientQueryService clientQueryService;
    private final ClientCommandService clientCommandService;

    /**
     * Constructs a new {@code ProfilesContextFacadeImpl} with the required services.
     *
     * @param clientCommandService the service for client profile commands
     * @param clientQueryService   the service for client profile queries
     * @param lawyerCommandService the service for lawyer profile commands
     * @param lawyerQueryService   the service for lawyer profile queries
     */
    public ProfilesContextFacadeImpl(ClientCommandService clientCommandService, ClientQueryService clientQueryService, LawyerCommandService lawyerCommandService, LawyerQueryService lawyerQueryService) {
        this.clientCommandService = clientCommandService;
        this.clientQueryService = clientQueryService;
        this.lawyerCommandService = lawyerCommandService;
        this.lawyerQueryService = lawyerQueryService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long createLawyerProfile(String name, String lastname, String dni,
                                    String phone, String description, Set<String> specialty, Long userId) {

        CreateLawyerCommand command = new CreateLawyerCommand(name, lastname, dni, phone, description, specialty);

        var lawyer = lawyerCommandService.handle(command, userId);

        return lawyer.map(AuditableAbstractAggregateRoot::getId).orElse(0L);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long createClientProfile(String name, String lastname, String dni, String phone, Long userId) {


        CreateClientCommand command = new CreateClientCommand(name, lastname, dni, phone);

        var client = clientCommandService.handle(command, userId);

        return client.map(AuditableAbstractAggregateRoot::getId).orElse(0L);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsLawyerByUserId(Long userId) {

        var query = new GetLawyerByUserIdQuery(userId);

        var lawyer = lawyerQueryService.handle(query);

        return lawyer.isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsClientByUserId(Long userId) {

        var query = new GetClientByUserIdQuery(userId);

        var client = clientQueryService.handle(query);

        return client.isPresent();
    }
}