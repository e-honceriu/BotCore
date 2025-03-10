package com.dimetro.discordbot.securityservice.security.core.dto.request.apikey;

import java.util.UUID;

import com.dimetro.discordbot.securityservice.security.core.dto.request.apikey.validator.GenerateApiKeyRequestDTOConstraint;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@GenerateApiKeyRequestDTOConstraint
public class GenerateApiKeyRequestDTO {
    
    private String botName;
    private UUID botId;

}
