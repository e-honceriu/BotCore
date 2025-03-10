package com.dimetro.discordbot.securityservice.music.song.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dimetro.discordbot.securityservice.music.song.service.SongMetadataService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/music/metadata")
public class SongMetadataController {
    
    private final SongMetadataService metadataService;

    @Autowired
    public SongMetadataController(SongMetadataService metadataService) {
        this.metadataService = metadataService;
    }

    @GetMapping("/id")
    public ResponseEntity<?> getSongById(@RequestParam(value = "songId", required = false) UUID id,
                                         @RequestParam(value = "youtubeId", required = false) String youtubeId,
                                         @RequestParam(value = "spotifyId", required = false) String spotifyId) {
        return metadataService.getSongById(id, youtubeId, spotifyId);
    }

    @GetMapping("/title")
    public ResponseEntity<?> getSongByTitle(@RequestParam(value = "title", required = true) String title,
                                            @RequestParam(value = "platform", required = false) String platform) {
        return metadataService.getSongByTitle(title, platform);
    }

    @GetMapping("/playlist")
    public ResponseEntity<?> getSongsByPlaylist(@RequestParam(value = "youtubePlaylistId", required = false) String youtubePlaylistId,
                                                @RequestParam(value = "spotifyPlaylistId", required = false) String spotifyPlaylistId) {
        return metadataService.getSongByPlaylist(youtubePlaylistId, spotifyPlaylistId);
    }

    @GetMapping("/album")
    public ResponseEntity<?> getSongsByAlbum(@RequestParam(value = "youtubeAlbumId", required = false) String youtubeAlbumId,
                                             @RequestParam(value = "spotifyAlbumId", required = false) String spotifyAlbumId) {
        return metadataService.getSongsByAlbum(youtubeAlbumId, spotifyAlbumId);
    }

}
