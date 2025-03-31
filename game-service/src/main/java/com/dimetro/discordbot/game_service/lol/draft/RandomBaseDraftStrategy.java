package com.dimetro.discordbot.game_service.lol.draft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.dimetro.discordbot.game_service.lol.draft.exception.InsufficientChampionsException;
import com.dimetro.discordbot.game_service.lol.entity.champion.Champion;
import com.dimetro.discordbot.game_service.lol.entity.champion.ChampionRole;
import com.dimetro.discordbot.game_service.lol.entity.match.Match;
import com.dimetro.discordbot.game_service.lol.entity.team.Team;
import com.dimetro.discordbot.game_service.lol.service.champion.ChampionService;

public abstract class RandomBaseDraftStrategy extends DraftStrategy {

    public RandomBaseDraftStrategy(ChampionService championService, int championPoolSize) {
        super(championService, championPoolSize);
    }

    protected abstract List<Champion> getTeamUnavailableChampions(Team team);

    private List<Champion> getChampionPool(List<Champion> unavailableChampions) {

        List<Champion> championPool = new ArrayList<>();

        int numberOfRoles = championsByRole.keySet().size();
        int championsToSelectPerRole = CHAMP_POOL_SIZE / numberOfRoles;
        int remainder = CHAMP_POOL_SIZE % numberOfRoles;

        List<ChampionRole> roles = new ArrayList<>(championsByRole.keySet());
        Collections.shuffle(roles);

        for (ChampionRole role : roles) {
            List<Champion> roleChampions = championsByRole.get(role);
            List<Champion> filteredRoleChampions = roleChampions.stream()
                .filter(champion -> !championPool.contains(champion) && !unavailableChampions.contains(champion))
                .collect(Collectors.toList());
            
            Collections.shuffle(filteredRoleChampions);
            int toSelect = championsToSelectPerRole + (remainder-- > 0 ? 1 : 0);
            championPool.addAll(getChampionsForRole(filteredRoleChampions, toSelect));
        }

        List<Champion> remainingChamps = allChampions.stream()
            .filter(champion -> !championPool.contains(champion) && !unavailableChampions.contains(champion))
            .collect(Collectors.toList());

        if (remainingChamps.size() < 5) {
            throw new InsufficientChampionsException();
        }

        if (championPool.size() < CHAMP_POOL_SIZE) {
            int remainingCount = CHAMP_POOL_SIZE - championPool.size();
            if (remainingCount > remainingChamps.size()) {
                remainingCount = remainingChamps.size();
            }
            Collections.shuffle(remainingChamps);
            championPool.addAll(remainingChamps.subList(0, remainingCount));
        }
        return championPool;
    }

    private List<Champion> getChampionsForRole(List<Champion> roleChampions, int toSelect) {

        List<Champion> champions = new ArrayList<>();

        for (int i = 0; i < toSelect; i++) {
            if (roleChampions.isEmpty()) {
                break; 
            }
            Champion selectedChampion = roleChampions.remove(0);
            champions.add(selectedChampion);
        }

        return champions;
    }

    @Override
    public Map<Team, List<Champion>> generateChampionPool(Match match) {
        fetchChampions();
        return match.getTeams().stream()
            .collect(Collectors.toMap(
                team -> team,
                team -> getChampionPool(getTeamUnavailableChampions(team))
            ));
    }

    @Override
    public List<Champion> generateChampionPool(Team team) {
        fetchChampions();
        return getChampionPool(getTeamUnavailableChampions(team));
    }

}
