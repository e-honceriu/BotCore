package com.dimetro.discordbot.musicservice.song.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dimetro.discordbot.musicservice.core.entity.SongPlatform;
import com.dimetro.discordbot.musicservice.song.dto.response.song.SongMetadataResponseDTO;
import com.dimetro.discordbot.musicservice.song.service.song.SongMetadataService;

@RestController
@RequestMapping("/api/metadata")
public class SongMetadataController {
    
    private final SongMetadataService metadataService;

    @Autowired
    public SongMetadataController(SongMetadataService metadataService) {
        this.metadataService = metadataService;
    }

    @GetMapping("/id")
    public ResponseEntity<SongMetadataResponseDTO> getSongById(
        @RequestParam(required = false) UUID id,
        @RequestParam(required = false) String youtubeId,
        @RequestParam(required = false) String spotifyId
    ) {

        SongMetadataResponseDTO dto;
        
        if (id != null) {
            dto = metadataService.getSongById(id);
        } else if (youtubeId != null && !youtubeId.isEmpty()) {
            dto = metadataService.getSongByExternalId(SongPlatform.YOUTUBE, youtubeId);
        } else if (spotifyId != null && !spotifyId.isEmpty()) {
            dto = metadataService.getSongByExternalId(SongPlatform.SPOTIFY, spotifyId);
        } else {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/title")
    public ResponseEntity<SongMetadataResponseDTO> getSongByTitle(
        @RequestParam String title,
        @RequestParam(required=false) SongPlatform platform
    ) {

        SongMetadataResponseDTO dto = metadataService.getSongByTitle(platform, title);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/playlist")
    public ResponseEntity<List<SongMetadataResponseDTO>> getSongsByPlaylist(
        @RequestParam(required = false) String youtubePlaylistId,
        @RequestParam(required = false) String spotifyPlaylistId
    ) {
        
        List<SongMetadataResponseDTO> dtos;

        if (youtubePlaylistId != null && !youtubePlaylistId.isEmpty()) {
            dtos = metadataService.getSongsByPlaylistId(SongPlatform.YOUTUBE, youtubePlaylistId);
        } else if (spotifyPlaylistId != null && !spotifyPlaylistId.isEmpty()) {
            dtos = metadataService.getSongsByPlaylistId(SongPlatform.SPOTIFY, spotifyPlaylistId);
        } else {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/album")
    public ResponseEntity<List<SongMetadataResponseDTO>> getSongsByAlbum(
        @RequestParam(required = false) String youtubeAlbumId,
        @RequestParam(required = false) String spotifyAlbumId
    ) {
        
        List<SongMetadataResponseDTO> dtos;

        if (youtubeAlbumId != null && !youtubeAlbumId.isEmpty()) {
            dtos = metadataService.getSongsByAlbumId(SongPlatform.YOUTUBE, youtubeAlbumId);
        } else if (spotifyAlbumId != null && !spotifyAlbumId.isEmpty()) {
            dtos = metadataService.getSongsByAlbumId(SongPlatform.SPOTIFY, spotifyAlbumId);
        } else {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(dtos);
    }

}
