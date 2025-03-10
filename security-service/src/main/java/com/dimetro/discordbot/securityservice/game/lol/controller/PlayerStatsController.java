package com.dimetro.discordbot.securityservice.game.lol.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dimetro.discordbot.securityservice.game.lol.service.PlayerStatsService;

@RestController
@RequestMapping("/api/game/lol/player/stats")
public class PlayerStatsController {
    
    private final PlayerStatsService playerStatsService;

    @Autowired
    public PlayerStatsController(PlayerStatsService playerStatsService) {
        this.playerStatsService = playerStatsService;
    }

    @PostMapping("/live")
    public ResponseEntity<?> updateLiveStats(@RequestBody Object requestBody,
                                            @RequestHeader(name = "X-API-KEY", required = true) String apiKey) {
        return playerStatsService.updateLiveStats(requestBody, apiKey);
    }

    @GetMapping
    public ResponseEntity<?> getStats(@RequestParam(name="playerId", required=true) UUID playerId,
                                      @RequestParam(name="matchId", required=true) UUID matchId,
                                      @RequestHeader(name = "X-API-KEY", required = true) String apiKey) {
        return playerStatsService.getStats(playerId, matchId, apiKey);
    }

}
