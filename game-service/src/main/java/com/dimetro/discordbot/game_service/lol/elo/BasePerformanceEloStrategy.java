package com.dimetro.discordbot.game_service.lol.elo;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.dimetro.discordbot.game_service.lol.entity.match.MatchResult;
import com.dimetro.discordbot.game_service.lol.entity.player.Player;
import com.dimetro.discordbot.game_service.lol.entity.player.PlayerStats;
import com.dimetro.discordbot.game_service.lol.entity.team.Team;
import com.dimetro.discordbot.game_service.lol.service.player.PlayerStatsService;

public class BasePerformanceEloStrategy extends BaseDifficultyEloStrategy {

    protected int performanceScaling;
    protected final PlayerStatsService playerStatsService;

    public BasePerformanceEloStrategy(int baseElo, int maxEloGain, int difficultyMagnitude, int difficultyKFactor,
                                      int performanceScaling, PlayerStatsService playerStatsService) {
        super(baseElo, maxEloGain, difficultyMagnitude, difficultyKFactor);
        this.performanceScaling = performanceScaling;
        this.playerStatsService = playerStatsService;
    }

    private double getRelativePerformance(Player player, MatchResult matchResult, List<PlayerStats> playerStats) {

        Team playerTeam = matchResult.getPlayerTeam(player);


        Map<UUID, PlayerStats> playerStatsMap = playerStats.stream()
                .collect(Collectors.toMap(ps -> ps.getPlayer().getId(), ps -> ps));

        int totalKills = 0;
        int totalDeaths = 0;
        int totalAssists = 0;

        for (Player teamPlayer : playerTeam.getPlayers()) {
            PlayerStats stats = playerStatsMap.get(teamPlayer.getId());
            if (stats != null) {
                totalKills += stats.getKills();
                totalDeaths += stats.getDeaths();
                totalAssists += stats.getAssists();
            }
        }

        PlayerStats playerStat = playerStatsMap.get(player.getId());
        if (playerStat == null) {
            return 0.0; 
        }

        int totalKA = totalKills + totalAssists;
        int playerKA = playerStat.getKills() + playerStat.getAssists();

        double relativeKA = totalKA > 0 ? (double) playerKA / totalKA : 0;
        double relativeD = totalDeaths > 0 ? (double) playerStat.getDeaths() / totalDeaths : 0;

        double factor = relativeKA - relativeD;

        return factor;
    }

    protected Integer computePerformanceBonus(Player player, MatchResult matchResult) {
        
        List<PlayerStats> playerStats = playerStatsService.getAllByMatch(matchResult.getMatch());

        if (playerStats.size() == 0) {
            return 0;
        }

        double performanceFactor = getRelativePerformance(player, matchResult, playerStats);

        return (int) (performanceFactor * this.performanceScaling);
    }

    @Override
    protected Integer computePlayerElo(Player player, MatchResult result) {
        return getBasePlayerElo(player, result) + computeDifficultyBonus(player, result) + computePerformanceBonus(player, result);
    }
    
}
