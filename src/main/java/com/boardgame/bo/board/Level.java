package com.boardgame.bo.board;

import com.boardgame.bo.GameObject;
import org.springframework.data.annotation.Transient;

import java.util.HashMap;
import java.util.Map;

public class Level extends GameObject {

    @Transient
    Integer level;
    Map<Integer, Map<Integer, Tile>> tiles = new HashMap<>();

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Map<Integer, Map<Integer, Tile>> getTiles() {
        return tiles;
    }

    public void setTiles(Map<Integer, Map<Integer, Tile>> tiles) {
        this.tiles = tiles;
    }
}
