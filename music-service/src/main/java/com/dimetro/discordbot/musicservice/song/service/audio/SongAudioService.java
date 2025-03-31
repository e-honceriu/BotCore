package com.dimetro.discordbot.musicservice.song.service.audio;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimetro.discordbot.musicservice.core.entity.SongPlatform;
import com.dimetro.discordbot.musicservice.song.entity.Song;
import com.dimetro.discordbot.musicservice.song.service.audio.exception.AudioFileNotFoundException;
import com.dimetro.discordbot.musicservice.song.service.song.SongService;

@Service
public class SongAudioService {
    
    private final SongService songService;

    @Autowired
    public SongAudioService(SongService songService) {
        this.songService = songService;
    }
 
    private File getAudioFileBySong(Song song) {

        if (song.getAudioFilePath() == null) {
            throw new AudioFileNotFoundException(song);
        }

        File audioFile = new File(song.getAudioFilePath());

        if (!audioFile.exists()) {
            song.setAudioFilePath(null);
            songService.saveSong(song);
            throw new AudioFileNotFoundException(song);
        }
        return audioFile;
    }

    public File getAudioFileBySongId(UUID songId) {

        Song song = songService.getSongByIdOrThrow(songId);

        return getAudioFileBySong(song);
    }

    public File getAudioFileByExternalId(SongPlatform platform, String id) {
        
        Song song = songService.searchSongByPlatformAndExternalId(platform, id);

        return getAudioFileBySong(song);
    }

    public File getAudioFileByTitle(SongPlatform platform, String title) {

        Song song = songService.searchSongByPlatformAndTitle(platform, title);

        return getAudioFileBySong(song);
    }

}
