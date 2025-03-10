package com.dimetro.discordbot.securityservice.security.authentication.apikey.exception;

import org.springframework.http.HttpStatus;

public class NoAuthTokenHeaderFoundException extends ApiKeyAuthenticationServiceException {
    
    public NoAuthTokenHeaderFoundException() {
        super(
            HttpStatus.BAD_REQUEST,
            "No Auth API Token header provided",
            "An error has occurred!"
        );
    }

}
