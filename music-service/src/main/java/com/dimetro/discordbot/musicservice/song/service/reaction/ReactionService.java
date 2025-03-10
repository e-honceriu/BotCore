package com.dimetro.discordbot.musicservice.song.service.reaction;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimetro.discordbot.musicservice.song.entity.Song;
import com.dimetro.discordbot.musicservice.song.entity.Reaction;
import com.dimetro.discordbot.musicservice.song.entity.ReactionType;
import com.dimetro.discordbot.musicservice.song.repository.ReactionRepository;

@Service
public class ReactionService {
    
    private final ReactionRepository repository;

    @Autowired
    public ReactionService(ReactionRepository repository) {
        this.repository = repository;
    }

    public Optional<Reaction> getReactionBySongAndBotAndGuildSafe(Song song, UUID botId, String guildDiscordId) {
        return repository.findBySongAndBotIdAndGuildDiscordId(song, botId, guildDiscordId);
    }

    public Reaction saveReaction(Reaction reaction) {
        return repository.save(reaction);
    }

    public long getReactionCount(Song song, UUID botId, String guildDiscordId, ReactionType type) {
        return repository.countBySongAndBotIdAndGuildDiscordIdAndType(song, botId, guildDiscordId, type);
    }

}
