package com.dimetro.discordbot.securityservice.security.core.service.apikey.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.securityservice.security.core.entity.Bot;

public class BlockedBotException extends ApiKeyServiceException {

    public BlockedBotException(Bot bot) {
        super(
            HttpStatus.FORBIDDEN,
            "Bot " + bot.getName() + " is blocked!",
            "The bot is blocked from making any requests!"
        );
    }

}
   