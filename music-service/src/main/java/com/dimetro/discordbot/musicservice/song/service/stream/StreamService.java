package com.dimetro.discordbot.musicservice.song.service.stream;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimetro.discordbot.musicservice.song.entity.Song;
import com.dimetro.discordbot.musicservice.song.entity.Stream;
import com.dimetro.discordbot.musicservice.song.repository.StreamRepository;
import com.dimetro.discordbot.musicservice.song.service.stream.exception.StreamRequestNotFoundException;

@Service
public class StreamService {
    
    private final StreamRepository repository;

    @Autowired
    public StreamService(StreamRepository repository) {
        this.repository = repository;
    }

    public Stream getStreamByIdOrThrow(UUID streamRequestId) {
        
        Optional<Stream> dbStreamRequest = repository.findById(streamRequestId);

        if (dbStreamRequest.isEmpty()) {
            throw new StreamRequestNotFoundException(streamRequestId);
        }

        return dbStreamRequest.get();
    }

    public Stream saveStream(Stream streamRequest) {
        return repository.save(streamRequest);
    }

    public long getStreamsCount(Song song, UUID botId, String guildDiscordId) {
        return repository.countBySongAndBotIdAndGuildDiscordId(song, botId, guildDiscordId);
    } 


}
