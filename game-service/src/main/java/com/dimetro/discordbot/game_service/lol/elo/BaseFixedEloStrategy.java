package com.dimetro.discordbot.game_service.lol.elo;

import com.dimetro.discordbot.game_service.lol.elo.exception.PlayerNotFoundInMatchResultException;
import com.dimetro.discordbot.game_service.lol.entity.match.MatchResult;
import com.dimetro.discordbot.game_service.lol.entity.player.Player;

public abstract class BaseFixedEloStrategy extends NonZeroEloStrategy {
    
    protected Integer BASE_ELO;

    public BaseFixedEloStrategy(int baseElo, int maxEloGain) {
        super(maxEloGain);
        BASE_ELO = baseElo;
    }

    protected Integer getBasePlayerElo(Player player, MatchResult result) {

        if (result.winningTeamHasPlayer(player)) {
            return BASE_ELO;
        }

        if (result.losingTeamHasPlayer(player)) {
            return -1 * BASE_ELO;
        }

        throw new PlayerNotFoundInMatchResultException(player, result.getMatch());
    }

    @Override
    protected Integer computePlayerElo(Player player, MatchResult result) {
        return getBasePlayerElo(player, result);
    }

}
