package com.dimetro.discordbot.securityservice.util.exception;

import org.springframework.http.HttpStatus;

public class UnexpectedRestClientException extends RestServiceClientException{

    public UnexpectedRestClientException() {
        super(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "An internal server error occurred during the request execution",
            "An unexpected error occurred while making the request"
        );
    }
    public UnexpectedRestClientException(Throwable cause) {
        super(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "An internal server error occurred during the request execution (check cause for more info)",
            "An unexpected error occurred while making the request",
            cause
        );
    }
}
