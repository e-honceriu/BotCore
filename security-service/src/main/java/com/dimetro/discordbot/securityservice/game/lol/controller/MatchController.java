package com.dimetro.discordbot.securityservice.game.lol.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dimetro.discordbot.securityservice.game.lol.service.MatchService;

@RestController
@RequestMapping("/api/game/lol/match")
public class MatchController {
    
    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    public ResponseEntity<?> getMatch(@RequestParam(name = "matchId", required = true) UUID matchId,
                                      @RequestHeader(name = "X-API-KEY", required = true) String apiKey) {
        return matchService.getMatch(matchId, apiKey);
    }

    @PostMapping
    public ResponseEntity<?> createMatch(@RequestBody Object requestBody,
                                         @RequestHeader(name = "X-API-KEY", required = true) String apiKey) {
        return matchService.createMatch(requestBody, apiKey);
    }

    @PostMapping("/rosters")
    public ResponseEntity<?> generateRosters(@RequestBody Object requestBody,
                                             @RequestHeader(name = "X-API-KEY", required = true) String apiKey) {
        return matchService.generateRosters(requestBody, apiKey);
    }

    @PostMapping("/champion/ban")
    public ResponseEntity<?> banChampion(@RequestBody Object requestBody,
                                         @RequestHeader(name = "X-API-KEY", required = true) String apiKey) {
        return matchService.banChampion(requestBody, apiKey);
    }

    @PostMapping("/result")
    public ResponseEntity<?> setResult(@RequestBody Object requestBody,
                                       @RequestHeader(name = "X-API-KEY", required = true) String apiKey) {
        return matchService.setResult(requestBody, apiKey);
    }

    @PostMapping("/result/live")
    public ResponseEntity<?> setResultLive(@RequestBody Object requestBody,
                                           @RequestHeader(name = "X-API-KEY", required = true) String apiKey) {
        return matchService.setResultLive(requestBody, apiKey);
    }

    @PostMapping("/live")
    public ResponseEntity<?> getMatch(@RequestBody Object requestBody,
                                      @RequestHeader(name = "X-API-KEY", required = true) String apiKey) {
        return matchService.getMatchLive(requestBody, apiKey);
    }

}
