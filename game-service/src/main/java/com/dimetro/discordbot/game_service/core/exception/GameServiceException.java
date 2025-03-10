package com.dimetro.discordbot.game_service.core.exception;

import org.springframework.http.HttpStatus;

public class GameServiceException extends RuntimeException {
    
    private final HttpStatus status;
    private final String userMessage;
    private final String devMessage;

    public GameServiceException(HttpStatus status, String userMessage, String devMessage) {
        super(devMessage);
        this.status = status;
        this.userMessage = userMessage;
        this.devMessage = devMessage;
    }

    public GameServiceException(HttpStatus status, String userMessage, String devMessage, Throwable cause) {
        super(devMessage, cause);
        this.status = status;
        this.userMessage = userMessage;
        this.devMessage = devMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public ErrorResponseDTO toDTO() {
        return new ErrorResponseDTO(devMessage, userMessage);
    }

}
