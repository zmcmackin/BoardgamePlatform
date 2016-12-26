package com.boardgame.service.board;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.boardgame.bo.board.Board;
import com.boardgame.bo.board.Tile;
import com.boardgame.bo.board.Token;
import com.boardgame.enums.board.TokenType;

@Service
public class TokenService {

	private static final String STARTING_TILE = "Starting Tile";

	public Token createStartToken(Board board, Tile tile) {
		return createToken(board, tile, STARTING_TILE, TokenType.GLOBAL, null);
	}

	public Token createToken(Board board, Tile tile, String name, TokenType type, Object owner) {
		Token token = buildToken(name, type, owner);

		String uuid = UUID.randomUUID().toString();

		board.getTokens().put(uuid, token);

		if (tile != null) {
			tile.getTokenIds().add(uuid);
		}
		
		return token;
	}

	private Token buildToken(String name, TokenType type, Object owner) {
		return new Token(name, type, owner);
	}

}
