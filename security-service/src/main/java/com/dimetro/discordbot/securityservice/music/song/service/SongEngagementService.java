package com.dimetro.discordbot.securityservice.music.song.service;

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
public class SongEngagementService extends BotRestServiceClient {
    
    @Value("${microservices.music.url}")
    protected String MUSIC_SERVICE_URL;

    @Autowired
    public SongEngagementService(RestTemplate restTemplate, BotService botService) {
        super(restTemplate, botService);
    }

    public ResponseEntity<?> addListeners(String apiKey, Object requestBody) {
        
        Map<String, Object> updatedBody = addBotIdToRequestBody(apiKey, requestBody);

        String url = ApiUrlBuilder.from(MUSIC_SERVICE_URL + Endpoints.LISTENER).build(); 

        return sendRequest(HttpMethod.POST, url, updatedBody);
    }
 
    public ResponseEntity<?> addReaction(String apiKey, Object requestBody) {

        Map<String, Object> updatedBody = addBotIdToRequestBody(apiKey, requestBody);

        String url = ApiUrlBuilder.from(MUSIC_SERVICE_URL + Endpoints.REACTION).build();

        return sendRequest(HttpMethod.POST, url, updatedBody);
    }

    public ResponseEntity<?> getSongReaction(String apiKey, UUID songId, String guildDiscordId) {
        
        Bot bot = botService.getBotByKeyOrThrow(apiKey);

        String url = ApiUrlBuilder.from(MUSIC_SERVICE_URL + Endpoints.REACTION_SONG)
                            .addParam("songId", songId)
                            .addParam("botId", bot.getId())
                            .addParam("guildDiscordId", guildDiscordId)
                            .build();
        
        return sendRequest(HttpMethod.GET, url);
    }

    public ResponseEntity<?> addStream(String apiKey, Object requestBody) {
        
        Map<String, Object> updatedBody = addBotIdToRequestBody(apiKey, requestBody);

        String url = ApiUrlBuilder.from(MUSIC_SERVICE_URL + Endpoints.STREAM).build();

        return sendRequest(HttpMethod.POST, url, updatedBody);
    }

    public ResponseEntity<?> getSongEngagement(String apiKey, UUID songId, String guildDiscordId) {
        
        Bot bot = botService.getBotByKeyOrThrow(apiKey);

        String url = ApiUrlBuilder.from(MUSIC_SERVICE_URL + Endpoints.ENGAGEMENT_SONG)
                            .addParam("songId", songId)
                            .addParam("botId", bot.getId())
                            .addParam("guildDiscordId", guildDiscordId)
                            .build();
        
        return sendRequest(HttpMethod.GET, url);
    }

}
