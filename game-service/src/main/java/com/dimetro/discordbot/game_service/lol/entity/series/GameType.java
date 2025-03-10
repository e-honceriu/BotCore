package com.dimetro.discordbot.game_service.lol.entity.series;

import com.fasterxml.jackson.annotation.JsonValue;

public enum GameType {

    RDAM("RDAM", true), 
    RFDAM("RFDAM", true),
    RDSR("RDSR", true),
    RFDSR("RFDSR", true),
    SR("SR", false);

    private final String label;
    private final boolean hasRandomDraft;

    GameType(String label, boolean hasRandomDraft) {
        this.label = label;
        this.hasRandomDraft = hasRandomDraft;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }

    public boolean hasRandomDraft() {
        return hasRandomDraft;
    }
    
}
