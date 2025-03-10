package com.dimetro.discordbot.musicservice.song.dto.request.download.validator;


import java.util.Objects;

import com.dimetro.discordbot.musicservice.song.dto.request.download.DownloadAudioByIdRequestDTO;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DownloadByIdDTOValidator implements ConstraintValidator<DownloadByIdDTOConstraint, DownloadAudioByIdRequestDTO> {
    
    @Override 
    public boolean isValid(DownloadAudioByIdRequestDTO dto, ConstraintValidatorContext context) {
        return dto != null &&
            (
                Objects.nonNull(dto.getSongId()) ||
                (
                    Objects.nonNull(dto.getPlatform()) &&
                    Objects.nonNull(dto.getExternalId()) &&
                    !dto.getExternalId().isEmpty()
                )
            );
    }

}
