package com.dimetro.discordbot.securityservice.security.core.service.bot;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimetro.discordbot.securityservice.security.core.entity.Bot;
import com.dimetro.discordbot.securityservice.security.core.repository.BotRepository;
import com.dimetro.discordbot.securityservice.security.core.service.bot.exception.*;

@Service
public class BotService {
    
    private final BotRepository repository;

    @Autowired
    public BotService(BotRepository repository) {
        this.repository = repository;
    }

    public Bot getBotByKeyOrThrow(String apiKey) {

        Optional<Bot> dbBot = repository.findByKey(apiKey);

        if (dbBot.isEmpty()) {
            throw new BotNotFoundException();
        }

        return dbBot.get();

    }

    public Bot getByIdOrThrow(UUID botId) {

        Optional<Bot> dbBot = repository.findById(botId);

        if (dbBot.isEmpty()) {
            throw new BotNotFoundException(botId);
        }

        return dbBot.get();
    }

    public Bot getByNameOrThrow(String botName) {

        Optional<Bot> dbBot = repository.findByName(botName);

        if (dbBot.isEmpty()) {
            throw new BotNotFoundException(botName);
        }

        return dbBot.get();
    }

    public Optional<Bot> getBotByNameSafe(String botName) {
        return repository.findByName(botName);
    }

    public Bot saveBot(Bot bot) {
        return repository.save(bot);
    }

    public Bot createBotOrThrow(String botName) {

        Optional<Bot> dbBot = repository.findByName(botName);

        if (dbBot.isPresent()) {
            throw new BotAlreadyExistsException(botName);
        }

        Bot bot = new Bot();
        bot.setName(botName);
        bot.setBlocked(false);
        
        return repository.save(bot);
    }

}
