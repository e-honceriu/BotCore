package com.dimetro.discordbot.musicservice.playlist.dto.request.song.validator;

import java.util.Objects;

import com.dimetro.discordbot.musicservice.playlist.dto.request.song.AddSongsToPlaylistByIdRequestDTO;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AddSongsToPlaylistByIdRequestDTOValidator implements ConstraintValidator<AddSongsToPlaylistByIdRequestDTOConstraint, AddSongsToPlaylistByIdRequestDTO> {
    
    @Override 
    public boolean isValid(AddSongsToPlaylistByIdRequestDTO dto, ConstraintValidatorContext context) {
        return dto != null &&
               (
                Objects.nonNull(dto.getSongIds()) ||
                (
                    Objects.nonNull(dto.getSongExternalIds()) && 
                    Objects.nonNull(dto.getPlatform()))
                )
                && Objects.nonNull(dto.getPlaylistId())
                && Objects.nonNull(dto.getRequesterDiscordId())
                && Objects.nonNull(dto.getBotId());
    }

}
