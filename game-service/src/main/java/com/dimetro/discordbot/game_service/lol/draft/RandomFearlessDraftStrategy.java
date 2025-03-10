package com.dimetro.discordbot.game_service.lol.draft;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dimetro.discordbot.game_service.lol.config.LolConfig;
import com.dimetro.discordbot.game_service.lol.entity.champion.Champion;
import com.dimetro.discordbot.game_service.lol.entity.match.Match;
import com.dimetro.discordbot.game_service.lol.entity.player.PlayerStats;
import com.dimetro.discordbot.game_service.lol.entity.team.Team;
import com.dimetro.discordbot.game_service.lol.service.champion.ChampionService;
import com.dimetro.discordbot.game_service.lol.service.match.MatchService;
import com.dimetro.discordbot.game_service.lol.service.player.PlayerStatsService;

@Component
public class RandomFearlessDraftStrategy extends RandomBaseDraftStrategy {

    private final MatchService matchService;
    private final PlayerStatsService playerStatsService;

    @Autowired
    public RandomFearlessDraftStrategy(ChampionService championService, MatchService matchService, 
                                       PlayerStatsService playerStatsService, LolConfig lolConfig) {
        super(championService, lolConfig.getChampionPoolSize());
        this.matchService = matchService;
        this.playerStatsService = playerStatsService;
    }

    @Override
    protected List<Champion> getTeamUnavailableChampions(Team team) {

        Set<Champion> unavailableChampions = new HashSet<>();
        List<Match> seriesMatches = matchService.getMatchesBySeries(team.getMatch().getSeries());

        for (Match seriesMatch : seriesMatches) {
            List<PlayerStats> matchPlayerStats = playerStatsService.getAllByMatch(seriesMatch);
            for (PlayerStats playerStats : matchPlayerStats) {
                
                Champion playedChampion = playerStats.getPlayedChampion();
                if (playedChampion != null) {
                    unavailableChampions.add(playedChampion);
                }

            }
            
        }

        for (Team matchTeam : team.getMatch().getTeams()) {
            if (!matchTeam.equals(team)) {
                unavailableChampions.addAll(matchTeam.getBans());
            }
        }

        return new ArrayList<>(unavailableChampions);
    }
    
}
