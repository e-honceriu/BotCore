package com.dimetro.discordbot.game_service.lol.elo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dimetro.discordbot.game_service.lol.config.LolConfig;

@Component
public class DifficultyEloStrategy extends BaseDifficultyEloStrategy {
    
    @Autowired
    public DifficultyEloStrategy(LolConfig lolConfig) {
        super(
            lolConfig.getEloGain(), 
            lolConfig.getMaxEloGain(), 
            lolConfig.getEloDifficultyFactor(),
             lolConfig.getEloDifficultyKFactor()
        );
    }

}
