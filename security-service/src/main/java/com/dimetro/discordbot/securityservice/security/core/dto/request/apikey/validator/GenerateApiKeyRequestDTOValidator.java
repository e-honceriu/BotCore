package com.dimetro.discordbot.securityservice.security.core.dto.request.apikey.validator;

import java.util.Objects;

import com.dimetro.discordbot.securityservice.security.core.dto.request.apikey.GenerateApiKeyRequestDTO;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GenerateApiKeyRequestDTOValidator implements ConstraintValidator<GenerateApiKeyRequestDTOConstraint, GenerateApiKeyRequestDTO> {
    
    @Override
    public boolean isValid(GenerateApiKeyRequestDTO dto, ConstraintValidatorContext context) {
        return dto != null &&
            (
                Objects.nonNull(dto.getBotId()) || Objects.nonNull(dto.getBotName())
            );
    }

}
