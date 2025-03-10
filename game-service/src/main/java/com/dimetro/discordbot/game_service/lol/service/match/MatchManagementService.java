package com.dimetro.discordbot.game_service.lol.service.match;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimetro.discordbot.game_service.lol.dto.request.match.CreateMatchRequestDTO;
import com.dimetro.discordbot.game_service.lol.dto.response.match.MatchResponseDTO;
import com.dimetro.discordbot.game_service.lol.entity.match.Match;
import com.dimetro.discordbot.game_service.lol.entity.series.Series;
import com.dimetro.discordbot.game_service.lol.service.series.SeriesService;


@Service
public class MatchManagementService {
    
    private final MatchService matchService;
    private final SeriesService seriesService;

    @Autowired
    public MatchManagementService(MatchService matchService, SeriesService seriesService) {
        this.matchService = matchService;
        this.seriesService = seriesService;
    }

    public MatchResponseDTO createMatch(CreateMatchRequestDTO dto) {

        Series series = seriesService.getSeriesById(dto.getSeriesId(), dto.getBotId());

        Match match = new Match();
        match.setSeries(series);

        return MatchResponseDTO.build(matchService.saveMatch(match));
    }

    public MatchResponseDTO getMatch(UUID id, UUID botId) {
        return MatchResponseDTO.build(matchService.getMatchByIdOrThrow(id, botId));
    }

}
