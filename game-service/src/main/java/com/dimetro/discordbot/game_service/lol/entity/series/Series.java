package com.dimetro.discordbot.game_service.lol.entity.series;

import lombok.*;
import jakarta.persistence.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "lol_series")
public class Series {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private GameType gameType;

    @Enumerated(EnumType.STRING)
    private RankingType rankingType;
    
    @Column(name = "bot_id", nullable = false)
    private UUID botId;

    @Column(name = "guildDiscordId", nullable = false)
    private String guildDiscordId;
}
