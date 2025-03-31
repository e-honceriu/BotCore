package com.dimetro.discordbot.game_service.lol.entity.player;

import lombok.*;
import jakarta.persistence.*;

import java.util.UUID;

import com.dimetro.discordbot.game_service.lol.entity.champion.Champion;
import com.dimetro.discordbot.game_service.lol.entity.match.Match;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "lol_player_stats")
public class PlayerStats {
    
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    @ManyToOne
    @JoinColumn(name = "played_champion_id", nullable = true)
    private Champion playedChampion;

    @Column(name="elo_gain", nullable = true)
    @Builder.Default
    private Integer eloGain = 0;

    @Column(name= "kills", nullable = true)
    private Integer kills;

    @Column(name= "assists", nullable = true)
    private Integer assists;

    @Column(name = "deaths", nullable = true)
    private Integer deaths;

    @Column(name = "creepScore", nullable = true)
    private Integer creepScore;

    @PrePersist
    public void prePersist() {
        if (eloGain == null) {
            eloGain = 0;
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id=" + id + ", " +
                "playerId=" + (player != null ? player.getId() : null) + ", " +
                "matchId=" + (match != null ? match.getId() : null) + ", " +
                "playedChampion=" + playedChampion + ", " +
                "eloGain=" + eloGain + ", " +
                "kills=" + kills + ", " +
                "assists=" + assists + ", " +
                "deaths=" + deaths + ", " +
                "creepScore=" + creepScore +
                ")";
    }
    
}
