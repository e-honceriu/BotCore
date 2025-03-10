package com.dimetro.discordbot.securityservice.security.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dimetro.discordbot.securityservice.security.core.dto.request.bot.CreateBotRequestDTO;
import com.dimetro.discordbot.securityservice.security.core.dto.response.bot.BotResponseDTO;
import com.dimetro.discordbot.securityservice.security.core.service.bot.BotManagementService;

@RestController
@RequestMapping("/api/bot")
public class BotController {
    
    private final BotManagementService botManagementService;

    @Autowired
    public BotController(BotManagementService botManagementService) {
        this.botManagementService = botManagementService;
    }

    @PostMapping
    public ResponseEntity<BotResponseDTO> createBot(@RequestBody CreateBotRequestDTO requestDTO) {
        BotResponseDTO responseDTO = botManagementService.createBot(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

}
