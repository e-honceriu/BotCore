package com.dimetro.discordbot.game_service.lol.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dimetro.discordbot.game_service.lol.dto.request.player.*;
import com.dimetro.discordbot.game_service.lol.dto.response.player.PlayerResponseDTO;
import com.dimetro.discordbot.game_service.lol.service.player.PlayerManagementService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/lol/player")
public class PlayerController {

    private final PlayerManagementService playerService;

    @Autowired
    public PlayerController(PlayerManagementService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<PlayerResponseDTO> createPlayer(@RequestBody @Valid CreatePlayerRequestDTO dto) {
        return ResponseEntity.ok(playerService.createPlayer(dto));
    }

    @GetMapping
    public ResponseEntity<PlayerResponseDTO> getPlayer(
        @RequestParam(name = "playerId", required = false) UUID playerId,
        @RequestParam(name = "discordPlayerId", required = false) String discordPlayerId,
        @RequestParam(name = "botId", required = true) UUID botId
    ) {
        PlayerResponseDTO dto = null;
        if (playerId != null) {
            dto = playerService.getPlayer(playerId, botId);
        }
        if (discordPlayerId != null) {
            dto = playerService.getPlayer(discordPlayerId, botId);
        }

        if (dto == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PutMapping
    public ResponseEntity<PlayerResponseDTO> editPlayer(@RequestBody @Valid EditPlayerRequestDTO dto) {
        return ResponseEntity.ok(playerService.editPlayer(dto));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePlayer(
        @RequestParam(name = "playerId", required = true) UUID playerId, 
        @RequestParam(name = "botId") UUID botId
    ) {
        playerService.deletePlayer(playerId, botId);
        return ResponseEntity.noContent().build();
    }

}
