package com.dimetro.discordbot.game_service.lol.elo;

import java.util.HashMap;
import java.util.Map;

import com.dimetro.discordbot.game_service.lol.entity.match.MatchResult;
import com.dimetro.discordbot.game_service.lol.entity.player.Player;

public abstract class NonZeroEloStrategy implements EloStrategy {

    private final int maxEloGain;

    public NonZeroEloStrategy(int maxEloGain) {
        this.maxEloGain = maxEloGain;
    }

    protected abstract Integer computePlayerElo(Player player, MatchResult result);

    private Integer getPlayerElo(Player player, MatchResult result) {
        Integer playerElo = computePlayerElo(player, result);
        if (playerElo < -1 * maxEloGain) {
            return -1 * maxEloGain;
        }
        if (playerElo > maxEloGain) {
            return maxEloGain;
        }
        return playerElo;
    }

    @Override
    public Map<Player, Integer> computeElo(MatchResult result) {

        Map<Player, Integer> eloMap = new HashMap<>();

        for (Player player : result.getWinningTeam().getPlayers()) {
            eloMap.put(player, getPlayerElo(player, result));
        }

        for (Player player : result.getLosingTeam().getPlayers()) {
            eloMap.put(player, getPlayerElo(player, result));
        }

        return eloMap;
    }
    
}
