package com.dimetro.discordbot.musicservice.song.dto.response.listener;

import java.util.List;
import java.util.UUID;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddListenersResponseDTO {
    
    private UUID streamId;
    private List<String> listenersDiscordIds;

    public static AddListenersResponseDTO build(UUID streamId, List<String> listenersDiscordIds) {
        return AddListenersResponseDTO.builder()
                                .streamId(streamId)
                                .listenersDiscordIds(listenersDiscordIds)
                                .build();
    }

}
