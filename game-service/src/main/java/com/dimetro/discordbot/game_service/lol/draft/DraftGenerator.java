package com.dimetro.discordbot.game_service.lol.draft;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dimetro.discordbot.game_service.lol.draft.exception.DraftStrategyNotFoundException;
import com.dimetro.discordbot.game_service.lol.entity.champion.Champion;
import com.dimetro.discordbot.game_service.lol.entity.match.Match;
import com.dimetro.discordbot.game_service.lol.entity.series.GameType;
import com.dimetro.discordbot.game_service.lol.entity.team.Team;

@Component
public class DraftGenerator {
    
    private final Map<GameType, DraftStrategy> strategyMap;

    @Autowired
    public DraftGenerator(RandomDraftStrategy randomDraftStrategy, RandomFearlessDraftStrategy randomFearlessDraftStrategy) {
        strategyMap = new EnumMap<>(GameType.class);
        strategyMap.put(GameType.RDAM, randomDraftStrategy);
        strategyMap.put(GameType.RDSR, randomDraftStrategy);
        strategyMap.put(GameType.RFDAM, randomFearlessDraftStrategy);
        strategyMap.put(GameType.RFDSR, randomFearlessDraftStrategy);
    }

    public Map<Team, List<Champion>> generateChampionPool(Match match) {
        
        GameType gameType = match.getSeries().getGameType();
        DraftStrategy strategy = strategyMap.get(gameType);

        if (strategy == null) {
            throw new DraftStrategyNotFoundException(gameType);
        }

        return strategy.generateChampionPool(match);
    }

    public List<Champion> generateChampionPool(Team team) {
        
        GameType gameType = team.getMatch().getSeries().getGameType();
        DraftStrategy strategy = strategyMap.get(gameType);

        if (strategy == null) {
            throw new DraftStrategyNotFoundException(gameType);
        }

        return strategy.generateChampionPool(team);
    }

}
