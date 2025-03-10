package com.dimetro.discordbot.game_service.lol.service.champion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimetro.discordbot.game_service.lol.entity.champion.Champion;
import com.dimetro.discordbot.game_service.lol.entity.champion.ChampionRole;
import com.dimetro.discordbot.game_service.lol.repository.ChampionRepository;
import com.dimetro.discordbot.game_service.lol.service.champion.exception.ChampionNotFoundException;

@Service
public class ChampionService {
    
    private final ChampionRepository championRepository;

    @Autowired
    public ChampionService(ChampionRepository championRepository) {
        this.championRepository = championRepository;
    }

    public Champion getChampionByQueryOrThrow(String championQuery) {

        Optional<Champion> dbChampion = championRepository.findByName(championQuery.toUpperCase());

        if (dbChampion.isPresent()) {
            return dbChampion.get();
        }
        dbChampion = championRepository.findByRiotId(championQuery.toUpperCase());
        if (dbChampion.isPresent()) {
            return dbChampion.get();
        }
        throw new ChampionNotFoundException(championQuery);
    }

    public List<Champion> getAllChampions() {
        return championRepository.findAll();
    }

    public Map<ChampionRole, List<Champion>> getChampionsByRole() {
        Map<ChampionRole, List<Champion>> championsByRole = new HashMap<>();
        List<Champion> champions = championRepository.findAll();
        for(Champion champion : champions) {
            for (ChampionRole role : champion.getRoles()) {
                championsByRole.computeIfAbsent(role, k -> new ArrayList<>()).add(champion);
            }
        }
        return championsByRole;
    }

}
