package com.dimetro.discordbot.musicservice.song.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dimetro.discordbot.musicservice.song.entity.Song;
import com.dimetro.discordbot.musicservice.song.entity.Listener;
import com.dimetro.discordbot.musicservice.song.entity.Stream;

public interface ListenerRepository extends JpaRepository<Listener, UUID> {
    
    Optional<Listener> findByStreamAndListenerDiscordId(Stream stream, String listenerDiscordId);

    @Query("SELECT COUNT(l) FROM Listener l WHERE l.stream.song = :song AND l.stream.botId = :botId AND l.stream.guildDiscordId = :guildDiscordId")
    long countBySongAndBotIdAndGuildDiscordId(
        @Param("song") Song song, 
        @Param("botId") UUID botId, 
        @Param("guildDiscordId") String guildDiscordId
    );

}
