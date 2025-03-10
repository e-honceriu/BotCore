package com.dimetro.discordbot.game_service.lol.service.series;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimetro.discordbot.game_service.lol.dto.request.series.CreateSeriesRequestDTO;
import com.dimetro.discordbot.game_service.lol.dto.response.series.SeriesResponseDTO;
import com.dimetro.discordbot.game_service.lol.entity.series.Series;

import java.util.UUID;

@Service
public class SeriesManagementService {
    
    private final SeriesService seriesService;

    @Autowired
    public SeriesManagementService(SeriesService seriesService) {
        this.seriesService = seriesService;
    }

    public SeriesResponseDTO createSeries(CreateSeriesRequestDTO dto) {
        Series series = seriesService.saveSeries(dto.toSeries());
        return SeriesResponseDTO.build(series);
    }

    public SeriesResponseDTO getSeries(UUID id, UUID botId) {
        return  SeriesResponseDTO.build(seriesService.getSeriesById(id, botId));
    }

}
