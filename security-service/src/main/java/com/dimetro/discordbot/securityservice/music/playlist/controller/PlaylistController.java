package com.dimetro.discordbot.securityservice.music.playlist.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.dimetro.discordbot.securityservice.music.playlist.service.PlaylistService;

@Controller
@RequestMapping("/api/music/playlist")
public class PlaylistController {
    
    private final PlaylistService playlistService;

    @Autowired
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping("/guild")
    public ResponseEntity<?> getPlaylistsByGuildDiscordId(
        @RequestHeader(name = "X-API-KEY", required = true) String apiKey,
        @RequestParam String guildDiscordId
    ) {
        return playlistService.getPlaylistsByGuildDiscordId(apiKey, guildDiscordId);
    }

    @GetMapping
    public ResponseEntity<?> getPlaylist(
        @RequestHeader(name = "X-API-KEY") String apiKey,
        @RequestParam(required = false) UUID playlistId,
        @RequestParam(required = false) String title,
        @RequestParam(required = false) String guildDiscordId
    ) {
        return playlistService.getPlaylist(apiKey, playlistId, title, guildDiscordId);
    }

    @PostMapping
    public ResponseEntity<?> createPlaylist(
        @RequestHeader(name = "X-API-KEY", required = true) String apiKey, 
        @RequestBody Object requestBody
    ) {
        return playlistService.createPlaylist(requestBody, apiKey);
    }

    @PostMapping("/song/id")
    public ResponseEntity<?> addSongsToPlaylistById(
        @RequestHeader(name = "X-API-KEY", required = true) String apiKey, 
        @RequestBody Object requestBody
    ) {
        return playlistService.addSongsToPlaylistById(requestBody, apiKey);
    }

    @PostMapping("/song/title")
    public ResponseEntity<?> addSongToPlaylistByTitle(
        @RequestHeader(name = "X-API-KEY", required = true) String apiKey, 
        @RequestBody Object requestBody
    ) {
        return playlistService.addSongsToPlaylistByTitle(requestBody, apiKey);
    }

    @DeleteMapping("/song")
    public ResponseEntity<?> removeSongFromPlaylist(
        @RequestHeader(name = "X-API-KEY", required = true) String apiKey, 
        @RequestParam UUID playlistId,
        @RequestParam String requesterDiscordId,
        @RequestParam(required = false) UUID songId,
        @RequestParam(required = false) Integer position
    ) {
        return playlistService.removeSongFromPlaylist(playlistId, requesterDiscordId, songId, position, apiKey);
    }

    @DeleteMapping
    public ResponseEntity<?> deletePlaylist(
        @RequestHeader(name = "X-API-KEY", required = true) String apiKey, 
        @RequestParam UUID playlistId, 
        @RequestParam String requesterDiscordId
    ) {
        return playlistService.deletePlaylist(playlistId, requesterDiscordId, apiKey);
    }

}
