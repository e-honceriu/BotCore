package com.dimetro.discordbot.securityservice.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.dimetro.discordbot.securityservice.exception.InvalidRequestBodyException;
import com.dimetro.discordbot.securityservice.security.core.entity.Bot;
import com.dimetro.discordbot.securityservice.security.core.service.bot.BotService;

public class BotRestServiceClient extends RestServiceClient {

    protected final BotService botService;

    public BotRestServiceClient(RestTemplate restTemplate, BotService botService) {
        super(restTemplate);
        this.botService = botService;
    }

    protected Map<String, Object> addBotIdToRequestBody(String apiKey, Object requestBody) {
        
         Bot bot = botService.getBotByKeyOrThrow(apiKey);

        Map<String, Object> modifiedBody;
        
        if (requestBody instanceof Map) {
            modifiedBody = new HashMap<>((Map<String, Object>) requestBody);
        } else {
            throw new InvalidRequestBodyException();
        }

        modifiedBody.put("botId", bot.getId());

        return modifiedBody;
    }
    
}
