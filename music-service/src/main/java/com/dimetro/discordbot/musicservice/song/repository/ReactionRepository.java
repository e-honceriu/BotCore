package com.dimetro.discordbot.musicservice.song.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dimetro.discordbot.musicservice.song.entity.Song;
import com.dimetro.discordbot.musicservice.song.entity.Reaction;
import com.dimetro.discordbot.musicservice.song.entity.ReactionType;

public interface ReactionRepository extends JpaRepository<Reaction, UUID> {
    
    Optional<Reaction> findBySongAndBotIdAndGuildDiscordId(Song song, UUID botId, String guildDiscordId);

    @Query("SELECT COUNT(r) FROM Reaction r WHERE r.song = :song AND r.botId = :botId AND r.guildDiscordId = :guildDiscordId AND r.type = :type")
    long countBySongAndBotIdAndGuildDiscordIdAndType(
        @Param("song") Song song, 
        @Param("botId") UUID botId, 
        @Param("guildDiscordId") String guildDiscordId, 
        @Param("type") ReactionType type
    );

}
