package com.dimetro.discordbot.game_service.lol.elo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dimetro.discordbot.game_service.lol.config.LolConfig;
import com.dimetro.discordbot.game_service.lol.service.player.PlayerStatsService;

@Component
public class PerformanceEloStrategy extends BasePerformanceEloStrategy{

    @Autowired
    public PerformanceEloStrategy(LolConfig lolConfig, PlayerStatsService playerStatsService) {
        super(
            lolConfig.getEloGain(), 
            lolConfig.getMaxEloGain(), 
            lolConfig.getEloDifficultyFactor(),
            lolConfig.getEloDifficultyKFactor(),
            lolConfig.getEloPerformanceFactor(),
            playerStatsService
        );
    }
    
}
