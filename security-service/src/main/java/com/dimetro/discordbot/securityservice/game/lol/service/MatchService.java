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
public class MatchService extends BotRestServiceClient {
    
    @Value("${microservices.game.url}")
    protected String GAME_SERVICE_URL;

    @Autowired
    public MatchService(RestTemplate restTemplate, BotService botService) {
        super(restTemplate, botService);
    }

    public ResponseEntity<?> getMatch(UUID matchId, String apiKey) {

        Bot bot = botService.getBotByKeyOrThrow(apiKey);

        String url = ApiUrlBuilder.from(GAME_SERVICE_URL + Endpoints.MATCH)
                                .addParam("matchId", matchId)
                                .addParam("botId", bot.getId())
                                .build();

        return sendRequest(HttpMethod.GET, url);
    }

    public ResponseEntity<?> createMatch(Object requestBody, String apiKey) {

        String url = ApiUrlBuilder.from(GAME_SERVICE_URL + Endpoints.MATCH).build();

        Map<String, Object> updatedBody = addBotIdToRequestBody(apiKey, requestBody);

        return sendRequest(HttpMethod.POST, url, updatedBody);
    }

    public ResponseEntity<?> generateRosters(Object requestBody, String apiKey) {

        String url = ApiUrlBuilder.from(GAME_SERVICE_URL + Endpoints.MATCH_ROSERS).build();
        
        Map<String, Object> updatedBody = addBotIdToRequestBody(apiKey, requestBody);

        return sendRequest(HttpMethod.POST, url, updatedBody);
    }

    public ResponseEntity<?> banChampion(Object requestBody, String apiKey) {

        String url = ApiUrlBuilder.from(GAME_SERVICE_URL + Endpoints.MATCH_CHAMPION_BAN).build();

        Map<String, Object> updatedBody = addBotIdToRequestBody(apiKey, requestBody);

        return sendRequest(HttpMethod.POST, url, updatedBody);
    }

    public ResponseEntity<?> setResult(Object requestBody, String apiKey) {

        String url = ApiUrlBuilder.from(GAME_SERVICE_URL + Endpoints.MATCH_RESULT).build();

        Map<String, Object> updatedBody = addBotIdToRequestBody(apiKey, requestBody);

        return sendRequest(HttpMethod.POST, url, updatedBody);
    }

    public ResponseEntity<?> setResultLive(Object requestBody, String apiKey) {

        String url = ApiUrlBuilder.from(GAME_SERVICE_URL + Endpoints.MATCH_RESULT_LIVE).build();

        Map<String, Object> updatedBody = addBotIdToRequestBody(apiKey, requestBody);

        return sendRequest(HttpMethod.POST, url, updatedBody);
    }

    public ResponseEntity<?> getMatchLive(Object requestBody, String apiKey) {

        String url = ApiUrlBuilder.from(GAME_SERVICE_URL + Endpoints.MATCH_LIVE).build();

        Map<String, Object> updatedBody = addBotIdToRequestBody(apiKey, requestBody);

        return sendRequest(HttpMethod.POST, url, updatedBody);
    }

}
