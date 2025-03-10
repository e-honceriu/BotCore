package com.dimetro.discordbot.musicservice.song.service.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimetro.discordbot.musicservice.song.dto.request.listener.AddListenersRequestDTO;
import com.dimetro.discordbot.musicservice.song.dto.response.listener.AddListenersResponseDTO;
import com.dimetro.discordbot.musicservice.song.entity.Listener;
import com.dimetro.discordbot.musicservice.song.entity.Stream;
import com.dimetro.discordbot.musicservice.song.service.stream.StreamService;

@Service
public class ListenerMangementService {
    
    private final ListenerService listenerService;
    private final StreamService streamService;

    @Autowired
    public ListenerMangementService(ListenerService listenerService, StreamService streamService) {
        this.listenerService = listenerService;
        this.streamService = streamService;
    }

    public AddListenersResponseDTO addListeners(AddListenersRequestDTO requestDTO) {
        
        Stream stream = streamService.getStreamByIdOrThrow(requestDTO.getStreamId());

        List<String> listenerIds = new ArrayList<>();

        for (String reqListenerDiscordId : requestDTO.getListenersDiscordIds()) {

            Optional<Listener> dbListener = listenerService.getListenerByStreamAndDiscordIdSafe(stream, reqListenerDiscordId);

            if (dbListener.isEmpty()) {
                Listener listener = new Listener();
                listener.setListenerDiscordId(reqListenerDiscordId);
                listener.setStream(stream);
                listenerService.saveListen(listener);
            }
            listenerIds.add(reqListenerDiscordId);

        }

        return AddListenersResponseDTO.build(stream.getId(), listenerIds);
    }

}
