package com.dimetro.discordbot.musicservice.playlist.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.dimetro.discordbot.musicservice.playlist.dto.request.playlist.CreatePlaylistRequestDTO;
import com.dimetro.discordbot.musicservice.playlist.dto.request.song.*;
import com.dimetro.discordbot.musicservice.playlist.dto.response.playlist.PlaylistResponseDTO;
import com.dimetro.discordbot.musicservice.playlist.service.playlist.PlaylistManagementService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/playlist")
public class PlaylistController {
    
    private final PlaylistManagementService playlistManagementService;

    @Autowired
    public PlaylistController(PlaylistManagementService playlistManagementService) {
        this.playlistManagementService = playlistManagementService;
    }

    @GetMapping("/guild")
    public ResponseEntity<List<PlaylistResponseDTO>> getPlaylistsByGuildDiscordId(
        @RequestParam String guildDiscordId,
        @RequestParam UUID botId
    ) {
        return ResponseEntity.ok(playlistManagementService.getPlaylistsByGuildDiscordId(guildDiscordId, botId));
    }

    @GetMapping
    public ResponseEntity<PlaylistResponseDTO> getPlaylist(
        @RequestParam(required = false) UUID playlistId,
        @RequestParam UUID botId,
        @RequestParam(required = false) String title,
        @RequestParam(required = false) String guildDiscordId
    ) {
        PlaylistResponseDTO responseDTO;
        if (playlistId != null) {
            responseDTO = playlistManagementService.getPlaylistById(playlistId, botId);
        } else if (title != null && guildDiscordId != null) {
            responseDTO = playlistManagementService.getPlaylistByTitle(title, botId, guildDiscordId);
        } else {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping
    public ResponseEntity<PlaylistResponseDTO> createPlaylist(
        @RequestBody @Valid CreatePlaylistRequestDTO requestDTO
    ) {
        PlaylistResponseDTO responseDTO = playlistManagementService.createPlaylist(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/song/id")
    public ResponseEntity<PlaylistResponseDTO> addSongToPlaylistById(
        @RequestBody @Valid AddSongsToPlaylistByIdRequestDTO requestDTO
    ) {
        PlaylistResponseDTO responseDTO = playlistManagementService.addSongsToPlaylistById(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/song/title")
    public ResponseEntity<PlaylistResponseDTO> addSongToPlaylistByTitle(
        @RequestBody @Valid AddSongsToPlaylistByTitleRequestDTO requestDTO
    ) {
        PlaylistResponseDTO responseDTO = playlistManagementService.addSongsToPlaylistByTitle(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/song")
    public ResponseEntity<PlaylistResponseDTO> removeSongFromPlaylist(
        @RequestParam UUID playlistId,
        @RequestParam UUID botId,
        @RequestParam String requesterDiscordId,
        @RequestParam(required = false) UUID songId,
        @RequestParam(required = false) Integer position
    ) {

        if (songId == null && position == null) {
            return ResponseEntity.badRequest().build();
        }

        PlaylistResponseDTO responseDTO = playlistManagementService
        .removeSongFromPlaylist(playlistId, botId, requesterDiscordId, songId, position);
        
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePlaylist(
        @RequestParam UUID playlistId, 
        @RequestParam String requesterDiscordId,
        @RequestParam UUID botId
    ) {
        playlistManagementService.deletePlaylist(playlistId, requesterDiscordId, botId);
        return ResponseEntity.ok().build();
    }

}
