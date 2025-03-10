package com.dimetro.discordbot.musicservice.song.service.reaction;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimetro.discordbot.musicservice.song.entity.Song;
import com.dimetro.discordbot.musicservice.song.dto.request.reaction.AddReactionRequestDTO;
import com.dimetro.discordbot.musicservice.song.dto.response.reaction.ReactionResponseDTO;
import com.dimetro.discordbot.musicservice.song.dto.response.reaction.SongReactionResponseDTO;
import com.dimetro.discordbot.musicservice.song.entity.Reaction;
import com.dimetro.discordbot.musicservice.song.entity.ReactionType;
import com.dimetro.discordbot.musicservice.song.service.song.SongService;

@Service
public class ReactionManagementService {
    
    private final ReactionService reactionService;
    private final SongService songService;

    @Autowired
    public ReactionManagementService(ReactionService reactionService, SongService songService) {
        this.reactionService = reactionService;
        this.songService = songService;
    }

    public ReactionResponseDTO addReaction(AddReactionRequestDTO requestDTO) {

        Song song = songService.getSongByIdOrThrow(requestDTO.getSongId());

        Optional<Reaction> dbReaction = reactionService
                                            .getReactionBySongAndBotAndGuildSafe(
                                                            song, 
                                                            requestDTO.getBotId(), 
                                                            requestDTO.getGuildDiscordId()
                                                        );
        Reaction reaction;

        if (dbReaction.isPresent()) {

            reaction = dbReaction.get();
            reaction.setType(requestDTO.getType());

        } else {

            reaction = new Reaction();
            reaction.setBotId(requestDTO.getBotId());
            reaction.setGuildDiscordId(requestDTO.getGuildDiscordId());
            reaction.setUserDiscordId(requestDTO.getUserDiscordId());
            reaction.setType(requestDTO.getType());
            reaction.setSong(song);
            
        }

        return ReactionResponseDTO.build(reactionService.saveReaction(reaction));
    }

    public SongReactionResponseDTO getSongReaction(UUID songId, UUID botId, String guildDiscordId) {

        Song song = songService.getSongByIdOrThrow(songId);

        long likes = reactionService.getReactionCount(song, botId, guildDiscordId, ReactionType.LIKE);
        long dislikes = reactionService.getReactionCount(song, botId, guildDiscordId, ReactionType.DISLIKE);

        return SongReactionResponseDTO.build(song, likes, dislikes);
    }

}
