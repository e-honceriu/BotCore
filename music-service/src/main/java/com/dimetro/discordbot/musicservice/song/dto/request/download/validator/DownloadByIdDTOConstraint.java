package com.dimetro.discordbot.musicservice.song.dto.request.download.validator;

import jakarta.validation.Constraint;

import java.lang.annotation.*;

import jakarta.validation.Payload;

@Constraint(validatedBy = DownloadByIdDTOValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DownloadByIdDTOConstraint {
    
    String message() default "songId or platform and externalId must be provided";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
