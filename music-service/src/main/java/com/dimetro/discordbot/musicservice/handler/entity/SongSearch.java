package com.dimetro.discordbot.musicservice.handler.entity;

import com.dimetro.discordbot.musicservice.core.entity.SongPlatform;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SongSearch {
    
    private String id;
    private SongPlatform platform;
    private String title;
    private String thumbnailUrl;

}
