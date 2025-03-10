package com.dimetro.discordbot.game_service.lol.service.match;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimetro.discordbot.game_service.lol.dto.request.match.GenerateTeamsRequestDTO;
import com.dimetro.discordbot.game_service.lol.dto.response.match.MatchResponseDTO;
import com.dimetro.discordbot.game_service.lol.entity.match.Match;
import com.dimetro.discordbot.game_service.lol.entity.match.MatchStatus;
import com.dimetro.discordbot.game_service.lol.entity.player.Player;
import com.dimetro.discordbot.game_service.lol.entity.team.Team;

import com.dimetro.discordbot.game_service.lol.roster.RosterGenerator;
import com.dimetro.discordbot.game_service.lol.service.match.exception.MatchNotInRosterGenerationPhaseException;
import com.dimetro.discordbot.game_service.lol.service.player.PlayerService;

@Service
public class MatchRosterService {
    
    private final MatchService matchService;
    private final PlayerService playerService;
    private final RosterGenerator rosterGenerator;

    @Autowired
    public MatchRosterService(MatchService matchService, PlayerService playerService,
                              RosterGenerator rosterGenerator) {
        this.matchService = matchService;
        this.playerService = playerService;
        this.rosterGenerator = rosterGenerator;
    }

    public MatchResponseDTO generateTeams(GenerateTeamsRequestDTO dto) {

        Match match = matchService.getMatchByIdOrThrow(dto.getMatchId(), dto.getBotId());

        if (match.getStatus() != MatchStatus.TEAM_GENERATION) {
            throw new MatchNotInRosterGenerationPhaseException(match);
        }

        List<Player> players = playerService.getPlayersByIdsOrThrow(dto.getPlayerIds(), dto.getBotId());

        List<Team> teams = rosterGenerator.generateTeams(match.getSeries().getGameType(), players);
        match.changeTeams(teams);
        
        return MatchResponseDTO.build(matchService.saveMatch(match));
    }

}
