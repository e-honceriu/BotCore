package com.dimetro.discordbot.securityservice.game.lol.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dimetro.discordbot.securityservice.game.lol.service.PlayerService;

@RestController
@RequestMapping("/api/game/lol/player")
public class PlayerController {
    
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<?> createPlayer(@RequestBody Object requestBody,
                                          @RequestHeader(name = "X-API-KEY", required = true) String apiKey) {
        return playerService.createPlayer(apiKey, requestBody);
    }

    @PutMapping
    public ResponseEntity<?> editPlayer(@RequestBody Object requestBody,
                                        @RequestHeader(name = "X-API-KEY", required = true) String apiKey) {
        return playerService.editPlayer(apiKey, requestBody);
    }

    @DeleteMapping
    public ResponseEntity<?> deletePlayer(@RequestParam(name = "playerId", required = true) UUID playerId, 
                                          @RequestHeader(name = "X-API-KEY", required = true) String apiKey) {
        return playerService.deletePlayer(playerId, apiKey);
    }

    @GetMapping
    public ResponseEntity<?> getPlayer(@RequestParam(name = "playerId", required = false) UUID playerId,
                                       @RequestParam(name = "discordPlayerId", required = false) String discordPlayerId,
                                       @RequestHeader(name = "X-API-KEY", required = true) String apiKey) {
        return playerService.getPlayer(playerId, discordPlayerId, apiKey);
    }

}
