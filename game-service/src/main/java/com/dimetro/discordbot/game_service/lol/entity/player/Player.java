package com.dimetro.discordbot.game_service.lol.entity.player;

import jakarta.persistence.*;
import lombok.*;

import java.util.Map;
import java.util.UUID;

import com.dimetro.discordbot.game_service.lol.entity.player.exception.PlayerEloNotFoundException;
import com.dimetro.discordbot.game_service.lol.entity.series.GameType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name="lol_player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="discord_id", nullable = false, unique = false)
    private String discordId;

    @Column(name="riot_id", nullable = true, unique = false)
    private String riotId;

    @OneToOne(mappedBy = "player", cascade = CascadeType.ALL)
    @JoinColumn(name = "player_elo_id", nullable = false, unique = true)
    private PlayerElo playerElo;

    @Column(name = "bot_id", nullable = false)
    private UUID botId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id.equals(player.getId());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public Integer getElo(GameType gameType) {

        if (playerElo == null) {
            throw new PlayerEloNotFoundException(this);
        }

        return playerElo.getElo(gameType);
    }

    public void updateElo(GameType gameType, Integer eloGain) {
        playerElo.updateElo(gameType, eloGain);
    }

    public void updateElo(Map<GameType, Integer> eloMap) {
        playerElo.updateElo(eloMap);
    }

}
