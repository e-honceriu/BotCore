package com.dimetro.discordbot.musicservice.core.entity;

public enum SongPlatform {
    
    YOUTUBE("YOUTUBE"),
    SPOTIFY("SPOTIFY");

    private final String name;

    SongPlatform(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
