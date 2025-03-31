package com.dimetro.discordbot.game_service.lol.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dimetro.discordbot.game_service.lol.entity.player.Player;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, UUID> {

    Optional<Player> findByDiscordId(String discordId);
    Optional<Player> findByRiotId(String riotId);
    
}
