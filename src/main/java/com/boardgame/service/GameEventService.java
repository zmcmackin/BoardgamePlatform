package com.boardgame.service;

import com.boardgame.bo.events.GameEvent;
import com.boardgame.dto.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameEventService {
    Logger logger = LoggerFactory.getLogger(GameEventService.class);

    @Autowired
    GameService gameService;

    public Game performEvent(Game game, GameEvent event, Object owner){
        if(event.id==null){
            event.id = UUID.randomUUID().toString();
        }
        if(owner!=null){
            event.owner = owner;
        }

        Object eventRules = game.rules.get(event.eventName);

        logger.info("Event :{}\nEvent Rules : {}",event,eventRules);


        //TODO DECIDE STRUCTURE FOR GAME RULES OBJECT
        //TODO DECIDE HOW GAME OBJECTS WILL INTERACT WITH GAME SERVICES (JS -> RHINO, CONFIGURATION, PRE-WRITTEN?)

        //GAME RULES SHOULD BE A SERIES OF EVENT LISTENERS

        //GAME RULES CAN INTERACT WITH ANY PROVIDED SERVICE (IE BOARDSERVICE)

        //GAME RULES CAN CAUSE OTHER GAME RULES TO TRIGGER (ie END_TURN CAN CAUSE START_TURN FOR ANOTHER PLAYER)

        //GAME RULES CAN ADD EVENT LISTENERS ON THE FLY, AND CAN REMOVE THEMSELVES OR OTHER GAME RULES

        game.gameEventHistory.add(event);

        return gameService.save(game);
    }
}
