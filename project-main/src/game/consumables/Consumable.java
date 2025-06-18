package game.consumables;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Interface representing Items that are Consumable by an Actor
 * @author Ian Lai
 * @version 1.0.0
 */

public interface Consumable {
    /**
     * Gets a String representing the act of drinking a consumable
     * @param actor The Actor drinking the consumable
     * @param map   The Map of which the Item exists on
     * @return the String representing the act of the Actor drinking a consumable
     */
    String consume(Actor actor, GameMap map);
}
