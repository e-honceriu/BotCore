package com.dimetro.discordbot.game_service.core.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dimetro.discordbot.game_service.core.exception.ErrorResponseDTO;
import com.dimetro.discordbot.game_service.core.exception.GameServiceException;
import com.dimetro.discordbot.game_service.core.exception.UnknownException;


@ControllerAdvice
public class GameServiceControllerAdvice {
    
    @ExceptionHandler(GameServiceException.class)
    public ResponseEntity<ErrorResponseDTO> handleLolException(GameServiceException ex) {
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
