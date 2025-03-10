package com.dimetro.discordbot.game_service.lol.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;


@Component
@ConfigurationProperties(prefix = "lol")
@Getter
@Setter
public class LolConfig {

    public static int STARTING_ELO;

    private int maxEloGain;
    private int maxBans;
    private int maxPlayers;
    private int championPoolSize;
    private int eloGain;
    private int eloDifficultyFactor;
    private int eloPerformanceFactor;
    private int eloDifficultyKFactor;
    private int startingElo;
    
}
