package com.dimetro.discordbot.musicservice.playlist.dto.request.song.validator;

import jakarta.validation.Constraint;

import java.lang.annotation.*;

import jakarta.validation.Payload;

@Constraint(validatedBy = RemoveSongFromPlaylistRequestDTOValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RemoveSongFromPlaylistRequestDTOConstraint {
    
    String message() default "playlistId, requesterDiscordId, botId must be provided along with songID or position";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
