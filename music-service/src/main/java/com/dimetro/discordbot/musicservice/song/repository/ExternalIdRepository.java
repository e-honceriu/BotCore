package com.dimetro.discordbot.musicservice.song.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dimetro.discordbot.musicservice.core.entity.SongPlatform;
import com.dimetro.discordbot.musicservice.song.entity.ExternalId;

import java.util.Optional;
import java.util.UUID;

public interface ExternalIdRepository extends JpaRepository<ExternalId, UUID> {

    Optional<ExternalId> findByPlatformAndExternalId(SongPlatform platform, String externalId);
    
}
