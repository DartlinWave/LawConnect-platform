package com.dartlinwave.platform.lawconnectplatform.tracking.interfaces;

import com.dartlinwave.platform.lawconnectplatform.tracking.domain.model.queries.GetStatusByCaseIdQuery;
import com.dartlinwave.platform.lawconnectplatform.tracking.domain.services.StatusCommandService;
import com.dartlinwave.platform.lawconnectplatform.tracking.domain.services.StatusQueryService;
import com.dartlinwave.platform.lawconnectplatform.tracking.interfaces.resources.CreateStatusResource;
import com.dartlinwave.platform.lawconnectplatform.tracking.interfaces.resources.StatusResource;
import com.dartlinwave.platform.lawconnectplatform.tracking.interfaces.transform.CreateStatusCommandFromResourceAssembler;
import com.dartlinwave.platform.lawconnectplatform.tracking.interfaces.transform.StatusResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/status" ,produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Status", description = "Status Endpoints")
public class StatusController {
    private final StatusCommandService statusCommandService;
    private final StatusQueryService statusQueryService;
    public StatusController(StatusCommandService statusCommandService, StatusQueryService statusQueryService) {
        this.statusCommandService = statusCommandService;
        this.statusQueryService = statusQueryService;
    }

    @PostMapping
    public ResponseEntity<?> createStatus(@RequestBody CreateStatusResource createStatusResource){
        var command = CreateStatusCommandFromResourceAssembler.toCommandFromResource(createStatusResource);
        statusCommandService.handle(command);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StatusResource>> getAllStatuses(@RequestParam long legalCaseId) {
        var query = new GetStatusByCaseIdQuery(legalCaseId);
        var statuses = statusQueryService.handle(query);
        var resource = statuses.stream()
                .map(StatusResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }
}
