package com.dimetro.discordbot.securityservice.security.core.dto.request.apikey.validator;

import jakarta.validation.Constraint;

import java.lang.annotation.*;

import jakarta.validation.Payload;

@Constraint(validatedBy = GenerateApiKeyRequestDTOValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface GenerateApiKeyRequestDTOConstraint {

    String message() default "Bot name or Bot ID must be provided";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
}
