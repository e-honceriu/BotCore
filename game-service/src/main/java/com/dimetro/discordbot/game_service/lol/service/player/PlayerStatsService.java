package com.dimetro.discordbot.game_service.lol.service.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimetro.discordbot.game_service.lol.entity.match.Match;
import com.dimetro.discordbot.game_service.lol.entity.player.Player;
import com.dimetro.discordbot.game_service.lol.entity.player.PlayerStats;
import com.dimetro.discordbot.game_service.lol.repository.PlayerStatsRepository;
import com.dimetro.discordbot.game_service.lol.service.player.exception.PlayerStatsNotFoundException;

@Service
public class PlayerStatsService {
    
    private final PlayerStatsRepository repository;

    @Autowired
    public PlayerStatsService(PlayerStatsRepository repository) {
        this.repository = repository;
    }

    public List<PlayerStats> getAllByMatch(Match match) {
        return repository.findAllByMatch(match);
    }

    public List<PlayerStats> updateOrCreatePlayerStats(Map<Player, Integer> eloMap, Match match) {

        List<PlayerStats> playerStats = new ArrayList<>();

        Map<Player, PlayerStats> existingStats = getAllByMatch(match).stream()
            .collect(Collectors.toMap(PlayerStats::getPlayer, Function.identity()));
        
        for (Player player : eloMap.keySet()) {
            
            PlayerStats stat = existingStats.get(player);
            
            if (stat == null) {
                stat = new PlayerStats();
                stat.setPlayer(player);
                stat.setMatch(match);
            }
            
            stat.setEloGain(eloMap.get(player));
            playerStats.add(repository.save(stat));
        }
        
        return playerStats;
    }

    public PlayerStats updateOrCreatePlayerStats(PlayerStats playerStats) {
        
        Optional<PlayerStats> dbPlayerStats = repository.findByPlayerAndMatch(playerStats.getPlayer(), playerStats.getMatch());
        
        if (dbPlayerStats.isEmpty()) {
            return repository.save(playerStats);
        }

        PlayerStats savedStats = dbPlayerStats.get();
        if (playerStats.getPlayedChampion() != null) {
            savedStats.setPlayedChampion(playerStats.getPlayedChampion());
        }
        if (playerStats.getEloGain() != null) {
            savedStats.setEloGain(playerStats.getEloGain());
        }
        if (playerStats.getKills() != null) {
            savedStats.setKills(playerStats.getKills());
        }
        if (playerStats.getAssists() != null) {
            savedStats.setAssists(playerStats.getAssists());
        }
        if (playerStats.getDeaths() != null) {
            savedStats.setDeaths(playerStats.getDeaths());
        }
        if (playerStats.getCreepScore() != null) {
            savedStats.setCreepScore(playerStats.getCreepScore());
        }

        return repository.save(savedStats);
    }

    public PlayerStats getStatsByPlayerAndMatchOrThrow(Player player, Match match) {

        Optional<PlayerStats> dbPlayerStats = repository.findByPlayerAndMatch(player, match);

        if (dbPlayerStats.isEmpty()) {
            throw new PlayerStatsNotFoundException(player, match);
        }

        return dbPlayerStats.get();
    }

}
