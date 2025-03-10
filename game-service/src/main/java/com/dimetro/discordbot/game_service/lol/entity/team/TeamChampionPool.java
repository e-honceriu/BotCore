package com.dimetro.discordbot.game_service.lol.entity.team;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

import com.dimetro.discordbot.game_service.lol.entity.champion.Champion;

import jakarta.persistence.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="lol_team_champion_pool")
public class TeamChampionPool {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @ManyToMany
    @JoinTable(
        name = "team_champion_pool_champions",
        joinColumns = @JoinColumn(name = "team_champion_pool_id"),
        inverseJoinColumns = @JoinColumn(name = "champion_id")
    )
    private List<Champion> champions = new ArrayList<>();

    public void changeChampions(List<Champion> champions) {
        this.champions.clear();
        this.champions.addAll(champions);
    }
}
