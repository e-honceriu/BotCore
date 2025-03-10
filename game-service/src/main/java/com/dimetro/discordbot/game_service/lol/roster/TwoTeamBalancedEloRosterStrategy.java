package com.dimetro.discordbot.game_service.lol.roster;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dimetro.discordbot.game_service.lol.entity.player.Player;
import com.dimetro.discordbot.game_service.lol.entity.team.Team;

public class TwoTeamBalancedEloRosterStrategy implements RosterStrategy {

    @Override
    public List<Team> generateTeams(Map<Player, Integer> eloMap) {
        
        Integer[] elos = new Integer[]{0, 0};
        Team[] teams = new Team[]{new Team(), new Team()};

        List<Map.Entry<Player, Integer>> sortedPlayers = new ArrayList<>(eloMap.entrySet());
        sortedPlayers.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        for (Map.Entry<Player, Integer> entry : sortedPlayers) {
            Player player = entry.getKey();
            Integer elo = entry.getValue();
            int eloDif = elos[0] - elos[1];
            if (eloDif < 0) {
                teams[0].addPlayer(player);
                elos[0] += elo;
            } else {
                teams[1].addPlayer(player);
                elos[1] += elo;
            }
        }

        int teamSizeDif = teams[0].getTeamSize() - teams[1].getTeamSize();
        
        while (Math.abs(teamSizeDif) >= 2) {

            int highEloTeamIndex = teamSizeDif > 0 ? 0 : 1;
            int lowEloTeamIndex = highEloTeamIndex == 0 ? 1 : 0;

            for (int i = sortedPlayers.size() - 1; i >= 0; i--) {
                Player player = sortedPlayers.get(i).getKey();
                if (teams[highEloTeamIndex].removePlayer(player)) {
                    teams[lowEloTeamIndex].addPlayer(player);
                    break;
                }
            }

            teamSizeDif = teams[0].getTeamSize() - teams[1].getTeamSize();

        }

        return List.of(teams);
    }
    
}
