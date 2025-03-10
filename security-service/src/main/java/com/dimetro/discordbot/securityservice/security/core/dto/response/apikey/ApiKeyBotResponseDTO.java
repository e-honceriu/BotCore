package com.dimetro.discordbot.securityservice.security.core.dto.response.apikey;

import com.dimetro.discordbot.securityservice.security.core.entity.ApiKey;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiKeyBotResponseDTO {
 
    private String apiKey;     

    public static ApiKeyBotResponseDTO build(ApiKey key){
        return ApiKeyBotResponseDTO.builder()
                            .apiKey(key.getKey())
                            .build();
    }

}
