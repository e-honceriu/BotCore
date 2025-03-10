package com.dimetro.discordbot.game_service.lol.entity.match;

import lombok.*;

import java.util.UUID;

import com.dimetro.discordbot.game_service.lol.entity.match.exception.PlayerNotFoundInMatchException;
import com.dimetro.discordbot.game_service.lol.entity.player.Player;
import com.dimetro.discordbot.game_service.lol.entity.series.GameType;
import com.dimetro.discordbot.game_service.lol.entity.series.RankingType;
import com.dimetro.discordbot.game_service.lol.entity.team.Team;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lol_match_result")
public class MatchResult {
    
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    @OneToOne(mappedBy = "result", cascade = CascadeType.ALL)
    private Match match;

    @OneToOne(cascade = CascadeType.ALL)
    private Team winningTeam;

    @OneToOne(cascade = CascadeType.ALL)
    private Team losingTeam;

    public GameType getGameType() {
        return match.getGameType();
    }

    public RankingType getRankingType() {
        return match.getRankingType();
    }

    public boolean winningTeamHasPlayer(Player player) {
        return winningTeam.hasPlayer(player);
    }

    public boolean losingTeamHasPlayer(Player player) {
        return losingTeam.hasPlayer(player);
    }

    public Integer getWinningTeamAverageElo(GameType gameType) {
        return winningTeam.getAverageElo(gameType);
    }

    public Integer getLosingTeamAverageElo(GameType gameType) {
        return losingTeam.getAverageElo(gameType);
    }

    public Team getPlayerTeam(Player player) {
        if (losingTeamHasPlayer(player)) {
            return losingTeam;
        }
        if (winningTeamHasPlayer(player)) {
            return winningTeam;
        }
        throw new PlayerNotFoundInMatchException(player, match);
    }

}
