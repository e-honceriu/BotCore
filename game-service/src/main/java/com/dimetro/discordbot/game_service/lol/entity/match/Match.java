package com.dimetro.discordbot.game_service.lol.entity.match;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.dimetro.discordbot.game_service.lol.entity.champion.Champion;
import com.dimetro.discordbot.game_service.lol.entity.match.exception.*;
import com.dimetro.discordbot.game_service.lol.entity.player.Player;
import com.dimetro.discordbot.game_service.lol.entity.series.*;
import com.dimetro.discordbot.game_service.lol.entity.team.Team;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lol_match")
public class Match {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "series_id", nullable = false)
    private Series series;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private MatchStatus status = MatchStatus.TEAM_GENERATION;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Team> teams = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "result_id")
    private MatchResult result;

    public void changeTeams(List<Team> newTeams) {
        newTeams.forEach(team -> team.setMatch(this));
        teams.clear();
        teams.addAll(newTeams);
    }

    public boolean containsTeam(Team team) {
        return teams.contains(team);
    }

    public void banChampion(Player player, Champion champion, int maxBans) {
        
        for (Team team : teams) {
            if (team.hasPlayer(player)) {
                team.banChampion(champion, maxBans);
                return;
            }
        }
        
        throw new PlayerNotFoundInMatchException(player, this);
    }

    public GameType getGameType() {
        return series.getGameType();
    }

    public RankingType getRankingType() {
        return series.getRankingType();
    }

    public boolean hasTeamWithPlayers(List<Player> players) {
        for (Team team : teams) {
            if (team.hasPlayers(players)) {
                return true;
            }
        }
        return false;
    }

    public Team getPlayerTeam(Player player) {
        for (Team team : teams) {
            if (team.hasPlayer(player)) {
                return team;
            }
        }
        
        throw new PlayerNotFoundInMatchException(player, this);
    }

    public Team getPlayerOpposingTeam(Player player) {
        for (Team team : teams) {
            if (!team.hasPlayer(player)) {
                return team;
            }
        }
        throw new PlayerFoundInAllTeamsException(player, this);
    }

}
