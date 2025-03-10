package com.dimetro.discordbot.securityservice.util.exception;

import org.springframework.http.HttpStatus;

public class ParamEncodingException extends ApiUrlBuilderException {
    
    public ParamEncodingException() {
        super(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "An encoding error has occurred while building the request url",
            "An error has occurred!"
        );
    }

}
