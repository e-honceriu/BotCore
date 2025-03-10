package com.dimetro.discordbot.securityservice.security.authentication.apikey;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class ApiKeyAuthentication extends AbstractAuthenticationToken {
    
    private final String apiKey;

    public ApiKeyAuthentication(String apiKey, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);  
        this.apiKey = apiKey;
        setAuthenticated(true);  
    }

    @Override
    public Object getCredentials() {
        return apiKey;  
    }

    @Override
    public Object getPrincipal() {
        return apiKey;  
    }

}
