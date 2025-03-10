package com.dimetro.discordbot.musicservice.song.entity;

public enum ReactionType {
    
    LIKE("LIKE"),
    DISLIKE("DISLIKE");

    private final String label;

    ReactionType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
