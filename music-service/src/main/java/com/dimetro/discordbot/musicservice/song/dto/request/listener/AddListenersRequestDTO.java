package com.dimetro.discordbot.musicservice.song.dto.request.listener;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddListenersRequestDTO {

    @NotNull(message = "Song ID must be provided")
    private UUID streamId;
    @NotNull(message = "Listeners Discord IDs must be provided")
    private List<String> listenersDiscordIds;

}
