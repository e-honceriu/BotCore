package com.dimetro.discordbot.game_service.lol.entity.team;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.dimetro.discordbot.game_service.lol.entity.champion.Champion;
import com.dimetro.discordbot.game_service.lol.entity.champion.ChampionAlreadyBannedException;
import com.dimetro.discordbot.game_service.lol.entity.match.Match;
import com.dimetro.discordbot.game_service.lol.entity.player.Player;
import com.dimetro.discordbot.game_service.lol.entity.series.GameType;
import com.dimetro.discordbot.game_service.lol.entity.team.exception.BansExceededException;
import com.dimetro.discordbot.game_service.lol.entity.team.exception.EmptyTeamException;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lol_team")
public class Team {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

    @ManyToMany
    @JoinTable(
        name = "team_players",
        joinColumns = @JoinColumn(name = "team_id"),
        inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private List<Player> players = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "champion_bans",
        joinColumns = @JoinColumn(name = "team_id"),
        inverseJoinColumns = @JoinColumn(name = "champion_id")
    )
    private Set<Champion> bans = new HashSet<>();

    public void addPlayer(Player player) {
        players.add(player);
    }

    public boolean removePlayer(Player player) {
        return players.remove(player);
    }

    public int getTeamSize() {
        return players.size();
    }

    public boolean hasPlayer(Player player) {
        return players.contains(player);
    }

    public void banChampion(Champion champion, int maxBans) {
        
        if (bans.size() >= maxBans) {
            throw new BansExceededException(this, maxBans);
        }

        if (!bans.add(champion)) {
            throw new ChampionAlreadyBannedException(this, champion);
        }

    }

    public Integer getAverageElo(GameType gameType) {

        if (players.isEmpty()) {
            throw new EmptyTeamException(this);
        }

        Integer totalElo = 0;

        for (Player player : players) {
            totalElo += player.getElo(gameType);
        }

        return totalElo / players.size();

    }

    public boolean hasPlayers(List<Player> players) {
        return this.players.size() == players.size() && this.players.containsAll(players);
    }

    @Override
    public String toString() {
        return String.format(
        "Team(id=%s, matchId=%s, players=%s, bans=%s)",
        id,
        match != null ? match.getId() : "null",
        players,
        bans
    );
    }

}
