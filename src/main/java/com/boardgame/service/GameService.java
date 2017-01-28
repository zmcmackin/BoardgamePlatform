package com.boardgame.service;

import javax.ws.rs.BadRequestException;

import com.boardgame.bo.events.GameEvent;
import com.boardgame.enums.GameEventTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boardgame.dao.GameRepository;
import com.boardgame.dto.Game;

import java.util.Map;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepo;

	@Autowired
    GameEventService gameEventService;
	
	public Game getGameByName(String name) {
		Game game = gameRepo.findByName(name);

		if (game == null) {
			throw new BadRequestException(String.format("Game name '%s' does not exist.", name));
		}
		
		return game;
	}
	
	public Game save(Game game){
		return gameRepo.save(game);
	}

	public Game createGame(String name, Map<String, Object> rules) {
		if(gameRepo.findByName(name) != null)
			throw new BadRequestException(String.format("Game name '%s' already exist.", name));


		Game game = new Game(name);

		game.rules = rules;

		save(game);

        return gameEventService.performEvent(game, new GameEvent(GameEventTypes.START_GAME.getEventRulesName()), null);
	}
}
