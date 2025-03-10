package com.dimetro.discordbot.securityservice.security.core.service.bot.exception;

import org.springframework.http.HttpStatus;

public class BotAlreadyExistsException extends BotServiceException {

    public BotAlreadyExistsException(String name) {
        super(
            HttpStatus.CONFLICT,
            "Bot with name: " + name + " already exists",
            "Bot with name: " + name + " already exists"
        );
    }
    
}
