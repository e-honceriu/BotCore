package com.dimetro.discordbot.securityservice.music.song.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dimetro.discordbot.securityservice.music.song.service.SongEngagementService;

@RestController
@RequestMapping("/api/music/engagement")
public class SongEngagementController {
    
    private final SongEngagementService engagementService;

    @Autowired
    public SongEngagementController(SongEngagementService engagementService) {
        this.engagementService = engagementService;
    }

    @PostMapping("/listener")
    public ResponseEntity<?> addListeners(@RequestHeader(name = "X-API-KEY", required = true) String apiKey, @RequestBody Object requestBody) {
        return engagementService.addListeners(apiKey, requestBody);
    }

    @PostMapping("/reaction")
    public ResponseEntity<?> addReaction(@RequestHeader(name = "X-API-KEY", required = true) String apiKey, @RequestBody Object requestBody) {
        return engagementService.addReaction(apiKey, requestBody);
    }

    @GetMapping("/reaction/song")
    public ResponseEntity<?> getSongReaction(@RequestHeader(name = "X-API-KEY", required = true) String apiKey,
                                             @RequestParam(value = "songId", required=true) UUID songId,
                                             @RequestParam(value = "guildDiscordId", required=true) String guildDiscordId) {
        return engagementService.getSongReaction(apiKey, songId, guildDiscordId);
    }

    @PostMapping("/stream") 
    public ResponseEntity<?> addStream(@RequestHeader(name = "X-API-KEY", required = true) String apiKey, @RequestBody Object requestBody) {
        return engagementService.addStream(apiKey, requestBody);
    }

    @GetMapping("/song")
    public ResponseEntity<?> getSongEngagement(@RequestHeader(name = "X-API-KEY", required = true) String apiKey,
                                               @RequestParam(value = "songId", required=true) UUID songId,
                                               @RequestParam(value = "guildDiscordId", required=true) String guildDiscordId) {
        return engagementService.getSongEngagement(apiKey, songId, guildDiscordId);
    }
    
}
