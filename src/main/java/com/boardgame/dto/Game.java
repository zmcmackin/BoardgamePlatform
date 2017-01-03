package com.boardgame.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boardgame.bo.events.GameEvent;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.boardgame.bo.GameObject;
import com.boardgame.enums.GameObjectTypes;

@Document
public class Game {
	@Id
	public String _id;

	public String name;

	public List<String> user_ids;

	public Map<GameObjectTypes, GameObject> gameObjects = new HashMap<>();

	@JsonIgnore
	public Map<String, Object> rules;

	@JsonIgnore
	public List<GameEvent> gameEventHistory;

	public Game() {
	}

	public Game(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("Game[id=%s, name='%s']", _id, name);
	}

}
