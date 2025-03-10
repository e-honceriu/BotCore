package com.dimetro.discordbot.game_service.lol.dto.request.match;

public enum RequestMatchResult {
    
    WIN("WIN"),
    LOSE("LOSE");

    private final String label;

    RequestMatchResult(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
