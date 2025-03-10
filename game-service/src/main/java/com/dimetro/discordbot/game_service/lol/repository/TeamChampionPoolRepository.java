package com.dimetro.discordbot.game_service.lol.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dimetro.discordbot.game_service.lol.entity.team.Team;
import com.dimetro.discordbot.game_service.lol.entity.team.TeamChampionPool;

public interface TeamChampionPoolRepository extends JpaRepository<TeamChampionPool, UUID> {
    Optional<TeamChampionPool> findByTeam(Team team);
}
