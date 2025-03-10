package com.dimetro.discordbot.securityservice.security.core.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dimetro.discordbot.securityservice.security.core.entity.Bot;

public interface BotRepository extends JpaRepository <Bot, UUID>{
    
    Optional<Bot> findByName(String name);

    @Query("SELECT b FROM Bot b LEFT JOIN FETCH b.apiKey WHERE b.apiKey.key = :apiKey")
    Optional<Bot> findByKey(@Param("apiKey") String apiKey);

}
