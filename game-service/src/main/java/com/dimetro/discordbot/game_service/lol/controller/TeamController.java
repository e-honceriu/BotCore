package com.dimetro.discordbot.game_service.lol.controller;

import org.springframework.http.ResponseEntity;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import com.dimetro.discordbot.game_service.lol.dto.request.team.GenerateTeamChampPoolRequestDTO;
import com.dimetro.discordbot.game_service.lol.dto.response.team.TeamChampionPoolResponseDTO;
import com.dimetro.discordbot.game_service.lol.service.team.TeamManagementService;

@RestController
@RequestMapping("/api/lol/team")
public class TeamController {
    
    private final TeamManagementService teamManagementService;

    @Autowired
    public TeamController(TeamManagementService teamManagementService) {
        this.teamManagementService = teamManagementService;
    }

    @PostMapping("/champion-pool")
    public ResponseEntity<TeamChampionPoolResponseDTO> generateChampionPool(@RequestBody @Valid GenerateTeamChampPoolRequestDTO dto) {
        return ResponseEntity.ok(teamManagementService.generateChampionPool(dto));
    }

    @GetMapping("/champion-pool")
    public ResponseEntity<TeamChampionPoolResponseDTO> getChampionPool(@RequestParam(name = "teamId", required = true) UUID teamId,
                                                                       @RequestParam(name = "botId", required = true)UUID botId) {
        return ResponseEntity.ok(teamManagementService.getTeamChampionPool(teamId, botId));
    }

}
