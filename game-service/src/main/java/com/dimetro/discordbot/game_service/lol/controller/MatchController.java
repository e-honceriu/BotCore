package com.dimetro.discordbot.game_service.lol.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dimetro.discordbot.game_service.lol.dto.request.match.*;
import com.dimetro.discordbot.game_service.lol.dto.response.match.*;
import com.dimetro.discordbot.game_service.lol.service.match.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/lol/match")
public class MatchController {

    private final MatchManagementService matchService;
    private final MatchBanningService banningService;
    private final MatchRosterService rosterService;
    private final ResultService resultService;
    private final MatchLiveClientService matchLiveClientService;

    @Autowired
    public MatchController(
        MatchManagementService matchService, MatchBanningService banningService,
        MatchRosterService rosterService, ResultService resultService, 
        MatchLiveClientService matchLiveClientService
    ) {
        this.matchService = matchService;
        this.banningService = banningService;
        this.rosterService = rosterService;
        this.resultService = resultService;
        this.matchLiveClientService = matchLiveClientService;
    }

    @GetMapping
    public ResponseEntity<MatchResponseDTO> getMatch(
        @RequestParam(name = "matchId", required = true) UUID matchId, 
        @RequestParam(name = "botId", required = true) UUID botId
    ) {
        return ResponseEntity.ok(matchService.getMatch(matchId, botId));
    }

    @PostMapping
    public ResponseEntity<MatchResponseDTO> createMatch(@RequestBody @Valid CreateMatchRequestDTO dto) {
        return ResponseEntity.ok(matchService.createMatch(dto));
    }

    @PostMapping("/rosters")
    public ResponseEntity<MatchResponseDTO> generateRosters(@RequestBody @Valid GenerateTeamsRequestDTO dto) {
        return ResponseEntity.ok(rosterService.generateTeams(dto));
    }

    @PostMapping("/champion/ban")
    public ResponseEntity<MatchResponseDTO> banChampion(@RequestBody @Valid BanChampionRequestDTO dto) {
        return ResponseEntity.ok(banningService.banChampion(dto));
    }

    @PostMapping("/result")
    public ResponseEntity<MatchResultResponseDTO> setResult(@RequestBody @Valid SetMatchResultRequestDTO dto) {
        return ResponseEntity.ok(resultService.setMatchResult(dto));
    }

    @PostMapping("/result/live")
    public ResponseEntity<Void> setResultLive(@RequestBody @Valid SetLiveMatchResultDTO dto) {
        resultService.setLiveResult(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/live")
    public ResponseEntity<LiveClientMatchResponseDTO> getMatch(@RequestBody @Valid GetLiveClientMatchRequestDTO dto) {
        return ResponseEntity.ok(matchLiveClientService.getMatch(dto));
    }

}
