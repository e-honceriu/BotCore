package com.dimetro.discordbot.musicservice.song.service.song;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dimetro.discordbot.musicservice.song.entity.Song;
import com.dimetro.discordbot.musicservice.core.entity.SongPlatform;
import com.dimetro.discordbot.musicservice.handler.entity.SongSearch;
import com.dimetro.discordbot.musicservice.handler.handlers.SongHandlerAdapter;
import com.dimetro.discordbot.musicservice.song.entity.ExternalId;
import com.dimetro.discordbot.musicservice.song.repository.SongRepository;
import com.dimetro.discordbot.musicservice.song.service.song.exception.*;


@Service
public class SongService {
    
    private final SongRepository repository;
    private final SongHandlerAdapter platformAdapter;
    private final SongPlatform defaultPlatform;

    @Autowired
    public SongService(SongRepository repository, SongHandlerAdapter platformAdapter, 
                       @Value("${song.default-platform}")String defaultPlatform) {
        this.repository = repository;
        this.platformAdapter = platformAdapter;
        this.defaultPlatform = SongPlatform.valueOf(defaultPlatform);
    }

    public Song getSongByIdOrThrow(UUID id) {
    
        Optional<Song> dbSong = repository.findById(id);
        
        if (dbSong.isPresent()) {
            return dbSong.get();
        }

        throw new SongNotFoundException(id);
    }

    public Optional<Song> getSongByExternalIdSafe(ExternalId id) {
        return repository.findByExternalId(id);
    } 

    public Song getSongByExternalIdOrThrow(ExternalId id) {
        
        Optional<Song> dbSong = repository.findByExternalId(id);

        if (dbSong.isPresent()) {
            return dbSong.get();
        }

        throw new SongNotFoundException(id);
    }

    public Song saveSong(Song song) {
        return repository.save(song);
    }

    private Song saveSong(SongSearch search) {

        Optional<Song> dbSong = repository.findByExternalIdAndPlatform(search.getId(), search.getPlatform());

        if (dbSong.isPresent()) {
            throw new SongAlreadyExistsException(search.getPlatform(), search.getId());
        }

        Song song = new Song();
        ExternalId extId = new ExternalId();

        extId.setExternalId(search.getId());
        extId.setPlatform(search.getPlatform());
        extId.setSong(song);

        song.setTitle(search.getTitle());
        song.setThumbnailUrl(search.getThumbnailUrl());
        song.setExternalId(extId);

        return repository.save(song);
    }

    public Song searchSongByPlatformAndExternalId(SongPlatform platform, String externalId) {

        if (platform == null) {
            platform = defaultPlatform;
        }
        
        Optional<Song> dbSong = repository.findByExternalIdAndPlatform(externalId, platform);

        if (dbSong.isPresent()) {
            return dbSong.get();
        }
        
        SongSearch search = platformAdapter.searchSongById(platform, externalId);
        
        return saveSong(search);
    }

    public Song searchSongByPlatformAndTitle(SongPlatform platform, String title) {

        if (platform == null) {
            platform = defaultPlatform;
        }

        SongSearch search = platformAdapter.searchSongByTitle(platform, title);

        Optional<Song> dbSong = repository.findByExternalIdAndPlatform(search.getId(), platform);

        if (dbSong.isPresent()) {
            return dbSong.get();
        }
        
        return saveSong(search);
    }

    private List<Song> mapSearchesToSongs(List<SongSearch> searches) {

        List<Song> songs = new ArrayList<>();

        for (SongSearch search : searches) {

            Optional<Song> dbSong = repository.findByExternalIdAndPlatform(search.getId(), search.getPlatform());

            if (dbSong.isPresent()) {
                songs.add(dbSong.get());
            } else {
                songs.add(saveSong(search));
            }
            
        }

        return songs;
    }

    public List<Song> searchSongsByExternalPlaylistId(SongPlatform platform, String externalPlaylistId) {

        if (platform == null) {
            platform = defaultPlatform;
        }

        List<SongSearch> searches = platformAdapter.searchSongsByPlaylistId(platform, externalPlaylistId);

        return mapSearchesToSongs(searches);
    }

    public List<Song> searchSongsByAlbumId(SongPlatform platform, String albumId) {

        if (platform == null) {
            platform = defaultPlatform;
        }

        List<SongSearch> searches = platformAdapter.searchSongsByAlbumId(platform, albumId);

        return mapSearchesToSongs(searches);
    }

    public Song downloadSongAudioFile(Song song) {
        
        ExternalId externalId = song.getExternalId();

        if (externalId == null) {
            throw new NoExternalIdProvidedException(song);
        }

        String filePath = platformAdapter.downloadSongById(
                                    externalId.getPlatform(), 
                                    externalId.getExternalId()
                            );
        song.setAudioFilePath(filePath);

        return saveSong(song);
    }

}
