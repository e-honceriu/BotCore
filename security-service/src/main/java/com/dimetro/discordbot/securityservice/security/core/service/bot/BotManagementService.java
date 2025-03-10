package com.dimetro.discordbot.securityservice.security.core.service.bot;

import org.springframework.stereotype.Service;

import com.dimetro.discordbot.securityservice.security.core.dto.request.bot.CreateBotRequestDTO;
import com.dimetro.discordbot.securityservice.security.core.dto.response.bot.BotResponseDTO;

@Service
public class BotManagementService {
    
    private final BotService botService;

    public BotManagementService(BotService botService) {
        this.botService = botService;
    }

    public BotResponseDTO createBot(CreateBotRequestDTO requestDTO) {
        return BotResponseDTO.build(botService.createBotOrThrow(requestDTO.getName()));
    }

}
