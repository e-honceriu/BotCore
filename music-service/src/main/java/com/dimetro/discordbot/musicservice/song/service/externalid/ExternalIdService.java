package com.dimetro.discordbot.musicservice.song.service.externalid;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimetro.discordbot.musicservice.core.entity.SongPlatform;
import com.dimetro.discordbot.musicservice.song.entity.ExternalId;
import com.dimetro.discordbot.musicservice.song.repository.ExternalIdRepository;
import com.dimetro.discordbot.musicservice.song.service.externalid.exception.ExternalIdNotFoundException;

@Service
public class ExternalIdService {
    
    private final ExternalIdRepository repository;

    @Autowired
    public ExternalIdService(ExternalIdRepository repository) {
        this.repository = repository;
    }

    public Optional<ExternalId> getExternalIdByPlatformAndIdSafe(SongPlatform platform, String id) {
        return repository.findByPlatformAndExternalId(platform, id);
    }

    public ExternalId getExternalIdByPlatformAndIdOrThrow(SongPlatform platform, String id) {
        
        Optional<ExternalId> dbExternalId = repository.findByPlatformAndExternalId(platform, id);

        if (dbExternalId.isPresent()) {
            return dbExternalId.get();
        }

        throw new ExternalIdNotFoundException(platform, id);
    }

    public ExternalId saveExternalId(SongPlatform platform, String id) {

        ExternalId extId = new ExternalId();

        extId.setPlatform(platform);
        extId.setExternalId(id);
        
        return repository.save(extId);
    }

}
