package com.dimetro.discordbot.game_service.lol.service.team;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimetro.discordbot.game_service.lol.draft.DraftGenerator;
import com.dimetro.discordbot.game_service.lol.dto.request.team.GenerateTeamChampPoolRequestDTO;
import com.dimetro.discordbot.game_service.lol.dto.response.team.TeamChampionPoolResponseDTO;
import com.dimetro.discordbot.game_service.lol.entity.champion.Champion;
import com.dimetro.discordbot.game_service.lol.entity.match.Match;
import com.dimetro.discordbot.game_service.lol.entity.match.MatchStatus;
import com.dimetro.discordbot.game_service.lol.entity.team.Team;
import com.dimetro.discordbot.game_service.lol.entity.team.TeamChampionPool;
import com.dimetro.discordbot.game_service.lol.service.match.MatchService;
import com.dimetro.discordbot.game_service.lol.service.team.exception.MatchNotInDraftingPhaseException;

@Service
public class TeamManagementService {
    
    private final DraftGenerator draftGenerator;
    private final TeamService teamService;
    private final MatchService matchService;
    private final TeamChampionPoolService championPoolService;

    @Autowired
    public TeamManagementService(DraftGenerator draftGenerator, TeamService teamService, TeamChampionPoolService championPoolService,
                                 MatchService matchService) {
        this.draftGenerator = draftGenerator;
        this.teamService = teamService;
        this.championPoolService = championPoolService;
        this.matchService = matchService;
    }

    public TeamChampionPoolResponseDTO generateChampionPool(GenerateTeamChampPoolRequestDTO dto) {

        Team team = teamService.getTeamByIdOrThrow(dto.getTeamId(), dto.getBotId());
        Match match = team.getMatch();
        MatchStatus matchStatus = match.getStatus();

        if (matchStatus != MatchStatus.DRAFTING) {
            if (matchStatus != MatchStatus.BANNING && matchStatus != MatchStatus.TEAM_GENERATION) {
                throw new MatchNotInDraftingPhaseException(team.getMatch());
            }
            match.setStatus(MatchStatus.DRAFTING);
        }
        team.setMatch(matchService.saveMatch(match));

        List<Champion> champions = draftGenerator.generateChampionPool(team);

        TeamChampionPool championPool = new TeamChampionPool();
        championPool.setTeam(team);
        championPool.setChampions(champions);
        
        TeamChampionPool savedChampionPool = championPoolService.editChampionPoolOrCreate(championPool);
        return TeamChampionPoolResponseDTO.build(savedChampionPool);
    }

    public TeamChampionPoolResponseDTO getTeamChampionPool(UUID teamId, UUID botId) {

        Team team = teamService.getTeamByIdOrThrow(teamId, botId);
        TeamChampionPool championPool = championPoolService.getChampionPoolByTeamOrThrow(team);

        return TeamChampionPoolResponseDTO.build(championPool);
    }

}
