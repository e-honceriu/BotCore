package com.dimetro.discordbot.game_service.lol.service.match.exception;

import org.springframework.http.HttpStatus;

import com.dimetro.discordbot.game_service.lol.entity.match.Match;

public class MatchDoesNotBelongToBotException extends MatchServiceException {

    public MatchDoesNotBelongToBotException(Match match) {
        super(
            HttpStatus.BAD_REQUEST,
            String.format("Match does not belong to the bot.", match.getId()),
            String.format("Match (ID=%s), attempted to be managed by a bot it does not belong to.", match.getId())
        );
    }
}
