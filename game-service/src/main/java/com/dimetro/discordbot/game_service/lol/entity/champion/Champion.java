package com.dimetro.discordbot.game_service.lol.entity.champion;

import lombok.*;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lol_champion")
public class Champion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "riot_id", unique = true, nullable = false)
    private String riotId;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @ElementCollection(targetClass = ChampionRole.class)
    @CollectionTable(
        name = "lol_champion_role",
        joinColumns = @JoinColumn(name = "champion_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "roles", nullable = false)
    private List<ChampionRole> roles = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Champion champion = (Champion) o;
        return Objects.equals(id, champion.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
