package com.dimetro.discordbot.musicservice.core.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dimetro.discordbot.musicservice.core.exception.ErrorResponseDTO;
import com.dimetro.discordbot.musicservice.core.exception.MusicServiceException;
import com.dimetro.discordbot.musicservice.core.exception.UnknownException;

@ControllerAdvice
public class MusicServiceControllerAdvice {
    
    @ExceptionHandler(MusicServiceException.class)
    public ResponseEntity<ErrorResponseDTO> handleLolException(MusicServiceException ex) {
        return ResponseEntity
            .status(ex.getStatus())
            .body(ex.toDTO());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleUnknownException(Exception e) {
        UnknownException unknownException = new UnknownException(e);  
        return ResponseEntity
            .status(unknownException.getStatus())  
            .body(unknownException.toDTO());
    }
    
}
