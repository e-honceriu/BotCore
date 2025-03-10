package com.dimetro.discordbot.game_service.lol.service.match;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimetro.discordbot.game_service.lol.entity.match.Match;
import com.dimetro.discordbot.game_service.lol.entity.match.MatchStatus;
import com.dimetro.discordbot.game_service.lol.entity.player.Player;
import com.dimetro.discordbot.game_service.lol.entity.series.Series;

import com.dimetro.discordbot.game_service.lol.repository.MatchRepository;
import com.dimetro.discordbot.game_service.lol.service.match.exception.MatchDoesNotBelongToBotException;
import com.dimetro.discordbot.game_service.lol.service.match.exception.MatchNotFoundException;

@Service
public class MatchService {
    
    private final MatchRepository matchRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public Match getMatchByIdOrThrow(UUID id, UUID botId) {

        Optional<Match> dbMatch = matchRepository.findById(id);

        if (dbMatch.isEmpty()) {
            throw new MatchNotFoundException(id);
        }

        Match match = dbMatch.get();

        if (!match.getSeries().getBotId().equals(botId)) {
            throw new MatchDoesNotBelongToBotException(match);
        }

        return dbMatch.get();
    }

    public List<Match> getMatchesBySeries(Series series) {
        return matchRepository.findAllBySeries(series);
    }

    public Match saveMatch(Match match) {
        return matchRepository.save(match);
    }

    public Match getMatchByPlayersOrThrow(UUID botId, List<Player> teamOrder, List<Player> teamChaos) {
        
        LocalDateTime oneHourAgo = LocalDateTime.now().minusHours(1);
        List<Match> recentMatches = matchRepository.findMatchesByBotIdAndAfterTime(botId, oneHourAgo);

        List<Match> validMatches = recentMatches.stream()
                                        .filter(match -> match.getStatus() != MatchStatus.ENDED)
                                        .filter(match -> match.hasTeamWithPlayers(teamChaos) && match.hasTeamWithPlayers(teamOrder))
                                        .sorted(Comparator.comparing(Match::getStartTime).reversed()) 
                                        .toList();
        
        if (validMatches.isEmpty()) {
            throw new MatchNotFoundException(Stream.concat(teamOrder.stream(), teamChaos.stream()).toList());
        }

        return validMatches.get(0);
    }

}
