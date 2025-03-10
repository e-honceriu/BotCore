package com.dimetro.discordbot.musicservice.song.service.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimetro.discordbot.musicservice.song.entity.Song;
import com.dimetro.discordbot.musicservice.song.dto.request.stream.AddStreamRequestDTO;
import com.dimetro.discordbot.musicservice.song.dto.response.stream.StreamResponseDTO;
import com.dimetro.discordbot.musicservice.song.entity.Stream;
import com.dimetro.discordbot.musicservice.song.service.song.SongService;

@Service
public class StreamManagementService {

    private final StreamService streamService;
    private final SongService songService;

    @Autowired
    public StreamManagementService(StreamService streamService, SongService songService) {
        this.streamService = streamService;
        this.songService = songService;
    }

    public StreamResponseDTO addStream(AddStreamRequestDTO requestDTO) {
        
        Song song = songService.getSongByIdOrThrow(requestDTO.getSongId());

        Stream stream = new Stream();

        stream.setSong(song);
        stream.setBotId(requestDTO.getBotId());
        stream.setGuildDiscordId(requestDTO.getGuildDiscordId());
        stream.setRequesterDiscordId(requestDTO.getRequesterDiscordId());
        stream.setRequestedAt(requestDTO.getRequestedAt());

        return StreamResponseDTO.build(streamService.saveStream(stream));
    }

}