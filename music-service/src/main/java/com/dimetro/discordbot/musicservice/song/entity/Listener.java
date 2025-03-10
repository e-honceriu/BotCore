package com.dimetro.discordbot.musicservice.song.entity;

import lombok.*;

import java.util.UUID;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
    name = "listener",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"stream_id", "listener_discord_id"})
    }
)
public class Listener {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "stream_id", nullable = false)
    private Stream stream;

    @Column(name = "listener_discord_id", nullable = false)
    private String listenerDiscordId;

}
