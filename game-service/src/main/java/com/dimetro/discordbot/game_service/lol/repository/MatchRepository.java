package com.dimetro.discordbot.game_service.lol.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dimetro.discordbot.game_service.lol.entity.match.Match;
import com.dimetro.discordbot.game_service.lol.entity.series.Series;

public interface MatchRepository extends JpaRepository<Match, UUID> {

    List<Match> findAllBySeries(Series series);

    @Query("SELECT m FROM Match m WHERE m.startTime > :time AND m.series.botId = :botId")
    List<Match> findMatchesByBotIdAndAfterTime(
        @Param("botId") UUID botId,
        @Param("time") LocalDateTime time
    );
    
}
