package com.dimetro.discordbot.game_service.lol.service.player;

import org.springframework.stereotype.Service;

import com.dimetro.discordbot.game_service.lol.dto.request.player.LiveMatchStatsUpdateRequestDTO;
import com.dimetro.discordbot.game_service.lol.dto.request.player.LivePlayerStatsUpdateRequestDTO;
import com.dimetro.discordbot.game_service.lol.entity.champion.Champion;
import com.dimetro.discordbot.game_service.lol.entity.match.Match;
import com.dimetro.discordbot.game_service.lol.entity.match.MatchStatus;
import com.dimetro.discordbot.game_service.lol.entity.player.Player;
import com.dimetro.discordbot.game_service.lol.entity.player.PlayerStats;
import com.dimetro.discordbot.game_service.lol.service.champion.ChampionService;
import com.dimetro.discordbot.game_service.lol.service.match.MatchService;
import com.dimetro.discordbot.game_service.lol.service.player.exception.PlayerStatsUpdateNotAllowedEndedMatchException;

@Service
public class LivePlayerStatsService {
    
    private final PlayerService playerService;
    private final MatchService matchService;
    private final ChampionService championService;
    private final PlayerStatsService playerStatsService;

    public LivePlayerStatsService(PlayerService playerService, MatchService matchService, 
                                  PlayerStatsService playerStatsService, ChampionService championService) {
        this.playerService = playerService;
        this.matchService = matchService;
        this.playerStatsService = playerStatsService;
        this.championService = championService;
    }

    public void updateLivePlayerStats(LiveMatchStatsUpdateRequestDTO dto) {

        Match match = matchService.getMatchByIdOrThrow(dto.getMatchId(), dto.getBotId());

        if (match.getStatus() == MatchStatus.ENDED) {
            throw new PlayerStatsUpdateNotAllowedEndedMatchException(match);
        }

        for (LivePlayerStatsUpdateRequestDTO playerDTO : dto.getPlayerStats()) {
            Player player = playerService.getPlayerByRiotIdOrThrow(playerDTO.getRiotId());
            Champion champion = championService.getChampionByQueryOrThrow(playerDTO.getChampion());
            PlayerStats playerStats = PlayerStats.builder()
                                        .match(match)
                                        .player(player)
                                        .playedChampion(champion)
                                        .kills(playerDTO.getKills())
                                        .assists(playerDTO.getAssists())
                                        .deaths(playerDTO.getDeaths())
                                        .creepScore(playerDTO.getCreepScore())
                                        .build();
            playerStatsService.updateOrCreatePlayerStats(playerStats);
        }

    }

}
