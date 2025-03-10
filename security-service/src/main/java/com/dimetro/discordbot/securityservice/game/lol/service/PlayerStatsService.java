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
public class PlayerStatsService extends BotRestServiceClient {
    
    @Value("${microservices.game.url}")
    protected String GAME_SERVICE_URL;

    @Autowired
    public PlayerStatsService(RestTemplate restTemplate, BotService botService) {
        super(restTemplate, botService);
    }

    public ResponseEntity<?> updateLiveStats(Object requestBody, String apiKey) {

        String url = ApiUrlBuilder.from(GAME_SERVICE_URL + Endpoints.PLAYER_STATS_LIVE).build();
        
        Map<String, Object> updatedBody = addBotIdToRequestBody(apiKey, requestBody);

        return sendRequest(HttpMethod.POST, url, updatedBody);
    }

    public ResponseEntity<?> getStats(UUID playerId, UUID matchId, String apiKey) {

        Bot bot = botService.getBotByKeyOrThrow(apiKey);

        String url = ApiUrlBuilder.from(GAME_SERVICE_URL + Endpoints.PLAYER_STATS)
                                .addParam("playerId", playerId)
                                .addParam("matchId", matchId)
                                .addParam("botId", bot.getId())
                                .build();

        return sendRequest(HttpMethod.GET, url);
    }

}
