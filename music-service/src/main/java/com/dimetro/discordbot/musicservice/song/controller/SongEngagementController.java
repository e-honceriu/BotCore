package com.dimetro.discordbot.musicservice.song.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dimetro.discordbot.musicservice.song.dto.request.listener.AddListenersRequestDTO;
import com.dimetro.discordbot.musicservice.song.dto.request.reaction.AddReactionRequestDTO;
import com.dimetro.discordbot.musicservice.song.dto.request.stream.AddStreamRequestDTO;
import com.dimetro.discordbot.musicservice.song.dto.response.engagement.SongEngagementResponseDTO;
import com.dimetro.discordbot.musicservice.song.dto.response.listener.AddListenersResponseDTO;
import com.dimetro.discordbot.musicservice.song.dto.response.reaction.ReactionResponseDTO;
import com.dimetro.discordbot.musicservice.song.dto.response.reaction.SongReactionResponseDTO;
import com.dimetro.discordbot.musicservice.song.dto.response.stream.StreamResponseDTO;
import com.dimetro.discordbot.musicservice.song.service.engagement.EngagementService;
import com.dimetro.discordbot.musicservice.song.service.listener.ListenerMangementService;
import com.dimetro.discordbot.musicservice.song.service.reaction.ReactionManagementService;
import com.dimetro.discordbot.musicservice.song.service.stream.StreamManagementService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/engagement")
public class SongEngagementController {
    
    private final ListenerMangementService listenerService;
    private final ReactionManagementService reactionService;
    private final StreamManagementService streamService;
    private final EngagementService engagementService;

    @Autowired
    public SongEngagementController(ListenerMangementService listenerService, ReactionManagementService reactionService,
                                    StreamManagementService streamService, EngagementService engagementService) {
        this.listenerService = listenerService;
        this.reactionService = reactionService;
        this.streamService = streamService;
        this.engagementService = engagementService;
    }

    @PostMapping("/listener")
    public ResponseEntity<AddListenersResponseDTO> addListeners(
        @RequestBody @Valid AddListenersRequestDTO requestDTO
    ) {
        AddListenersResponseDTO responseDTO = listenerService.addListeners(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/reaction")
    public ResponseEntity<ReactionResponseDTO> addReaction(
        @RequestBody @Valid AddReactionRequestDTO requestDTO
    ) {
        ReactionResponseDTO responseDTO = reactionService.addReaction(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/reaction/song")
    public ResponseEntity<SongReactionResponseDTO> getSongReaction(
        @RequestParam UUID songId,
        @RequestParam UUID botId,
        @RequestParam String guildDiscordId
    ) {

        if (guildDiscordId.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        SongReactionResponseDTO responseDTO = reactionService.getSongReaction(songId, botId, guildDiscordId);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/stream")
    public ResponseEntity<StreamResponseDTO> addStream(
        @RequestBody @Valid AddStreamRequestDTO requestDTO
    ) {
        StreamResponseDTO responseDTO = streamService.addStream(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/song")
    public ResponseEntity<SongEngagementResponseDTO> getSongEngagement(
        @RequestParam UUID songId,
        @RequestParam UUID botId,
        @RequestParam String guildDiscordId
    ) {
        SongEngagementResponseDTO responseDTO = engagementService.getEngagement(songId, botId, guildDiscordId);
        return ResponseEntity.ok(responseDTO);
    }

}
