package com.dimetro.discordbot.game_service.lol.service.match;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimetro.discordbot.game_service.lol.dto.request.match.GetLiveClientMatchRequestDTO;
import com.dimetro.discordbot.game_service.lol.dto.response.match.LiveClientMatchResponseDTO;
import com.dimetro.discordbot.game_service.lol.entity.match.Match;
import com.dimetro.discordbot.game_service.lol.entity.match.MatchStatus;
import com.dimetro.discordbot.game_service.lol.entity.player.Player;
import com.dimetro.discordbot.game_service.lol.service.player.PlayerService;

@Service
public class MatchLiveClientService {
    
    private final PlayerService playerService;
    private final MatchService matchService;

    @Autowired
    public MatchLiveClientService(PlayerService playerService, MatchService matchService) {
        this.playerService = playerService;
        this.matchService = matchService;
    }

    public LiveClientMatchResponseDTO getMatch(GetLiveClientMatchRequestDTO dto) {
    
        List<Player> teamChaos = new ArrayList<>();
        List<Player> teamOrder = new ArrayList<>();

        UUID botId = dto.getBotId();

        for (String riotId : dto.getTeamChaos()) {
            teamChaos.add(playerService.getPlayerByRiotIdOrThrow(riotId));
        }

        for (String riotId : dto.getTeamOrder()) {
            teamOrder.add(playerService.getPlayerByRiotIdOrThrow(riotId));
        }

        Match match = matchService.getMatchByPlayersOrThrow(botId, teamOrder, teamChaos);
        match.setStatus(MatchStatus.PLAYING);
        return LiveClientMatchResponseDTO.build(matchService.saveMatch(match));
    }
    
}
