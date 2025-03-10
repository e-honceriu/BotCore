package com.dimetro.discordbot.musicservice.core.exception;

import org.springframework.http.HttpStatus;

public class MusicServiceException extends RuntimeException {

    private final HttpStatus status;
    private final String userMessage;
    private final String devMessage;

    //region constructors
    public MusicServiceException(HttpStatus status, String userMessage, String devMessage) {
        super(devMessage);
        this.status = status;
        this.userMessage = userMessage;
        this.devMessage = devMessage;
    }

    public MusicServiceException(HttpStatus status, String userMessage, String devMessage, Throwable cause) {
        super(devMessage, cause);
        this.status = status;
        this.userMessage = userMessage;
        this.devMessage = devMessage;
    }
    //endregion

    public HttpStatus getStatus() {
        return status;
    }

    public ErrorResponseDTO toDTO() {
        return new ErrorResponseDTO(devMessage, userMessage); 
    }

}
