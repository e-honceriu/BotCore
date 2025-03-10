package com.dimetro.discordbot.musicservice.playlist.service.playlist;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimetro.discordbot.musicservice.playlist.entity.Playlist;
import com.dimetro.discordbot.musicservice.playlist.repository.PlaylistRepository;
import com.dimetro.discordbot.musicservice.playlist.service.playlist.exception.*;

@Service
public class PlaylistService {
    
    private final PlaylistRepository repository;

    @Autowired
    public PlaylistService(PlaylistRepository repository) {
        this.repository = repository;
    }

    public Playlist getPlaylistOrThrow(UUID playlistId, UUID botId) {

        Optional<Playlist> dbPlaylist = repository.findById(playlistId);

        if (dbPlaylist.isEmpty()) {
            throw new PlaylistNotFoundException(playlistId);
        }

        Playlist playlist = dbPlaylist.get();

        if (!playlist.getBotId().equals(botId)) {
            
        }

        return dbPlaylist.get();
    }

    public Playlist getPlaylistAuthOrThrow(UUID playlistId, String requesterDiscordId, UUID botId) {
        
        Playlist playlist = getPlaylistOrThrow(playlistId, botId);

        if (!playlist.getOwnerDiscordId().equals(requesterDiscordId)) {
            throw new RequesterNotOwnerException(playlist, requesterDiscordId);
        }

        return playlist;
    }

    public List<Playlist> getPlaylistByDiscordGuildId(String discordGuildId, UUID botId) {
        return repository.findByGuildDiscordIdAndBotId(discordGuildId, botId);
    }

    public Playlist getPlaylistOrThrow(String title, UUID botId, String guildDiscordId) {
        
        Optional<Playlist> dbPlaylist = repository.findByTitleAndBotIdAndGuildDiscordId(title, botId, guildDiscordId);

        if (dbPlaylist.isEmpty()) {
            throw new PlaylistNotFoundException(title, botId, guildDiscordId);
        }

        return dbPlaylist.get();
    }

    public Optional<Playlist> getPlaylistSafe(String title, UUID botId, String guildDiscordId) {
        return repository.findByTitleAndBotIdAndGuildDiscordId(title, botId, guildDiscordId);
    }

    public Playlist getPlaylistAuthOrThrow(String title, UUID botId, String guildDiscordId, String requesterDiscordId) {
        
        Playlist playlist = getPlaylistOrThrow(title, botId, guildDiscordId);

        if (!playlist.getOwnerDiscordId().equals(requesterDiscordId)) {
            throw new RequesterNotOwnerException(playlist, requesterDiscordId);
        }

        return playlist;
    }

    public Playlist savePlaylist(Playlist playlist) {
        return repository.save(playlist);
    }

    public void deletePlaylist(UUID playlistId, String requesterDiscordId, UUID botId) {

        Playlist playlist = getPlaylistAuthOrThrow(playlistId, requesterDiscordId, botId);

        repository.delete(playlist);
    }

    public Playlist createPlaylist(String title, String ownerDiscordId, UUID botId, String guildDiscordId) {

        Optional<Playlist> dbPlaylist = getPlaylistSafe(title, botId, guildDiscordId);

        if (dbPlaylist.isPresent()) {
            throw new PlaylistAlreadyExistsException(title, botId, guildDiscordId);
        }

        Playlist playlist = new Playlist();
        playlist.setTitle(title);
        playlist.setOwnerDiscordId(ownerDiscordId);
        playlist.setBotId(botId);
        playlist.setGuildDiscordId(guildDiscordId);

        return repository.save(playlist);
    }

}
