package com.dimetro.discordbot.game_service.lol.service.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dimetro.discordbot.game_service.lol.dto.request.match.BanChampionRequestDTO;
import com.dimetro.discordbot.game_service.lol.dto.response.match.MatchResponseDTO;
import com.dimetro.discordbot.game_service.lol.entity.champion.Champion;
import com.dimetro.discordbot.game_service.lol.entity.match.Match;
import com.dimetro.discordbot.game_service.lol.entity.match.MatchStatus;
import com.dimetro.discordbot.game_service.lol.entity.player.Player;
import com.dimetro.discordbot.game_service.lol.service.champion.ChampionService;
import com.dimetro.discordbot.game_service.lol.service.match.exception.*;
import com.dimetro.discordbot.game_service.lol.service.player.PlayerService;

@Service
public class MatchBanningService {
    
    private final MatchService matchService;
    private final PlayerService playerService;
    private final ChampionService championService;
    private int maxBans;

    @Autowired
    public MatchBanningService(MatchService matchService, PlayerService playerService, 
                               ChampionService championService, @Value("${lol.maxBans}") int maxBans) {
        this.matchService = matchService;
        this.playerService = playerService;
        this.championService = championService;
        this.maxBans = maxBans;
    }
    
    public MatchResponseDTO banChampion(BanChampionRequestDTO dto) {

        Match match = matchService.getMatchByIdOrThrow(dto.getMatchId(), dto.getBotId());
        MatchStatus status = match.getStatus();

        if (status != MatchStatus.BANNING) {
            if (status != MatchStatus.TEAM_GENERATION) {
                throw new MatchNotInBanningPhaseException(match);
            } 
            match.setStatus(MatchStatus.BANNING);
        }
        
        Player player = playerService.getPlayerByIdOrThrow(dto.getPlayerId(), dto.getBotId());
        Champion champion = championService.getChampionByQueryOrThrow(dto.getChampion());
        
        match.banChampion(player, champion, maxBans);
        return MatchResponseDTO.build(matchService.saveMatch(match));
    }

}
