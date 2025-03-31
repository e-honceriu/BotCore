package com.dimetro.discordbot.securityservice.security.authentication.apikey;

import java.io.IOException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dimetro.discordbot.securityservice.security.authentication.apikey.exception.ApiKeyAuthenticationServiceException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ApiKeyAuthenticationFilter extends OncePerRequestFilter {
    
    private final ApiKeyAuthenticationService authService;
    private final Set<String> publicEndpoints;

    @Autowired
    public ApiKeyAuthenticationFilter(ApiKeyAuthenticationService authService, String[] publicEndpoints) {
        this.authService = authService;
        this.publicEndpoints = Set.of(publicEndpoints);
    }

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
        return publicEndpoints.contains(request.getRequestURI());
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        
        try {
            Authentication authentication = authService.getAuthentication(request);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (ApiKeyAuthenticationServiceException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(e.getDevMessage());
            return;
        }

        filterChain.doFilter(request, response);
    }
    
}
