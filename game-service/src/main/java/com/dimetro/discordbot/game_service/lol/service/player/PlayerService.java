package com.dimetro.discordbot.game_service.lol.service.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.dimetro.discordbot.game_service.core.exception.UnknownException;
import com.dimetro.discordbot.game_service.lol.entity.player.Player;
import com.dimetro.discordbot.game_service.lol.entity.series.GameType;
import com.dimetro.discordbot.game_service.lol.repository.PlayerRepository;
import com.dimetro.discordbot.game_service.lol.service.player.exception.*;

import jakarta.transaction.Transactional;

@Service
public class PlayerService {
    
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player getPlayerByIdOrThrow(UUID playerId, UUID botId) {

        Optional<Player> dbPlayer = playerRepository.findById(playerId);

        if (dbPlayer.isEmpty()) {
            throw new PlayerNotFoundException(playerId);
        }

        Player player = dbPlayer.get();

        if (!player.getBotId().equals(botId)) {
            throw new PlayerDoesNotBelongToBotException(player);
        }

        return dbPlayer.get();
    }

    public Player getPlayerByDiscordIdOrThrow(String discordId, UUID botId) {

        Optional<Player> dbPlayer = playerRepository.findByDiscordId(discordId);

        if (dbPlayer.isEmpty()) {
            throw new PlayerNotFoundException(discordId, null);
        }

        Player player = dbPlayer.get();

        if (!player.getBotId().equals(botId)) {
            throw new PlayerDoesNotBelongToBotException(player);
        }

        return dbPlayer.get();
    }

    public List<Player> getPlayersByIdsOrThrow(List<UUID> playerIds, UUID botId) {

        List<Player> players = new ArrayList<>();

        for(UUID playerId : playerIds) {
            players.add(getPlayerByIdOrThrow(playerId, botId));
        }

        return players;
    }

    public Player savePlayerOrThrow(Player player) {

        try {

            return playerRepository.save(player);

        } catch (DataIntegrityViolationException e) {

            if (e.getCause() instanceof ConstraintViolationException cve) {
                if (cve.getSQLException().getMessage().contains("unique constraint")) {
                    throw new PlayerAlreadyExistsException(player);
                }
            }
            throw new UnknownException(e);
        } catch (Exception e) {
            throw new UnknownException(e);
        }

    }

    @Transactional
    public List<Player> updatePlayerElo(Map<Player, Integer> eloMap, GameType gameType, UUID botId) {
        
        List<Player> updatedPlayers = new ArrayList<>();

        for (Player player : eloMap.keySet()) {
            Player dbPlayer = getPlayerByIdOrThrow(player.getId(), botId);
            Integer eloGain = eloMap.get(player);
            dbPlayer.updateElo(gameType, eloGain);
            updatedPlayers.add(playerRepository.save(dbPlayer));
        }

        return updatedPlayers;
    }

    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    public void deletePlayer(UUID playerId, UUID botId) {
        
        Player player = getPlayerByIdOrThrow(playerId, botId);

        playerRepository.delete(player);
    }

    public Player getPlayerByRiotIdOrThrow(String riotId) {
        Optional<Player> dbPlayer = playerRepository.findByRiotId(riotId);

        if (dbPlayer.isEmpty()) {
            throw new PlayerNotFoundException(null, riotId);
        }

        return dbPlayer.get();
    }

}
