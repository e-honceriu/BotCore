package com.dimetro.discordbot.game_service.core.exception;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponseDTO {
    
    private String devMessage;
    private String userMessage;

}
