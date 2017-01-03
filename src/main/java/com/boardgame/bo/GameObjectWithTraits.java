package com.boardgame.bo;

import java.util.HashMap;
import java.util.Map;

public abstract class GameObjectWithTraits extends GameObject {
    Map<String, Object> traits = new HashMap<>();

    public Map<String, Object> getTraits() {
        return traits;
    }

    public void setTraits(Map<String, Object> traits) {
        this.traits = traits;
    }

    @Override
    public String toString() {
        return "{" +
                "traits=" + traits +
                "} ";
    }
}
