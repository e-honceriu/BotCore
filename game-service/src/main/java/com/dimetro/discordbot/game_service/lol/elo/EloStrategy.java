package com.dimetro.discordbot.game_service.lol.elo;

import java.util.Map;

import com.dimetro.discordbot.game_service.lol.entity.match.MatchResult;
import com.dimetro.discordbot.game_service.lol.entity.player.Player;

public interface EloStrategy {

    public abstract Map<Player, Integer> computeElo(MatchResult result);

}
