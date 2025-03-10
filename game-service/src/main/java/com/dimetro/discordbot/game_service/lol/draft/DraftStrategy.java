package com.dimetro.discordbot.game_service.lol.draft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dimetro.discordbot.game_service.lol.entity.champion.Champion;
import com.dimetro.discordbot.game_service.lol.entity.champion.ChampionRole;
import com.dimetro.discordbot.game_service.lol.entity.match.Match;
import com.dimetro.discordbot.game_service.lol.entity.team.Team;
import com.dimetro.discordbot.game_service.lol.service.champion.ChampionService;

public abstract class DraftStrategy {
    
    protected final int CHAMP_POOL_SIZE;
    protected Map<ChampionRole, List<Champion>> championsByRole = new HashMap<>();
    protected List<Champion> allChampions = new ArrayList<>();
    private final ChampionService championService;

    public DraftStrategy(ChampionService championService, int championPoolSize) {
        this.championService = championService;
        this.CHAMP_POOL_SIZE = championPoolSize;
    }
    
    protected void fetchChampions() {
        if (championsByRole.isEmpty()) {
            championsByRole = championService.getChampionsByRole();
        }
        if (allChampions.isEmpty()) {
            allChampions = championService.getAllChampions();
        }
    }

    public abstract Map<Team, List<Champion>> generateChampionPool(Match match);
    public abstract List<Champion> generateChampionPool(Team team);

}
