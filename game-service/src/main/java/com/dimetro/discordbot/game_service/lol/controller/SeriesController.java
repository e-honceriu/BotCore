package com.dimetro.discordbot.game_service.lol.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;

import com.dimetro.discordbot.game_service.lol.dto.request.series.CreateSeriesRequestDTO;
import com.dimetro.discordbot.game_service.lol.dto.response.series.SeriesResponseDTO;
import com.dimetro.discordbot.game_service.lol.service.series.SeriesManagementService;

import jakarta.validation.Valid;

import java.util.UUID;

@RestController
@RequestMapping("/api/lol/series")
public class SeriesController {

    private final SeriesManagementService seriesService;

    public SeriesController(SeriesManagementService seriesService) {
        this.seriesService = seriesService;
    }

    @PostMapping
    public ResponseEntity<SeriesResponseDTO> createSeries(@RequestBody @Valid CreateSeriesRequestDTO dto) {
        return ResponseEntity.ok(seriesService.createSeries(dto));
    }

    @GetMapping
    public ResponseEntity<SeriesResponseDTO> getSeries(
        @RequestParam(name = "seriesId", required = true) UUID seriesId,
        @RequestParam(name = "botId", required = true) UUID botId
    ) {
        return ResponseEntity.ok(seriesService.getSeries(seriesId, botId));
    }

}
