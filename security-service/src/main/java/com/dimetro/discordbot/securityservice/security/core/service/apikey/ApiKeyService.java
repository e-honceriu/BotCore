package com.dimetro.discordbot.securityservice.security.core.service.apikey;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimetro.discordbot.securityservice.security.core.entity.ApiKey;
import com.dimetro.discordbot.securityservice.security.core.entity.Bot;
import com.dimetro.discordbot.securityservice.security.core.repository.ApiKeyRepository;

@Service
public class ApiKeyService {
    
    private final ApiKeyRepository repository;

    @Autowired
    public ApiKeyService(ApiKeyRepository repository) {
        this.repository = repository;
    }

    public Optional<ApiKey> getApiKeyByKeySafe(String key) {
        return repository.findByKey(key);
    }

    private String generateApiKey() {
        byte[] randomBytes = new byte[32];
        new java.security.SecureRandom().nextBytes(randomBytes);
        return java.util.Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }

    public ApiKey generateApiKey(Bot bot) {
        
        Optional<ApiKey> dbApiKey = repository.findByBot(bot);

        ApiKey key;

        if (dbApiKey.isEmpty()) {
            key = new ApiKey();
            bot.setApiKey(key);
            key.setBot(bot);
            key.setKey(generateApiKey());
        } else {
            key = dbApiKey.get();
            key.updateKey(generateApiKey());
        }

        return repository.save(key);
    }

}
