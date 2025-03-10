package com.dimetro.discordbot.game_service.lol.service.team;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimetro.discordbot.game_service.lol.entity.team.Team;

import com.dimetro.discordbot.game_service.lol.repository.TeamRepository;
import com.dimetro.discordbot.game_service.lol.service.team.exception.TeamDoesNotBelongToBotException;
import com.dimetro.discordbot.game_service.lol.service.team.exception.TeamNotFoundException;

@Service
public class TeamService {
    
    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team getTeamByIdOrThrow(UUID id, UUID botId) {

        Optional<Team> dbTeam = teamRepository.findById(id);

        if (dbTeam.isEmpty()) {
            throw new TeamNotFoundException(id);
        }

        Team team = dbTeam.get();
        
        if (!team.getMatch().getSeries().getBotId().equals(botId)) {
            throw new TeamDoesNotBelongToBotException(team);
        }

        return dbTeam.get();
    }

}
