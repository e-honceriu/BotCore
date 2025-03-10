package com.dimetro.discordbot.game_service.lol.entity.champion;

public enum ChampionRole {

    MARKSMAN("MARKSMAN"),
    MAGE("MAGE"),
    ASSASSIN("ASSASSIN"),
    SUPPORT("SUPPORT"),
    FIGHTER("FIGHTER"),
    TANK("TANK");

    private final String label;

    ChampionRole(String label) {
        this.label = label;
    }

    public String getRole() {
        return label;
    }
}
