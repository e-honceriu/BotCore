package com.dimetro.discordbot.musicservice.song.service.engagement;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimetro.discordbot.musicservice.song.entity.Song;
import com.dimetro.discordbot.musicservice.song.dto.response.engagement.SongEngagementResponseDTO;
import com.dimetro.discordbot.musicservice.song.entity.ReactionType;
import com.dimetro.discordbot.musicservice.song.service.listener.ListenerService;
import com.dimetro.discordbot.musicservice.song.service.reaction.ReactionService;
import com.dimetro.discordbot.musicservice.song.service.song.SongService;
import com.dimetro.discordbot.musicservice.song.service.stream.StreamService;

@Service
public class EngagementService {
    
    private final ReactionService reactionService;
    private final ListenerService listenerService;
    private final StreamService streamService;
    private final SongService songService;

    @Autowired
    public EngagementService(ReactionService reactionService, ListenerService listenerService, 
                             StreamService streamService, SongService songService) {
        this.reactionService = reactionService;
        this.listenerService = listenerService;
        this.streamService = streamService;
        this.songService = songService;
    }

    public SongEngagementResponseDTO getEngagement(UUID songId, UUID botId, String guildDiscordId) {

        Song song = songService.getSongByIdOrThrow(songId);

        long listenersCount = listenerService.getListenersCount(song, botId, guildDiscordId);
        long streamsCount = streamService.getStreamsCount(song, botId, guildDiscordId);
        long likesCount = reactionService.getReactionCount(song, botId, guildDiscordId, ReactionType.LIKE);
        long dislikesCount = reactionService.getReactionCount(song, botId, guildDiscordId, ReactionType.DISLIKE);

        return SongEngagementResponseDTO.builder()
                                .songId(songId)
                                .listenersCount(listenersCount)
                                .streamsCount(streamsCount)
                                .likesCount(likesCount)
                                .dislikesCount(dislikesCount)
                                .build();
    }

}
