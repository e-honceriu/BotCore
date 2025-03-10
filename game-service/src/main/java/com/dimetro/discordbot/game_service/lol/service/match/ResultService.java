package com.dimetro.discordbot.game_service.lol.service.match;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimetro.discordbot.game_service.lol.dto.request.match.*;
import com.dimetro.discordbot.game_service.lol.dto.response.match.MatchResultResponseDTO;
import com.dimetro.discordbot.game_service.lol.elo.EloGenerator;
import com.dimetro.discordbot.game_service.lol.entity.match.*;
import com.dimetro.discordbot.game_service.lol.entity.player.Player;
import com.dimetro.discordbot.game_service.lol.entity.team.Team;
import com.dimetro.discordbot.game_service.lol.service.match.exception.MatchAlreadyEndedException;
import com.dimetro.discordbot.game_service.lol.service.match.exception.WinningTeamNotFoundException;
import com.dimetro.discordbot.game_service.lol.service.player.PlayerService;
import com.dimetro.discordbot.game_service.lol.service.player.PlayerStatsService;
import com.dimetro.discordbot.game_service.lol.service.team.TeamService;

import jakarta.transaction.Transactional;

@Service
public class ResultService {
    
    private final MatchService matchService;
    private final TeamService teamService;
    private final PlayerService playerService;
    private final MatchResultService matchResultService;
    private final EloGenerator eloGenerator;
    private final PlayerStatsService playerStatsService;

    @Autowired
    public ResultService(PlayerService playerService, TeamService teamService, MatchResultService matchResultService, 
                         EloGenerator eloGenerator, PlayerStatsService playerStatsService, MatchService matchService) {
        this.playerService = playerService;
        this.teamService = teamService;
        this.matchResultService = matchResultService;
        this.eloGenerator = eloGenerator;
        this.playerStatsService = playerStatsService;
        this.matchService = matchService;
    }

    @Transactional
    public MatchResultResponseDTO setMatchResult(SetMatchResultRequestDTO dto) {

        Match match = matchService.getMatchByIdOrThrow(dto.getMatchId(), dto.getBotId());
        MatchStatus status = match.getStatus();

        if (status == MatchStatus.ENDED) {
            throw new MatchAlreadyEndedException(match);
        }

        Team winningTeam = teamService.getTeamByIdOrThrow(dto.getWinningTeamId(), dto.getBotId());

        MatchResult result = matchResultService.createMatchResult(match, winningTeam);
        Map<Player, Integer> eloMap = eloGenerator.getElo(result);
        playerStatsService.updateOrCreatePlayerStats(eloMap, match);
        playerService.updatePlayerElo(eloMap, match.getGameType(), dto.getBotId());

        match.setStatus(MatchStatus.ENDED);
        matchService.saveMatch(match);

        return MatchResultResponseDTO.build(result);
    }

    @Transactional
    public void setLiveResult(SetLiveMatchResultDTO dto) {
        
        Match match = matchService.getMatchByIdOrThrow(dto.getMatchId(), dto.getBotId());
        Player player = playerService.getPlayerByRiotIdOrThrow(dto.getRiotId());

        if (match.getStatus() == MatchStatus.ENDED) {
            throw new MatchAlreadyEndedException(match);
        }

        Team winningTeam;
        if (dto.getResult() == RequestMatchResult.WIN) {
            winningTeam = match.getPlayerTeam(player);
        } else {
            winningTeam = match.getPlayerOpposingTeam(player);
        }

        if (winningTeam == null) {
            throw new WinningTeamNotFoundException(player, match);
        }

        MatchResult result = matchResultService.createMatchResult(match, winningTeam);
        Map<Player, Integer> eloMap = eloGenerator.getElo(result);
        playerStatsService.updateOrCreatePlayerStats(eloMap, match);
        playerService.updatePlayerElo(eloMap, match.getGameType(), dto.getBotId());

        match.setStatus(MatchStatus.ENDED);
        matchService.saveMatch(match);
    }

}
