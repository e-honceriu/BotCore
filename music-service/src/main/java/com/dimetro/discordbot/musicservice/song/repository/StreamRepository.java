package com.dimetro.discordbot.musicservice.song.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dimetro.discordbot.musicservice.song.entity.Song;
import com.dimetro.discordbot.musicservice.song.entity.Stream;

public interface StreamRepository extends JpaRepository<Stream, UUID> {

    @Query("SELECT COUNT(s) FROM Stream s WHERE s.song = :song AND s.botId = :botId AND s.guildDiscordId = :guildDiscordId")
    long countBySongAndBotIdAndGuildDiscordId(
        @Param("song") Song song, 
        @Param("botId") UUID botId, 
        @Param("guildDiscordId") String guildDiscordId
    );

}
