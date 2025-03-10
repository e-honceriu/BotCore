package com.dimetro.discordbot.securityservice.security.core.dto.response.apikey;

import com.dimetro.discordbot.securityservice.security.core.entity.ApiKey;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiKeyResponseDTO {
    
    private String apiKey; 
    private String botName;    

    public static ApiKeyResponseDTO build(ApiKey key){
        return ApiKeyResponseDTO.builder()
                            .apiKey(key.getKey())
                            .botName(key.getBot().getName())
                            .build();
    }

}
