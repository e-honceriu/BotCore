package com.dimetro.discordbot.musicservice.song.dto.request.download;

import com.dimetro.discordbot.musicservice.core.entity.SongPlatform;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DownloadAudioByTitleRequestDTO {
    
    @NotBlank(message="title must be provided")
    private String title;

    private SongPlatform platform;

}
