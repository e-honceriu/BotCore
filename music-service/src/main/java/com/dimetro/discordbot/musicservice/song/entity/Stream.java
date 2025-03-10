package com.dimetro.discordbot.musicservice.song.entity;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stream_request")
public class Stream {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "song_id", nullable = false)
    private Song song;

    @Column(name = "bot_id", nullable = false)
    private UUID botId;

    @Column(name = "requester_discord_id", nullable = false)
    private String requesterDiscordId;

    @Column(name = "guild_discord_id", nullable = false)
    private String guildDiscordId;

    @Column(name = "requested_at", nullable = false)
    private LocalDateTime requestedAt;

}
