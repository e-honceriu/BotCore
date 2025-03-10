package com.dimetro.discordbot.securityservice.music.song.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dimetro.discordbot.securityservice.util.ApiUrlBuilder;
import com.dimetro.discordbot.securityservice.util.RestServiceClient;

@Service
public class SongAudioService extends RestServiceClient {
    
    @Value("${microservices.music.url}")
    protected String MUSIC_SERVICE_URL;

    @Autowired
    public SongAudioService(RestTemplate restTemplate) {
        super(restTemplate);
    }

    public ResponseEntity<?> getAudioFileById(UUID songId, String youtubeId, String spotifyId) {

        String url = ApiUrlBuilder.from(MUSIC_SERVICE_URL + Endpoints.AUDIO_ID)
                                .addParam("songId", songId)
                                .addParam("youtubeId", youtubeId)
                                .addParam("spotifyId", spotifyId)
                                .build();

        return sendFileRequest(HttpMethod.GET, url);
    }

    public ResponseEntity<?> getAudioFileByTitle(String title, String platform) {

        String url = ApiUrlBuilder.from(MUSIC_SERVICE_URL + Endpoints.AUDIO_TITLE)
                                .addParam("platform", platform)
                                .addParam("title", title)
                                .build();

        return sendFileRequest(HttpMethod.GET, url);
    }

    public ResponseEntity<?> downloadSongById(Object requestBody) {

        String url = ApiUrlBuilder.from(MUSIC_SERVICE_URL + Endpoints.DOWNLOAD_ID).build();
        
        return sendRequest(HttpMethod.POST, url, requestBody);
    }

    public ResponseEntity<?> downloadSongByTitle(Object requestBody) {

        String url = ApiUrlBuilder.from(MUSIC_SERVICE_URL + Endpoints.DOWNLOAD_TITLE).build();
        
        return sendRequest(HttpMethod.POST, url, requestBody);
    }

    public ResponseEntity<?> downloadSongsByPlaylist(Object requestBody) {

        String url = ApiUrlBuilder.from(MUSIC_SERVICE_URL + Endpoints.DOWNLOAD_PLAYLIST).build();
        
        return sendRequest(HttpMethod.POST, url, requestBody);
    }

    public ResponseEntity<?> getDownload(UUID downloadId) {

        String url = ApiUrlBuilder.from(MUSIC_SERVICE_URL + Endpoints.DOWNLOAD)
                                .addParam("downloadId", downloadId)
                                .build();
        
        return sendRequest(HttpMethod.GET, url);
    }

}
