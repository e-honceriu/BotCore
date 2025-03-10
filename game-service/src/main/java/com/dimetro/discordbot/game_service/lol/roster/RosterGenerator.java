package com.dimetro.discordbot.game_service.lol.roster;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dimetro.discordbot.game_service.lol.entity.player.Player;
import com.dimetro.discordbot.game_service.lol.entity.series.GameType;
import com.dimetro.discordbot.game_service.lol.entity.team.Team;
import com.dimetro.discordbot.game_service.lol.roster.exception.RosterStrategyNotFoundException;

@Component
public class RosterGenerator {
    
    private final Map<GameType, RosterStrategy> strategyMap;

    @Autowired
    public RosterGenerator() {
        RosterStrategy balancedStrategy = new TwoTeamBalancedEloRosterStrategy();
        strategyMap = new EnumMap<>(GameType.class);
        strategyMap.put(GameType.RDAM, balancedStrategy);
        strategyMap.put(GameType.RDSR, balancedStrategy);
        strategyMap.put(GameType.RFDAM, balancedStrategy);
        strategyMap.put(GameType.RFDSR, balancedStrategy);
        strategyMap.put(GameType.SR, balancedStrategy);
    }

    private Map<Player, Integer> getEloMap(GameType gameType, List<Player> players) {
        
        Map<Player, Integer> eloMap = new HashMap<>();

        for (Player player : players) {
            Integer elo = player.getPlayerElo().getElo(gameType);

            eloMap.put(player, elo);
        }

        return eloMap;
    }

    private RosterStrategy getStrategy(GameType gameType) {
        RosterStrategy strategy = strategyMap.get(gameType);
        if (strategy == null) {
            throw new RosterStrategyNotFoundException(gameType);
        }
        return strategy;
    }

    public List<Team> generateTeams(GameType gameType, List<Player> players) {

        Map<Player, Integer> eloMap = getEloMap(gameType, players);
        RosterStrategy strategy = getStrategy(gameType);

        return strategy.generateTeams(eloMap);
    }

}
