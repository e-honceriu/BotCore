package com.dimetro.discordbot.securityservice.security.core.dto.request.bot;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBotRequestDTO {
    
    @NotBlank (message = "Name must not be empty")
    private String name;

}
