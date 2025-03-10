package com.dimetro.discordbot.securityservice.game.lol.service;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dimetro.discordbot.securityservice.security.core.entity.Bot;
import com.dimetro.discordbot.securityservice.security.core.service.bot.BotService;
import com.dimetro.discordbot.securityservice.util.ApiUrlBuilder;
import com.dimetro.discordbot.securityservice.util.BotRestServiceClient;

@Service
public class PlayerService extends BotRestServiceClient {

    @Value("${microservices.game.url}")
    protected String GAME_SERVICE_URL;

    @Autowired
    public PlayerService(RestTemplate restTemplate, BotService botService) {
        super(restTemplate, botService);
    }

    public ResponseEntity<?> createPlayer(String apiKey, Object requestBody) {

        String url = ApiUrlBuilder.from(GAME_SERVICE_URL + Endpoints.PLAYER).build();

        Map<String, Object> updatedBody = addBotIdToRequestBody(apiKey, requestBody);

        return sendRequest(HttpMethod.POST, url, updatedBody);
    }

    public ResponseEntity<?> editPlayer(String apiKey, Object requestBody) {

        String url = ApiUrlBuilder.from(GAME_SERVICE_URL + Endpoints.PLAYER).build();
        
        Map<String, Object> updatedBody = addBotIdToRequestBody(apiKey, requestBody);

        return sendRequest(HttpMethod.PUT, url, updatedBody);
    }

    public ResponseEntity<?> deletePlayer(UUID playerId, String apiKey) {
        
        Bot bot = botService.getBotByKeyOrThrow(apiKey);

        String url = ApiUrlBuilder.from(GAME_SERVICE_URL + Endpoints.PLAYER)
                                .addParam("playerId", playerId)
                                .addParam("botId", bot.getId())
                                .build();

        return sendRequest(HttpMethod.DELETE, url);
    }

    public ResponseEntity<?> getPlayer(UUID playerId, String discordPlayerId, String apiKey) {

        Bot bot = botService.getBotByKeyOrThrow(apiKey);

        String url = ApiUrlBuilder.from(GAME_SERVICE_URL + Endpoints.PLAYER)
                                .addParam("discordPlayerId", discordPlayerId)
                                .addParam("playerId", playerId)
                                .addParam("botId", bot.getId())
                                .build();
        
        return sendRequest(HttpMethod.GET, url);
    }

}
