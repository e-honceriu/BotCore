package com.dimetro.discordbot.game_service.lol.service.player;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimetro.discordbot.game_service.lol.config.LolConfig;
import com.dimetro.discordbot.game_service.lol.dto.request.player.CreatePlayerRequestDTO;
import com.dimetro.discordbot.game_service.lol.entity.match.Match;
import com.dimetro.discordbot.game_service.lol.entity.player.Player;
import com.dimetro.discordbot.game_service.lol.entity.player.PlayerElo;
import com.dimetro.discordbot.game_service.lol.entity.player.PlayerStats;
import com.dimetro.discordbot.game_service.lol.dto.request.player.EditPlayerRequestDTO;
import com.dimetro.discordbot.game_service.lol.dto.response.player.PlayerResponseDTO;
import com.dimetro.discordbot.game_service.lol.dto.response.player.PlayerStatsResponseDTO;
import com.dimetro.discordbot.game_service.lol.service.match.MatchService;

@Service
public class PlayerManagementService {
    
    private final PlayerService playerService;
    private final MatchService matchService;
    private final LolConfig config;
    private final PlayerStatsService playerStatsService;

    @Autowired
    public PlayerManagementService(PlayerService playerService, MatchService matchService, 
                                   LolConfig config, PlayerStatsService playerStatsService) {
        this.playerService = playerService;
        this.matchService = matchService;
        this.config = config;
        this.playerStatsService = playerStatsService;
    }

    public PlayerResponseDTO createPlayer(CreatePlayerRequestDTO dto) {

        Player playerEntity = dto.toPlayer();
        PlayerElo playerElo = new PlayerElo(playerEntity, config.getStartingElo());

        playerEntity.setPlayerElo(playerElo);
        Player player = playerService.savePlayerOrThrow(playerEntity);

        return PlayerResponseDTO.build(player);
    }

    public PlayerResponseDTO getPlayer(UUID playerId, UUID botId) {
        Player player = playerService.getPlayerByIdOrThrow(playerId, botId);
        return PlayerResponseDTO.build(player);
    }

    public PlayerResponseDTO getPlayer(String discordId, UUID botId) {
        Player player = playerService.getPlayerByDiscordIdOrThrow(discordId, botId);
        return PlayerResponseDTO.build(player);
    }

    public PlayerResponseDTO editPlayer(EditPlayerRequestDTO dto) {

        Player player = playerService.getPlayerByIdOrThrow(dto.getPlayerId(), dto.getBotId());
        
        if (dto.getDiscordId() != null) {
            player.setDiscordId(dto.getDiscordId());
        }
        if (dto.getRiotId() != null) {
            player.setRiotId(dto.getRiotId());
        }
        if (dto.getElo() != null) {
            player.getPlayerElo().updateElo(dto.getElo());
        }
        
        return PlayerResponseDTO.build(playerService.savePlayer(player));
    }

    public void deletePlayer(UUID playerId, UUID botId) {
        playerService.deletePlayer(playerId, botId);
    }

    public PlayerStatsResponseDTO getPlayerStats(UUID matchId, UUID playerId, UUID botId) {
        Match match = matchService.getMatchByIdOrThrow(matchId, botId);
        Player player = playerService.getPlayerByIdOrThrow(playerId, botId);

        PlayerStats stats = playerStatsService.getStatsByPlayerAndMatchOrThrow(player, match);
        return PlayerStatsResponseDTO.build(stats);
    }

}
