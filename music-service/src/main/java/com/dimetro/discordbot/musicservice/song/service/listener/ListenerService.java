package com.dimetro.discordbot.musicservice.song.service.listener;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.dimetro.discordbot.musicservice.song.entity.Song;
import com.dimetro.discordbot.musicservice.song.entity.Listener;
import com.dimetro.discordbot.musicservice.song.entity.Stream;
import com.dimetro.discordbot.musicservice.song.repository.ListenerRepository;

@Service
public class ListenerService {
    
    private final ListenerRepository repository;

    public ListenerService(ListenerRepository repository) {
        this.repository = repository;
    }

    public Listener saveListen(Listener listen) {
        return repository.save(listen);
    }

    public Optional<Listener> getListenerByStreamAndDiscordIdSafe(Stream stream, String listenerDiscordId) {
        return repository.findByStreamAndListenerDiscordId(stream, listenerDiscordId);
    }

    public long getListenersCount(Song song, UUID botId, String guildDiscordId) {
        return repository.countBySongAndBotIdAndGuildDiscordId(song, botId, guildDiscordId);
    } 

}
