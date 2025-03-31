package com.dimetro.discordbot.securityservice.security.authentication.apikey;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import com.dimetro.discordbot.securityservice.security.authentication.apikey.exception.InvalidApiKeyException;
import com.dimetro.discordbot.securityservice.security.authentication.apikey.exception.NoAuthTokenHeaderFoundException;
import com.dimetro.discordbot.securityservice.security.core.entity.ApiKey;
import com.dimetro.discordbot.securityservice.security.core.service.apikey.ApiKeyService;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ApiKeyAuthenticationService {
    
    private static final String AUTH_TOKEN_HEADER_NAME = "X-API-KEY";
    private final ApiKeyService apiKeyService;

    @Autowired
    public ApiKeyAuthenticationService(ApiKeyService apiKeyService) {
        this.apiKeyService = apiKeyService;
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        
        String apiKey = request.getHeader(AUTH_TOKEN_HEADER_NAME);

        if (apiKey == null) {
            throw new NoAuthTokenHeaderFoundException();
        }

        Optional<ApiKey> dbKey = apiKeyService.getApiKeyByKeySafe(apiKey);

        if (dbKey.isEmpty()) {
            throw new InvalidApiKeyException();
        }

        return new ApiKeyAuthentication(apiKey,  AuthorityUtils.NO_AUTHORITIES);
    }

}
