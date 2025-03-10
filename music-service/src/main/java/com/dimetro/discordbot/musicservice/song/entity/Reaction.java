package com.dimetro.discordbot.musicservice.song.entity;

import java.util.UUID;

import jakarta.persistence.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
    name = "reaction",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_discord_id", "bot_id", "song_id"})
    }
)
public class Reaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_discord_id", nullable = false)
    private String userDiscordId;

    @Column(name = "guild_discord_id", nullable = false)
    private String guildDiscordId;

    @Column(name = "bot_id", nullable = false)
    private UUID botId;

    @ManyToOne
    @JoinColumn(name = "song_id", nullable = false)
    private Song song;

    @Enumerated(EnumType.STRING)
    private ReactionType type;

}
