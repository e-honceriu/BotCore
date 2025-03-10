package com.dimetro.discordbot.game_service.lol.entity.match;

public enum MatchStatus {
    
    TEAM_GENERATION("TEAM_GENERATION"),
    BANNING("BANNING"),
    DRAFTING("DRAFTING"),
    PLAYING("PLAYING"),
    ENDED("ENDED");

    private final String label;

    MatchStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
