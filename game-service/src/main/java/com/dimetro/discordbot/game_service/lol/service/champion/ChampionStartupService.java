package com.dimetro.discordbot.game_service.lol.service.champion;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.dimetro.discordbot.game_service.lol.entity.champion.Champion;
import com.dimetro.discordbot.game_service.lol.entity.champion.ChampionRole;
import com.dimetro.discordbot.game_service.lol.repository.ChampionRepository;

import com.google.gson.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class ChampionStartupService implements CommandLineRunner {

    private static final String VERSIONS_URL = "https://ddragon.leagueoflegends.com/api/versions.json";
    private static final String CHAMPIONS_URL = "https://ddragon.leagueoflegends.com/cdn/{version}/data/en_US/champion.json";
    
    private static final Logger logger = LoggerFactory.getLogger(ChampionStartupService.class);

    private final ChampionRepository championRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public ChampionStartupService(ChampionRepository championRepository, RestTemplate restTemplate) {
        this.championRepository = championRepository;
        this.restTemplate = restTemplate;   
    }

    public void fetchAndUpdateChampions() {
        try {
            // Fetch the latest version
            String[] versions = restTemplate.getForObject(VERSIONS_URL, String[].class);
            if (versions == null || versions.length == 0) {
                logger.error("Could not retrieve the versions");
                return;
            }
            String latestVersion = versions[0];

            // Fetch the champions data for the latest version
            String championsJson = restTemplate.getForObject(CHAMPIONS_URL, String.class, latestVersion);
            JsonObject championDataJson = JsonParser.parseString(championsJson).getAsJsonObject();
            JsonObject championsData = championDataJson.getAsJsonObject("data");

            logger.info("Searching for new champions to add to the database...");

            for (Map.Entry<String, JsonElement> entry : championsData.entrySet()) {
                String riotId = entry.getKey();
                Optional<Champion> dbChampion = championRepository.findByRiotId(riotId.toUpperCase());

                // If the champion doesn't exist, create and save it
                if (dbChampion.isEmpty()) {
                    JsonObject championData = entry.getValue().getAsJsonObject();
                    String name = championData.get("name").getAsString();

                    // Extract tags and convert to ChampionRole
                    JsonArray championTags = championData.get("tags").getAsJsonArray();
                    List<ChampionRole> roles = new ArrayList<>();
                    for (JsonElement tagElement : championTags) {
                        String tag = tagElement.getAsString().toUpperCase();
                        try {
                            roles.add(ChampionRole.valueOf(tag));
                        } catch (IllegalArgumentException e) {
                            logger.warn("Invalid role tag: " + tag);
                        }
                    }

                    Champion champion = new Champion();
                    champion.setRiotId(riotId.toUpperCase());
                    champion.setName(name.toUpperCase());
                    champion.setRoles(roles);
                    
                    // Save the champion to the database
                    try {
                        championRepository.save(champion);
                        logger.info("Added " + champion.getName() + " to database");
                    } catch (Exception e) {
                        logger.error("Could not save champion " + champion.getName(), e);
                    }
                }
            }
            logger.info("Done searching");
        } catch (JsonSyntaxException | RestClientException e) {
            logger.error("Failed to fetch and update champions: " + e.getMessage(), e);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        fetchAndUpdateChampions();
    }

}
