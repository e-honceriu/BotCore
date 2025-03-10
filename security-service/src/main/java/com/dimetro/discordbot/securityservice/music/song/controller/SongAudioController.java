package com.dimetro.discordbot.securityservice.music.song.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dimetro.discordbot.securityservice.music.song.service.SongAudioService;


@RestController
@RequestMapping("/api/music/audio")
public class SongAudioController {
    
    private final SongAudioService audioService;

    @Autowired
    public SongAudioController(SongAudioService audioService) {
        this.audioService = audioService;
    }

    @GetMapping("/id")
    public ResponseEntity<?> getAudioFileById(@RequestParam( value = "songId", required = false) UUID songId,
                                              @RequestParam( value = "youtubeId", required = false) String youtubeId,
                                              @RequestParam( value = "spotifyId", required = false) String spotifyId) {
        return audioService.getAudioFileById(songId, youtubeId, spotifyId);
    }

    @GetMapping("/title")
    public ResponseEntity<?> getAudioFileByTitle(@RequestParam(value = "title", required = true) String title,
                                                 @RequestParam(value = "platform", required = false) String platform) {
        return audioService.getAudioFileByTitle(title, platform);
    }

    @PostMapping("/download/id")
    public ResponseEntity<?> downloadSongById(@RequestBody Object requestBody) {
        return audioService.downloadSongById(requestBody);
    }

    @PostMapping("/download/title")
    public ResponseEntity<?> downloadSongByTitle(@RequestBody Object requestBody) {
        return audioService.downloadSongByTitle(requestBody);
    }
    
    @GetMapping("/download")
    public ResponseEntity<?> getDownload(@RequestParam(value = "downloadId", required = true) UUID downloadId) {
        return audioService.getDownload(downloadId);
    }

    @PostMapping("/download/playlist")
    public ResponseEntity<?> downloadSongsByPlaylist(@RequestBody Object requestBody) {
        return audioService.downloadSongsByPlaylist(requestBody);
    }
}   
