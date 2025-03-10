package com.dimetro.discordbot.securityservice.security.core.service.apikey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimetro.discordbot.securityservice.security.core.dto.request.apikey.GenerateApiKeyRequestDTO;
import com.dimetro.discordbot.securityservice.security.core.dto.response.apikey.ApiKeyResponseDTO;
import com.dimetro.discordbot.securityservice.security.core.entity.Bot;
import com.dimetro.discordbot.securityservice.security.core.service.apikey.exception.*;
import com.dimetro.discordbot.securityservice.security.core.service.bot.BotService;

@Service
public class ApiKeyManagementService {
    
    private final ApiKeyService apiKeyService;
    private final BotService botService;

    @Autowired
    public ApiKeyManagementService(ApiKeyService apiKeyService, BotService botService) {
        this.apiKeyService = apiKeyService;
        this.botService = botService;
    }

    public ApiKeyResponseDTO generateKey(GenerateApiKeyRequestDTO requestDTO) {

        Bot bot;

        if (requestDTO.getBotId() != null) {
            bot = botService.getByIdOrThrow(requestDTO.getBotId());
        } else if (requestDTO.getBotName() != null) {
            bot = botService.getByNameOrThrow(requestDTO.getBotName());
        } else {
            throw new InvalidGenerationRequestException();
        }

        if (bot.isBlocked()) {
            throw new BlockedBotException(bot);
        }

        return ApiKeyResponseDTO.build(apiKeyService.generateApiKey(bot));
    }

}
