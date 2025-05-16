package com.dartlinwave.platform.lawconnectplatform.cases.interfaces.rest;

import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.aggregates.Case;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.commands.AcceptLawyerCommand;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.commands.ApplyToCaseCommand;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.commands.CreateCaseCommand;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.commands.RejectLawyerCommand;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.entities.Applicant;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.queries.GetCaseDetailsQuery;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.queries.GetCasesByClientQuery;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.queries.GetCasesBySpecialtyQuery;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.valueobjects.Specialty;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.services.CaseCommandService;
import com.dartlinwave.platform.lawconnectplatform.cases.domain.services.CaseQueryService;
import com.dartlinwave.platform.lawconnectplatform.cases.interfaces.rest.resources.ApplicantResource;
import com.dartlinwave.platform.lawconnectplatform.cases.interfaces.rest.resources.CaseResource;
import com.dartlinwave.platform.lawconnectplatform.cases.interfaces.rest.resources.CreateCaseResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/cases")
@Tag(name = "Cases", description = "Cases API")
public class CasesController {

    private final CaseCommandService caseCommandService;
    private final CaseQueryService caseQueryService;

    public CasesController(CaseCommandService caseCommandService, CaseQueryService caseQueryService) {
        this.caseCommandService = caseCommandService;
        this.caseQueryService = caseQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new case")
    public ResponseEntity<CaseResource> createCase(@RequestBody CreateCaseResource resource) {
        CreateCaseCommand command = new CreateCaseCommand(
                UUID.fromString(resource.getClientId()),
                resource.getTitle(),
                resource.getDescription(),
                Specialty.valueOf(resource.getSpecialty())
        );

        Case newCase = caseCommandService.handleCreateCase(command);
        return new ResponseEntity<>(convertToResource(newCase), HttpStatus.CREATED);
    }

    @PostMapping("/{caseId}/applications")
    @Operation(summary = "Apply to a case")
    public ResponseEntity<Void> applyToCase(@PathVariable Long caseId, @RequestBody ApplicantResource resource) {
        ApplyToCaseCommand command = new ApplyToCaseCommand(
                caseId,
                UUID.fromString(resource.getLawyerId())
        );

        caseCommandService.handleApplyToCase(command);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{caseId}/lawyers/{lawyerId}/accept")
    @Operation(summary = "Accept a lawyer for a case")
    public ResponseEntity<Void> acceptLawyer(@PathVariable Long caseId, @PathVariable String lawyerId) {
        AcceptLawyerCommand command = new AcceptLawyerCommand(
                caseId,
                UUID.fromString(lawyerId)
        );

        caseCommandService.handleAcceptLawyer(command);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{caseId}/lawyers/{lawyerId}/reject")
    @Operation(summary = "Reject a lawyer for a case")
    public ResponseEntity<Void> rejectLawyer(@PathVariable Long caseId, @PathVariable String lawyerId) {
        RejectLawyerCommand command = new RejectLawyerCommand(
                caseId,
                UUID.fromString(lawyerId)
        );

        caseCommandService.handleRejectLawyer(command);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/specialty/{specialty}")
    @Operation(summary = "Get cases by specialty")
    public ResponseEntity<List<CaseResource>> getCasesBySpecialty(@PathVariable String specialty) {
        GetCasesBySpecialtyQuery query = new GetCasesBySpecialtyQuery(
                Specialty.valueOf(specialty)
        );

        List<Case> cases = caseQueryService.handleGetCasesBySpecialty(query);
        List<CaseResource> resources = cases.stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping("/{caseId}")
    @Operation(summary = "Get case details")
    public ResponseEntity<CaseResource> getCaseDetails(@PathVariable Long caseId) {
        GetCaseDetailsQuery query = new GetCaseDetailsQuery(caseId);

        Optional<Case> caseOptional = caseQueryService.handleGetCaseDetails(query);

        if (caseOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(convertToResource(caseOptional.get()), HttpStatus.OK);
    }

    @GetMapping("/client/{clientId}")
    @Operation(summary = "Get cases by client")
    public ResponseEntity<List<CaseResource>> getCasesByClient(@PathVariable String clientId) {
        GetCasesByClientQuery query = new GetCasesByClientQuery(
                UUID.fromString(clientId)
        );

        List<Case> cases = caseQueryService.handleGetCasesByClient(query);
        List<CaseResource> resources = cases.stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    // Helper method to convert domain object to resource
    private CaseResource convertToResource(Case caseEntity) {
        CaseResource resource = new CaseResource();
        resource.setId(caseEntity.getId());
        resource.setClientId(caseEntity.getClientId().toString());
        resource.setTitle(caseEntity.getTitle());
        resource.setDescription(caseEntity.getDescription());
        resource.setSpecialtyRequired(caseEntity.getSpecialtyRequired().toString());
        resource.setCreatedAt(caseEntity.getCreatedAt());
        resource.setStatus(caseEntity.getStatus());

        List<ApplicantResource> applicantResources = caseEntity.getApplicants().stream()
                .map(this::convertToApplicantResource)
                .collect(Collectors.toList());

        resource.setApplicants(applicantResources);
        return resource;
    }

    private ApplicantResource convertToApplicantResource(Applicant applicant) {
        ApplicantResource resource = new ApplicantResource();
        resource.setLawyerId(applicant.getLawyerId().toString());
        resource.setApplicationDate(applicant.getApplicationDate());
        resource.setStatus(applicant.getStatus());
        return resource;
    }
}