package com.dimetro.discordbot.securityservice.game.lol.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dimetro.discordbot.securityservice.game.lol.service.TeamService;

@RestController
@RequestMapping("/api/game/lol/team")
public class TeamController {
    
    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping("/champion-pool")
    public ResponseEntity<?> generateChampionPool(@RequestBody Object requestBody,
                                                  @RequestHeader(name = "X-API-KEY", required = true) String apiKey) {
        return teamService.generateChampionPool(requestBody, apiKey);
    }

    @GetMapping("/champion-pool")
    public ResponseEntity<?> getChampionPool(@RequestParam(name = "teamId", required = true) UUID teamId,
                                            @RequestHeader(name = "X-API-KEY", required = true) String apiKey) {
        return teamService.getChampionPool(teamId, apiKey);
    }

}
