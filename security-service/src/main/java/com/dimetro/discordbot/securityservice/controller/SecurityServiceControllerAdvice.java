package com.dimetro.discordbot.securityservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dimetro.discordbot.securityservice.exception.ErrorResponseDTO;
import com.dimetro.discordbot.securityservice.exception.SecurityServiceException;

@ControllerAdvice
public class SecurityServiceControllerAdvice {
    
    @ExceptionHandler(SecurityServiceException.class)
    public ResponseEntity<ErrorResponseDTO> handleLolException(SecurityServiceException ex) {
        return ResponseEntity
            .status(ex.getStatus())
            .body(ex.toDTO());
    }

}
