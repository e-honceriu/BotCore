package com.dimetro.discordbot.securityservice.security.core.dto.response.bot;

import java.util.UUID;

import com.dimetro.discordbot.securityservice.security.core.dto.response.apikey.ApiKeyBotResponseDTO;
import com.dimetro.discordbot.securityservice.security.core.entity.Bot;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BotResponseDTO {
    
    private UUID botId;
    private String name;
    private ApiKeyBotResponseDTO apiKey;
    private boolean isBlocked;

    public static BotResponseDTO build(Bot bot) {
        return BotResponseDTO.builder()
                        .botId(bot.getId())
                        .name(bot.getName())
                        .apiKey(bot.getApiKey() != null ? ApiKeyBotResponseDTO.build(bot.getApiKey()) : null)
                        .isBlocked(bot.isBlocked())
                        .build();

    } 

}
