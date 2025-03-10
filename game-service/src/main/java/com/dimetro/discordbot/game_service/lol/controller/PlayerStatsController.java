package com.dimetro.discordbot.game_service.lol.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dimetro.discordbot.game_service.lol.dto.request.player.LiveMatchStatsUpdateRequestDTO;
import com.dimetro.discordbot.game_service.lol.dto.response.player.PlayerStatsResponseDTO;
import com.dimetro.discordbot.game_service.lol.service.player.LivePlayerStatsService;
import com.dimetro.discordbot.game_service.lol.service.player.PlayerManagementService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/lol/player/stats")
public class PlayerStatsController {
    
    private final LivePlayerStatsService livePlayerStatsService;
    private final PlayerManagementService playerManagementService;

    @Autowired
    public PlayerStatsController(LivePlayerStatsService livePlayerStatsService, PlayerManagementService playerManagementService) {
        this.livePlayerStatsService = livePlayerStatsService;
        this.playerManagementService = playerManagementService;
    }

    @PostMapping("/live")
    public ResponseEntity<Void> updateLiveStats(@RequestBody @Valid LiveMatchStatsUpdateRequestDTO dto) {
        livePlayerStatsService.updateLivePlayerStats(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<PlayerStatsResponseDTO> getStats(@RequestParam(name="playerId", required=true) UUID playerId,
                                                           @RequestParam(name="matchId", required=true) UUID matchId,
                                                           @RequestParam(name="botId", required=true) UUID botId) {
        return ResponseEntity.ok(playerManagementService.getPlayerStats(matchId, playerId, botId));
    }

}
