package com.dimetro.discordbot.musicservice.song.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.dimetro.discordbot.musicservice.core.entity.SongPlatform;
import com.dimetro.discordbot.musicservice.song.dto.request.download.*;
import com.dimetro.discordbot.musicservice.song.dto.response.download.DownloadResponseDTO;
import com.dimetro.discordbot.musicservice.song.service.audio.SongAudioService;
import com.dimetro.discordbot.musicservice.song.service.download.SongDownloadService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/audio")
public class SongAudioController {
    
    private final SongDownloadService downloadService;
    private final SongAudioService audioService;

    @Autowired
    public SongAudioController(SongDownloadService downloadService, SongAudioService audioService) {
        this.downloadService = downloadService;
        this.audioService = audioService;
    }

    private ResponseEntity<InputStreamResource> wrapAudioFile(File audioFile) {
        try {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(audioFile));
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + audioFile.getName());
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(audioFile.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (FileNotFoundException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/id")
    public ResponseEntity<InputStreamResource> getAudioFileById(
        @RequestParam(required = false) UUID songId,
        @RequestParam(required = false) String youtubeId,
        @RequestParam(required = false) String spotifyId
    ) {
        
        File audioFile;
        
        if (songId != null) {
            audioFile = audioService.getAudioFileBySongId(songId);
        } else if (youtubeId != null && !youtubeId.isEmpty()) {
            audioFile = audioService.getAudioFileByExternalId(SongPlatform.YOUTUBE, youtubeId);
        } else if (spotifyId != null && !spotifyId.isEmpty()) {
            audioFile = audioService.getAudioFileByExternalId(SongPlatform.SPOTIFY, spotifyId);
        } else {
            return ResponseEntity.badRequest().build();
        }

        return wrapAudioFile(audioFile);
    }

    @GetMapping("/title")
    public ResponseEntity<InputStreamResource> getAudioFileByTitle(
        @RequestParam String title,
        @RequestParam SongPlatform platform
    ) {
        File audioFile = audioService.getAudioFileByTitle(platform, title);
        return wrapAudioFile(audioFile);
    }

    @PostMapping("/download/id")
    public ResponseEntity<DownloadResponseDTO> downloadSongById(
        @RequestBody @Valid DownloadAudioByIdRequestDTO requestDTO
    ) {
        DownloadResponseDTO responseDTO = downloadService.downloadSongById(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/download/title")
    public ResponseEntity<DownloadResponseDTO> downloadSongByTitle(
        @RequestBody @Valid DownloadAudioByTitleRequestDTO requestDTO
    ) {
        DownloadResponseDTO responseDTO = downloadService.downloadSongByTitle(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }
    
    @GetMapping("/download")
    public ResponseEntity<DownloadResponseDTO> getDownloadStatus(
        @RequestParam UUID downloadId
    ) {
        DownloadResponseDTO dto = downloadService.getDownload(downloadId);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/download/playlist")
    public ResponseEntity<List<DownloadResponseDTO>> downloadSongsByPlaylist(
        @RequestBody @Valid DownloadAudioByPlaylistIdDTO requestDTO
    ) {
        List<DownloadResponseDTO> responseDTOs = downloadService.downloadSongsByPlaylist(requestDTO);
        return ResponseEntity.ok(responseDTOs);
    }

}
