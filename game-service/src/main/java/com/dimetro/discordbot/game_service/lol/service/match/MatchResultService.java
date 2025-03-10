package com.dimetro.discordbot.game_service.lol.service.match;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimetro.discordbot.game_service.lol.entity.match.Match;
import com.dimetro.discordbot.game_service.lol.entity.match.MatchResult;
import com.dimetro.discordbot.game_service.lol.entity.team.Team;
import com.dimetro.discordbot.game_service.lol.service.match.exception.*;
import com.dimetro.discordbot.game_service.lol.repository.MatchResultRepository;

@Service
public class MatchResultService {
    
    private final MatchResultRepository repository;

    @Autowired
    public MatchResultService(MatchResultRepository repository) {
        this.repository = repository;
    }

    public MatchResult createMatchResult(Match match, Team winningTeam) {
        
        List<Team> matchTeams = match.getTeams();

        if (!matchTeams.contains(winningTeam)) {
            throw new TeamNotInMatchException(winningTeam, match);
        }

        Optional<MatchResult> dbResult = repository.findByMatch(match);

        if (dbResult.isPresent()) {
            throw new MatchResultAlreadyExistsException(match);
        }

        MatchResult result = new MatchResult();
        result.setMatch(match);

        for (Team team : matchTeams) {
            if (team.equals(winningTeam)) {
                result.setWinningTeam(team);
            } else {
                result.setLosingTeam(team);
            }
        }

        return repository.save(result);
    }
    
}
