package com.dimetro.discordbot.securityservice.security.core.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dimetro.discordbot.securityservice.security.core.entity.ApiKey;
import com.dimetro.discordbot.securityservice.security.core.entity.Bot;

public interface ApiKeyRepository extends JpaRepository<ApiKey, UUID> {
    
    Optional<ApiKey> findByBot(Bot bot);
    Optional<ApiKey> findByKey(String key);
    
}
