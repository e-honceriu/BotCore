package com.dimetro.discordbot.musicservice.song.service.audio.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.musicservice.song.entity.Song;

public class AudioFileNotFoundException extends AudioServiceException {
    
    public AudioFileNotFoundException(Song song) {
        super(
            HttpStatus.NOT_FOUND,
            String.format("Audio file for song (ID=%s) not found.", song.getId()),
            "Could not find song audio file!" 
        );
    }

}
