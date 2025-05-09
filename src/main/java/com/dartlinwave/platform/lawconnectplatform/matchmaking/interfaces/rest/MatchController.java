package com.dartlinwave.platform.lawconnectplatform.matchmaking.interfaces.rest;

import com.dartlinwave.platform.lawconnectplatform.matchmaking.domain.model.commands.AcceptCaseByLawyerCommand;
import com.dartlinwave.platform.lawconnectplatform.matchmaking.domain.model.commands.AcceptLawyerByClientCommand;
import com.dartlinwave.platform.lawconnectplatform.matchmaking.domain.model.commands.DeclineCaseByLawyerCommand;
import com.dartlinwave.platform.lawconnectplatform.matchmaking.domain.model.commands.DeclineLawyerByClientCommand;
import com.dartlinwave.platform.lawconnectplatform.matchmaking.domain.model.queries.GetAllMatchesByLawyerIdQuery;
import com.dartlinwave.platform.lawconnectplatform.matchmaking.domain.model.queries.GetMatchByIdQuery;
import com.dartlinwave.platform.lawconnectplatform.matchmaking.domain.services.MatchCommandService;
import com.dartlinwave.platform.lawconnectplatform.matchmaking.domain.services.MatchQueryService;
import com.dartlinwave.platform.lawconnectplatform.matchmaking.interfaces.rest.resources.CreatePreMatchResource;
import com.dartlinwave.platform.lawconnectplatform.matchmaking.interfaces.rest.resources.MatchResource;
import com.dartlinwave.platform.lawconnectplatform.matchmaking.interfaces.rest.transform.CreatePreMatchCommandFromResourceAssembler;
import com.dartlinwave.platform.lawconnectplatform.matchmaking.interfaces.rest.transform.MatchResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/v1/matches", produces= MediaType.APPLICATION_JSON_VALUE)
@Tag(name="Matches", description="Match Management Endpoints")
public class MatchController {

    private final MatchCommandService matchCommandService;
    private final MatchQueryService matchQueryService;

    public MatchController(MatchCommandService matchCommandService, MatchQueryService matchQueryService) {
        this.matchCommandService = matchCommandService;
        this.matchQueryService = matchQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new match")
    public ResponseEntity<MatchResource> createMatch(@RequestBody CreatePreMatchResource resource) {
        var createPreMatchCommand = CreatePreMatchCommandFromResourceAssembler.toCommandFromResource(resource);
        var matchId = matchCommandService.handle(createPreMatchCommand);

        if (matchId == null || matchId == 0L) return ResponseEntity.badRequest().build();

        var getMatchByIdQuery = new GetMatchByIdQuery(matchId);
        var match = matchQueryService.handle(getMatchByIdQuery);

        if (match.isEmpty()) return ResponseEntity.notFound().build();

        var matchResource = MatchResourceFromEntityAssembler.toResourceFromEntity(match.get());
        return new ResponseEntity<>(matchResource, HttpStatus.CREATED);
    }

    @GetMapping("/{matchId}")
    @Operation(summary = "Get a match by Id")
    public ResponseEntity<MatchResource> getMatchById(@PathVariable Long matchId) {
        var getMatchByIdQuery = new GetMatchByIdQuery(matchId);
        var match = matchQueryService.handle(getMatchByIdQuery);

        if (match.isEmpty()) return ResponseEntity.notFound().build();

        var matchResource = MatchResourceFromEntityAssembler.toResourceFromEntity(match.get());
        return ResponseEntity.ok(matchResource);
    }

    @PatchMapping("/accept-lawyer-by-client/{matchId}")
    @Operation(summary = "Accept a lawyer by client")
    public ResponseEntity<?> acceptLawyerByClient(@PathVariable Long matchId) {
        matchCommandService.handle(new AcceptLawyerByClientCommand(matchId));
        return ResponseEntity.ok("Client approved lawyer");
    }

    @PatchMapping("/decline-lawyer-by-client/{matchId}")
    @Operation(summary = "Decline a lawyer by client")
    public ResponseEntity<?> declineLawyerByClient(@PathVariable Long matchId) {
        matchCommandService.handle(new DeclineLawyerByClientCommand(matchId));
        return ResponseEntity.ok("Client declined lawyer");
    }

    @PatchMapping("/accept-case-by-lawyer/{matchId}")
    @Operation(summary = "Accept a case by lawyer")
    public ResponseEntity<?> acceptCaseByLawyer(@PathVariable Long matchId) {
        matchCommandService.handle(new AcceptCaseByLawyerCommand(matchId));
        return ResponseEntity.ok("Lawyer accepted case");
    }

    @PatchMapping("/decline-case-by-lawyer/{matchId}")
    @Operation(summary = "Decline a case by lawyer")
    public ResponseEntity<?> declineCaseByLawyer(@PathVariable Long matchId) {
        matchCommandService.handle(new DeclineCaseByLawyerCommand(matchId));
        return ResponseEntity.ok("Lawyer declined case");
    }

    @GetMapping("/lawyerId/{lawyerId}/pending")
    @Operation(summary = "Get all pending matches by lawyer Id")
    public ResponseEntity<List<MatchResource>> getAllPendingMatchesByLawyerId(@PathVariable Long lawyerId) {
        var matches = matchQueryService.handle(new GetAllMatchesByLawyerIdQuery(lawyerId));

        if (matches.isEmpty()) return ResponseEntity.notFound().build();

        var matchResource =  matches.stream()
                .map(MatchResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(matchResource);
    }

    @GetMapping("/caseId/{caseId}/pending")
    @Operation(summary = "Get all pending matches by case Id")
    public ResponseEntity<List<MatchResource>> getAllPendingMatchesByCaseId(@PathVariable Long caseId) {
        var matches = matchQueryService.handle(new GetAllMatchesByLawyerIdQuery(caseId));

        if (matches.isEmpty()) return ResponseEntity.notFound().build();

        var matchResource =  matches.stream()
                .map(MatchResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(matchResource);
    }
}
