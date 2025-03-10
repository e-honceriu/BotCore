package com.dimetro.discordbot.game_service.lol.draft.exception;

import org.springframework.http.HttpStatus;

public class InsufficientChampionsException extends DraftException {
    
    public InsufficientChampionsException() {
        super(
            HttpStatus.UNPROCESSABLE_ENTITY,
            "Insufficient champions available for the draft!",
            "Draft cannot proceed due to a lack of champions in the pool."
        );
    }

}
