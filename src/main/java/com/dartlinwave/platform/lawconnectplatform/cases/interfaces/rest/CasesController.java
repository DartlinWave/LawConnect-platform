package com.dartlinwave.platform.lawconnectplatform.cases.interfaces.rest;

import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.aggregates.Case;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.queries.GetCasesByClientIdQuery;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.services.CaseCommandService;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.services.CaseQueryService;
import com.dartlinwave.platform.lawconnectplatform.cases.interfaces.rest.resources.CaseResource;
import com.dartlinwave.platform.lawconnectplatform.cases.interfaces.rest.resources.CreateCaseResource;
import com.dartlinwave.platform.lawconnectplatform.cases.interfaces.rest.transforms.CaseResourceFromEntityAssembler;
import com.dartlinwave.platform.lawconnectplatform.cases.interfaces.rest.transforms.CreateCaseCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/cases")
@Tag(name = "Cases", description = "Cases API")
public class CasesController {

    private final CaseQueryService caseQueryService;
    private final CaseCommandService caseCommandService;

    public CasesController(CaseCommandService caseCommandService, CaseQueryService caseQueryService) {
        this.caseCommandService = caseCommandService;
        this.caseQueryService = caseQueryService;
    }

    @Operation(summary = "Create a new case", description = "Create a new case")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Case created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<CaseResource> createCase(@RequestBody CreateCaseResource resource) {

        Optional<Case> newCase = caseCommandService.handle(
                CreateCaseCommandFromResourceAssembler.toCommandFromResource(resource)
        );

        return newCase.map(
                        source -> new ResponseEntity<>(
                                CaseResourceFromEntityAssembler.toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Get a list of cases by client id",
            description = "Get a list of cases by client id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cases found"),
            @ApiResponse(responseCode = "404", description = "Cases not found")
    })
    @GetMapping("/{caseId}")
    public ResponseEntity<List<CaseResource>> getCaseById(@PathVariable String caseId) {
        List<Case> cases = caseQueryService.handle(new GetCasesByClientIdQuery(Long.parseLong(caseId)));

        if (cases.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<CaseResource> resources = cases.stream()
                .map(CaseResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(resources);
    }
}