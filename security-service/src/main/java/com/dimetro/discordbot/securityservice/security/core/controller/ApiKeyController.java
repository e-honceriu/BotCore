package com.dimetro.discordbot.securityservice.security.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dimetro.discordbot.securityservice.security.core.dto.request.apikey.GenerateApiKeyRequestDTO;
import com.dimetro.discordbot.securityservice.security.core.dto.response.apikey.ApiKeyResponseDTO;
import com.dimetro.discordbot.securityservice.security.core.service.apikey.ApiKeyManagementService;

@RestController
@RequestMapping("/api/bot")
public class ApiKeyController {
    
    private final ApiKeyManagementService apiKeyManagementService;
    
    @Autowired
    public ApiKeyController(ApiKeyManagementService apiKeyManagementService) {
        this.apiKeyManagementService = apiKeyManagementService;
    }

    @PostMapping("/generate-api-key")
    public ResponseEntity<ApiKeyResponseDTO> generateApiKey(@RequestBody GenerateApiKeyRequestDTO requestDTO) {
        ApiKeyResponseDTO responseDTO = apiKeyManagementService.generateKey(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

}
