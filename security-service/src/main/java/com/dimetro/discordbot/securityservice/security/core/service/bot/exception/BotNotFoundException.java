package com.dimetro.discordbot.securityservice.security.core.service.bot.exception;

import java.util.UUID;

import org.springframework.http.HttpStatus;

public class BotNotFoundException extends BotServiceException {

    public BotNotFoundException(String name) {
        super(
            HttpStatus.NOT_FOUND,
            "Bot with name: " + name + " not found!",
            "Bot with name: " + name + " not found!"
        );
    }

    public BotNotFoundException() {
        super(
            HttpStatus.NOT_FOUND,
            "Bot with name not found!",
            "Bot with name not found!"
        );
    }

    public BotNotFoundException(UUID id) {
        super(
            HttpStatus.NOT_FOUND,
            "Bot with id: " + id + " not found!",
            "Bot with id: " + id + " not found!"
        );
    }

}
    
