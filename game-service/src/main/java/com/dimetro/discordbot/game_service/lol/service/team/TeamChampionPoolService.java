package com.dimetro.discordbot.game_service.lol.service.team;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.dimetro.discordbot.game_service.lol.entity.team.Team;
import com.dimetro.discordbot.game_service.lol.entity.team.TeamChampionPool;
 
import com.dimetro.discordbot.game_service.lol.repository.TeamChampionPoolRepository;
import com.dimetro.discordbot.game_service.lol.service.team.exception.TeamChampionPoolNotFoundException;
import com.dimetro.discordbot.game_service.lol.service.team.exception.TeamChampionPoolNotProvidedException;

import org.springframework.stereotype.Service;

@Service
public class TeamChampionPoolService {

    private final TeamChampionPoolRepository repository;

    @Autowired
    public TeamChampionPoolService(TeamChampionPoolRepository repository) {
        this.repository = repository;
    }

    public TeamChampionPool saveChampionPool(TeamChampionPool championPool) {
        return repository.save(championPool);
    }
    
    public TeamChampionPool editChampionPoolOrCreate(TeamChampionPool championPool) {

        if (championPool.getTeam() == null) {
            throw new TeamChampionPoolNotProvidedException();
        }

        Optional<TeamChampionPool> dbChampionPool = repository.findByTeam(championPool.getTeam());

        if (dbChampionPool.isEmpty()) {
            return repository.save(championPool);
        }

        TeamChampionPool existingChampPool = dbChampionPool.get();
        existingChampPool.changeChampions(championPool.getChampions());
        return repository.save(existingChampPool);
    }

    public TeamChampionPool getChampionPoolByTeamOrThrow(Team team) {

        Optional<TeamChampionPool> championPool = repository.findByTeam(team);

        if (championPool.isEmpty()) {
            throw new TeamChampionPoolNotFoundException(team);
        }

        return championPool.get();
    }

}
