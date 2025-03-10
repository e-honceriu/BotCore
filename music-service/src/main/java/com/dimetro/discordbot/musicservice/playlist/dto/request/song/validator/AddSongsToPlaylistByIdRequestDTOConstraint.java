package com.dimetro.discordbot.musicservice.playlist.dto.request.song.validator;

import jakarta.validation.Constraint;

import java.lang.annotation.*;

import jakarta.validation.Payload;

@Constraint(validatedBy = AddSongsToPlaylistByIdRequestDTOValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AddSongsToPlaylistByIdRequestDTOConstraint {
    
    String message() default "playlistId, botId, requesterDiscordId must be provided along with songIDs or songExternalIDs and platform";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
