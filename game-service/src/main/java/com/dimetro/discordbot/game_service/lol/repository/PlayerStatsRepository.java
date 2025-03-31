package com.dimetro.discordbot.game_service.lol.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dimetro.discordbot.game_service.lol.entity.match.Match;
import com.dimetro.discordbot.game_service.lol.entity.player.Player;
import com.dimetro.discordbot.game_service.lol.entity.player.PlayerStats;

public interface PlayerStatsRepository extends JpaRepository<PlayerStats, UUID> {

    List<PlayerStats> findAllByMatch(Match match);
    Optional<PlayerStats> findByPlayerAndMatch(Player player, Match match);

}
