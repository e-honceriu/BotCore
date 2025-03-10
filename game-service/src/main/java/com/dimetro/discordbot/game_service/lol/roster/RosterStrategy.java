package com.dimetro.discordbot.game_service.lol.roster;

import java.util.List;
import java.util.Map;

import com.dimetro.discordbot.game_service.lol.entity.player.Player;
import com.dimetro.discordbot.game_service.lol.entity.team.Team;

public interface RosterStrategy {
    
    public List<Team> generateTeams(Map<Player, Integer> eloMap);

}
