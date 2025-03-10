package com.dimetro.discordbot.game_service.lol.draft;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dimetro.discordbot.game_service.lol.config.LolConfig;
import com.dimetro.discordbot.game_service.lol.entity.champion.Champion;
import com.dimetro.discordbot.game_service.lol.entity.team.Team;
import com.dimetro.discordbot.game_service.lol.service.champion.ChampionService;

@Component
public class RandomDraftStrategy extends RandomBaseDraftStrategy {
    
    @Autowired
    public RandomDraftStrategy(ChampionService championService, LolConfig lolConfig) {
        super(championService, lolConfig.getChampionPoolSize());
    }

    @Override
    protected List<Champion> getTeamUnavailableChampions(Team team) {
        List<Champion> unavaiableChampions = new ArrayList<>();

        for (Team matchTeam : team.getMatch().getTeams()) {
            unavaiableChampions.addAll(matchTeam.getBans());
        }

        return unavaiableChampions;
    }

}
