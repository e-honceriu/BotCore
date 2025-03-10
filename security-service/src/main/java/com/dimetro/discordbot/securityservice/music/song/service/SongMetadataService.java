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
public class SongMetadataService extends RestServiceClient {
    
    @Value("${microservices.music.url}")
    protected String MUSIC_SERVICE_URL;

    @Autowired
    public SongMetadataService(RestTemplate restTemplate) {
        super(restTemplate);
    }

    public ResponseEntity<?> getSongById(UUID songId, String youtubeId, String spotifyId) {
        
        String url = ApiUrlBuilder.from(MUSIC_SERVICE_URL + Endpoints.METADATA_ID)
                                .addParam("songId", songId)
                                .addParam("youtubeId", youtubeId)
                                .addParam("spotifyId", spotifyId)
                                .build();
                                
        return sendRequest(HttpMethod.GET, url);
    }

    public ResponseEntity<?> getSongByTitle(String title, String platform) {

        String url = ApiUrlBuilder.from(MUSIC_SERVICE_URL + Endpoints.METADATA_TITLE)
                                .addParam("title", title)
                                .addParam("platform", platform)
                                .build();
                                
        return sendRequest(HttpMethod.GET, url);

    }

    public ResponseEntity<?> getSongByPlaylist(String youtubePlaylistId, String spotifyPlaylistId) {

        String url = ApiUrlBuilder.from(MUSIC_SERVICE_URL + Endpoints.METADATA_PLAYLIST)
                                .addParam("youtubePlaylistId", youtubePlaylistId)
                                .addParam("spotifyPlaylistId", spotifyPlaylistId)
                                .build();
        
        return sendRequest(HttpMethod.GET, url);
    }

    public ResponseEntity<?> getSongsByAlbum(String youtubeAlbumId, String spotifyAlbumId) {

        String url = ApiUrlBuilder.from(MUSIC_SERVICE_URL + Endpoints.METADATA_ALBUM)
                                .addParam("youtubeAlbumId", youtubeAlbumId)
                                .addParam("spotifyAlbumId", spotifyAlbumId)
                                .build();
        
        return sendRequest(HttpMethod.GET, url);
    }


}
