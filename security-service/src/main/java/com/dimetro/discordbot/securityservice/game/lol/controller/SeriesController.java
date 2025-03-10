package com.dimetro.discordbot.securityservice.game.lol.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dimetro.discordbot.securityservice.game.lol.service.SeriesService;

@RestController
@RequestMapping("/api/game/lol/series")
public class SeriesController {
    
    private final SeriesService seriesService;

    @Autowired
    public SeriesController(SeriesService seriesService) {
        this.seriesService = seriesService;
    }

    @PostMapping
    public ResponseEntity<?> createSeries(@RequestBody Object requestBody,
                                          @RequestHeader(name = "X-API-KEY", required = true) String apiKey) {
        return seriesService.createSeries(requestBody, apiKey);
    }

    @GetMapping
    public ResponseEntity<?> getSeries(@RequestParam(name = "seriesId", required = true) UUID seriesId,
                                       @RequestHeader(name = "X-API-KEY", required = true) String apiKey) {
        return seriesService.getSeries(seriesId, apiKey);
    }

}
