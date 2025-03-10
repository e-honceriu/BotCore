package com.dimetro.discordbot.game_service.lol.service.series;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimetro.discordbot.game_service.lol.entity.series.Series;
import com.dimetro.discordbot.game_service.lol.service.series.exception.*;
import com.dimetro.discordbot.game_service.lol.repository.SeriesRepository;

@Service
public class SeriesService {
 
    private final SeriesRepository seriesRepository;

    @Autowired
    public SeriesService(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

    public Series getSeriesById(UUID id, UUID botId) {
        Optional<Series> dbSeries = seriesRepository.findById(id);

        if (dbSeries.isEmpty()) {
            throw new SeriesNotFoundException(id);
        }

        Series series = dbSeries.get();

        if (!series.getBotId().equals(botId)) {
            throw new SeriesDoesNotBelongToBotException(series);
        }

        return series;
    }

    public Series saveSeries(Series series) {
        return seriesRepository.save(series);
    }

}
