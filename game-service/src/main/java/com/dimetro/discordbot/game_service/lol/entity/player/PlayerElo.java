package com.dimetro.discordbot.game_service.lol.entity.player;

import java.util.EnumMap;
import java.util.Map;

import lombok.*;
import jakarta.persistence.*;

import java.util.UUID;

import com.dimetro.discordbot.game_service.lol.entity.player.exception.*;
import com.dimetro.discordbot.game_service.lol.entity.series.GameType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "player_elo")
public class PlayerElo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name="player_id", nullable = false, unique = true)
    private Player player;

    @ElementCollection
    @MapKeyEnumerated(EnumType.STRING)
    @CollectionTable(
        name = "game_type_elo",
        joinColumns = @JoinColumn(name = "player_elo_id")
    )
    @Column(name = "elo")
    private Map<GameType, Integer> eloMap = new EnumMap<>(GameType.class);

    public PlayerElo(Player player, Integer initialElo) {
        this.player = player;
        this.eloMap = new EnumMap<>(GameType.class);
        
        for (GameType gameType : GameType.values()) {
            eloMap.put(gameType, initialElo);
        }
    }

    public Integer getElo(GameType gameType) {
        
        Integer elo = eloMap.get(gameType);
        
        if (elo == null) {
            throw new PlayerEloNotFoundException(gameType, player);
        }

        return elo;
    }

    public void updateElo(GameType gameType, Integer eloGain) {

        Integer elo = eloMap.get(gameType);
        
        if (elo == null) {
            throw new PlayerEloNotFoundException(gameType, player);
        }

        if ((elo += eloGain) < 0) {
            elo = 0;
        }

        eloMap.put(gameType, elo);
    }

    public void updateElo(Map<GameType, Integer> eloMap) {
        for (GameType gameType : eloMap.keySet()) {
            if (!this.eloMap.containsKey(gameType)) {
                throw new InvalidGameTypeException(gameType);
            }
            this.eloMap.put(gameType, eloMap.get(gameType));
        }
    }

    @Override
    public String toString() {
        return "PlayerElo(" +
           "id=" + id +
           ", playerId=" + player.getId() +
           ", eloMap=" + eloMap +
           ")";
    }

}
