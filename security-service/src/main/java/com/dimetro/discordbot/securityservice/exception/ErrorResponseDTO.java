package com.dimetro.discordbot.securityservice.exception;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponseDTO {
    
    private String devMessage;
    private String userMessage;

}
