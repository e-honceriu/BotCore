package com.dimetro.discordbot.game_service.lol.elo;

import java.util.EnumMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dimetro.discordbot.game_service.lol.elo.exception.EloStrategyNotFoundException;
import com.dimetro.discordbot.game_service.lol.entity.match.MatchResult;
import com.dimetro.discordbot.game_service.lol.entity.player.Player;
import com.dimetro.discordbot.game_service.lol.entity.series.RankingType;

@Component
public class EloGenerator {
    
    private final Map<RankingType, EloStrategy> strategyMap;

    @Autowired
    public EloGenerator(PerformanceEloStrategy perfomanceEloStrategy, ZeroEloStrategy zeroEloStrategy) {
        strategyMap = new EnumMap<>(RankingType.class);
        strategyMap.put(RankingType.UNRANKED, zeroEloStrategy);
        strategyMap.put(RankingType.RANKED, perfomanceEloStrategy);
    } 

    public Map<Player, Integer> getElo(MatchResult result) {
        
        RankingType rankingType = result.getRankingType();
        EloStrategy eloStrategy = strategyMap.get(rankingType);

        if (eloStrategy == null) {
            throw new EloStrategyNotFoundException(rankingType);
        }

        return eloStrategy.computeElo(result);
    }

}
