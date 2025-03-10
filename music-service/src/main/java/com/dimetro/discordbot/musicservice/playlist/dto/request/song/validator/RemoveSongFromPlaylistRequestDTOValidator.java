package com.dimetro.discordbot.musicservice.playlist.dto.request.song.validator;

import java.util.Objects;

import com.dimetro.discordbot.musicservice.playlist.dto.request.song.RemoveSongFromPlaylistRequestDTO;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RemoveSongFromPlaylistRequestDTOValidator implements ConstraintValidator<RemoveSongFromPlaylistRequestDTOConstraint, RemoveSongFromPlaylistRequestDTO> {
    
    @Override 
    public boolean isValid(RemoveSongFromPlaylistRequestDTO dto, ConstraintValidatorContext context) {
        return dto != null &&
               (
                Objects.nonNull(dto.getSongId()) ||
                Objects.nonNull(dto.getPosition())
                )
                && Objects.nonNull(dto.getPlaylistId())
                && Objects.nonNull(dto.getRequesterDiscordId())
                && Objects.nonNull(dto.getBotId());
    }

}
