package com.dimetro.discordbot.game_service.lol.entity.series;

public enum RankingType {
    
    RANKED("RANKED"),
    UNRANKED("UNRANKED");

    private final String label;

    RankingType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
