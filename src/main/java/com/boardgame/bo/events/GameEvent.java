package com.boardgame.bo.events;

import com.boardgame.bo.GameObjectWithTraits;

public class GameEvent extends GameObjectWithTraits{
    public GameEvent(String eventName) {
        this.eventName = eventName;
    }

    public String id;

    public String eventName;

    public Object owner;

    @Override
    public String toString() {
        return "GameEvent{" +
                "id='" + id + '\'' +
                ", eventName='" + eventName + '\'' +
                ", owner=" + owner +
                "} " + super.toString();
    }
}
