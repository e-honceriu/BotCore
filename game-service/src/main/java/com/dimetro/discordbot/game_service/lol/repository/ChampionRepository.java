package com.dimetro.discordbot.game_service.lol.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dimetro.discordbot.game_service.lol.entity.champion.Champion;

public interface ChampionRepository extends JpaRepository<Champion, UUID> {
    Optional<Champion> findByName(String name);
    Optional<Champion> findByRiotId(String riotId);
}
