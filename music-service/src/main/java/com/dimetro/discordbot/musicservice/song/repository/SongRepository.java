package com.dimetro.discordbot.musicservice.song.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dimetro.discordbot.musicservice.song.entity.Song;
import com.dimetro.discordbot.musicservice.core.entity.SongPlatform;
import com.dimetro.discordbot.musicservice.song.entity.ExternalId;

public interface SongRepository extends JpaRepository<Song, UUID> {
    
    Optional<Song> findByExternalId(ExternalId externalId);

    @Query("SELECT s FROM Song s JOIN s.externalId e WHERE e.externalId = :externalId AND e.platform = :platform")
    Optional<Song> findByExternalIdAndPlatform(
        @Param("externalId") String externalId, 
        @Param("platform") SongPlatform platform
    );

}
