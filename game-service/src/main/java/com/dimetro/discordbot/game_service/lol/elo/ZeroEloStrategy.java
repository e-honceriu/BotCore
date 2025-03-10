package com.dimetro.discordbot.game_service.lol.elo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.dimetro.discordbot.game_service.lol.entity.match.MatchResult;
import com.dimetro.discordbot.game_service.lol.entity.player.Player;

@Component
public class ZeroEloStrategy implements EloStrategy {
    
    @Override
    public Map<Player, Integer> computeElo(MatchResult result) {
        Map<Player, Integer> eloMap = new HashMap<>();

        for (Player player : result.getWinningTeam().getPlayers()) {
                eloMap.put(player, 0);
        }
        
        for (Player player : result.getLosingTeam().getPlayers()) {
            eloMap.put(player, 0);
        }

        return eloMap;
    }

}
