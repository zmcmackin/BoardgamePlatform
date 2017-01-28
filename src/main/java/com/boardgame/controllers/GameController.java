package com.boardgame.controllers;

import java.util.ArrayList;
import java.util.Map;

import javax.ws.rs.BadRequestException;

import com.boardgame.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.boardgame.dao.GameRepository;
import com.boardgame.dao.UserRepository;
import com.boardgame.dto.Game;
import com.boardgame.dto.User;

@RestController
public class GameController {

	@Autowired
	protected GameService gameService;


	@RequestMapping("/game/{name}/create")
	public @ResponseBody
	Game createGame(@PathVariable("name") String name, @RequestBody Map<String, Object> rules){
		return gameService.createGame(name, rules);
	}

	//TODO REFACTOR TO SERVICE
	@Autowired private GameRepository gameRepo;
	@Autowired private UserRepository userRepo;

	@RequestMapping("/game/{name}")
	public @ResponseBody Game getGame(@PathVariable("name") String name){
	    return gameRepo.findByName(name);
	}

	//TODO: remove after done with initial setup
	@RequestMapping("/game/{name}/delete")
	public @ResponseBody String deleteGame(@PathVariable("name") String name){
		gameRepo.delete(name);

		return "";
	}

	@RequestMapping("/game/{name}/join")
	public @ResponseBody User joinGame(@PathVariable("name") String name){
		Game game = gameRepo.findByName(name);
		if(game == null){
			throw new BadRequestException(String.format("Game name '%s' does not exist.", name));
		}

		User user = userRepo.save(new User());
		if (game.user_ids == null){
			game.user_ids = new ArrayList<String>();
		}
		game.user_ids.add(user._id);
		gameRepo.save(game);

		return user;
	}
}
