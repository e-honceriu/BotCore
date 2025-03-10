package com.dimetro.discordbot.securityservice.music.playlist.service;

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
public class PlaylistService extends BotRestServiceClient {
    
    @Value("${microservices.music.url}")
    protected String MUSIC_SERVICE_URL;

    @Autowired
    public PlaylistService(RestTemplate restTemplate, BotService botService) {
        super(restTemplate, botService);
    }

    public ResponseEntity<?> getPlaylist(String apiKey, UUID playlistId, String title, String guildDiscordId) {

        UUID botId = this.botService.getBotByKeyOrThrow(apiKey).getId();

        String url = ApiUrlBuilder.from(MUSIC_SERVICE_URL + Endpoints.PLAYLIST)
                                .addParam("playlistId", playlistId)
                                .addParam("botId", botId)
                                .addParam("title", title)
                                .addParam("guildDiscordId", guildDiscordId)
                                .build();
        
        return sendRequest(HttpMethod.GET, url);
    }

    public ResponseEntity<?> createPlaylist(Object requestBody, String apiKey) {

        String url = ApiUrlBuilder.from(MUSIC_SERVICE_URL + Endpoints.PLAYLIST).build();

        Map<String, Object> updatedBody = addBotIdToRequestBody(apiKey, requestBody);

        return sendRequest(HttpMethod.POST, url, updatedBody);
    }

    public ResponseEntity<?> addSongsToPlaylistById(Object requestBody, String apiKey) {

        String url = ApiUrlBuilder.from(MUSIC_SERVICE_URL + Endpoints.PLAYLIST_SONG_ID).build();

        Map<String, Object> updatedBody = addBotIdToRequestBody(apiKey, requestBody);

        return sendRequest(HttpMethod.POST, url, updatedBody);
    }

    public ResponseEntity<?> addSongsToPlaylistByTitle(Object requestBody, String apiKey) {

        String url = ApiUrlBuilder.from(MUSIC_SERVICE_URL + Endpoints.PLAYLIST_SONG_TITLE).build();

        Map<String, Object> updatedBody = addBotIdToRequestBody(apiKey, requestBody);

        return sendRequest(HttpMethod.POST, url, updatedBody);
    }

    public ResponseEntity<?> removeSongFromPlaylist(UUID playlistId, String requesterDiscordId, UUID songId, Integer position, String apiKey) {

        Bot bot = botService.getBotByKeyOrThrow(apiKey);

        String url = ApiUrlBuilder.from(MUSIC_SERVICE_URL + Endpoints.PLAYLIST_SONG)
                                  .addParam("playlistId", playlistId)
                                  .addParam("botId", bot.getId())
                                  .addParam("requesterDiscordId", requesterDiscordId)
                                  .addParam("songId", songId)
                                  .addParam("position", position)
                                  .build();

        return sendRequest(HttpMethod.DELETE, url);
    }

    public ResponseEntity<?> deletePlaylist(UUID playlistId, String requesterDiscordId, String apiKey) {
        
        Bot bot = botService.getBotByKeyOrThrow(apiKey);

        String url = ApiUrlBuilder.from(MUSIC_SERVICE_URL + Endpoints.PLAYLIST)
                                    .addParam("playlistId", playlistId)
                                    .addParam("requesterDiscordId", requesterDiscordId)
                                    .addParam("botId", bot.getId())
                                    .build();

        return sendRequest(HttpMethod.DELETE, url);
    }

    public ResponseEntity<?> getPlaylistsByGuildDiscordId(String apiKey, String guildDiscordId) {

        UUID botId = this.botService.getBotByKeyOrThrow(apiKey).getId();

        String url = ApiUrlBuilder.from(MUSIC_SERVICE_URL + Endpoints.PLAYLIST_GUILD)
                                .addParam("guildDiscordId", guildDiscordId)
                                .addParam("botId", botId)
                                .build();

        return sendRequest(HttpMethod.GET, url);
    }


}
