package com.dimetro.discordbot.game_service.lol.elo;

import com.dimetro.discordbot.game_service.lol.entity.match.MatchResult;
import com.dimetro.discordbot.game_service.lol.entity.player.Player;
import com.dimetro.discordbot.game_service.lol.entity.series.GameType;

public abstract class BaseDifficultyEloStrategy extends BaseFixedEloStrategy {
    
    protected final Integer DIFFICULTY_MAGNITUDE;
    protected final Integer DIFFICULTY_K_FACTOR;

    public BaseDifficultyEloStrategy(int baseElo, int maxEloGain, int difficultyMagnitude, int difficultyKFactor) {
        super(baseElo, maxEloGain);
        DIFFICULTY_MAGNITUDE = difficultyMagnitude;
        DIFFICULTY_K_FACTOR = difficultyKFactor;
    }

    protected Integer computeDifficultyBonus(Player player, MatchResult matchResult) {

        GameType gameType = matchResult.getGameType();
        boolean playerWon = matchResult.winningTeamHasPlayer(player);

        int result = (playerWon == true) ? 1 : 0;
        Integer playerElo = player.getElo(gameType);

        Integer enemyMeanElo = playerWon
                ? matchResult.getLosingTeamAverageElo(gameType)
                : matchResult.getWinningTeamAverageElo(gameType);

        double expected = 1.0 / (1 + Math.pow(10, (enemyMeanElo - playerElo) / (double) DIFFICULTY_K_FACTOR));
        int diffucultyBonus = (int) (DIFFICULTY_MAGNITUDE * (result - expected));
        return diffucultyBonus;
    }

    @Override
    protected Integer computePlayerElo(Player player, MatchResult result) {
        return getBasePlayerElo(player, result) + computeDifficultyBonus(player, result);
    }

}
