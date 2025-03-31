
package com.dimetro.discordbot.securityservice.exception;

import org.springframework.http.HttpStatus;

public class SecurityServiceException extends RuntimeException {

    private final HttpStatus status;
    private final String userMessage;
    private final String devMessage;

    //region constructors
    public SecurityServiceException(HttpStatus status, String userMessage, String devMessage) {
        super(devMessage);
        this.status = status;
        this.userMessage = userMessage;
        this.devMessage = devMessage;
    }

    public SecurityServiceException(HttpStatus status, String userMessage, String devMessage, Throwable cause) {
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

    public String getUserMessage() {
        return userMessage;
    }

    public String getDevMessage() {
        return devMessage;
    }

}
