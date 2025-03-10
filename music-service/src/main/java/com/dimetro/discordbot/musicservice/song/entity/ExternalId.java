package com.dimetro.discordbot.musicservice.song.entity;

import java.util.UUID;

import com.dimetro.discordbot.musicservice.core.entity.SongPlatform;

import jakarta.persistence.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
    name = "song_external_id",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"song_id", "platform"})
    }
)
public class ExternalId {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private SongPlatform platform;

    private String externalId;

    @OneToOne
    @JoinColumn(name = "song_id", referencedColumnName = "id")
    private Song song;

}
