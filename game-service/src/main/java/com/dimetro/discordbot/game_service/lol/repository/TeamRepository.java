package com.dimetro.discordbot.game_service.lol.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dimetro.discordbot.game_service.lol.entity.team.Team;

public interface TeamRepository extends JpaRepository<Team, UUID> {
    
}
