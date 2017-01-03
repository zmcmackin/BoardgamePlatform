package com.boardgame.enums;

public enum GameEventTypes {
    START_GAME("startGame");


    public final String eventRulesName;

    GameEventTypes(String eventRulesName) {
        this.eventRulesName = eventRulesName;
    }

    public String getEventRulesName() {
        return eventRulesName;
    }
}
