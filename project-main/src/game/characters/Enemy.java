package game.characters;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An abstract class representing an enemy in the game.
 * All enemy types should extend this class.
 */
public abstract class Enemy extends Actor {

    /**
     * Constructor for the Enemy class.
     *
     * @param name        the name of the enemy
     * @param displayChar the character to display for this enemy
     * @param hitPoints   the initial hit points of this enemy
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }

    /**
     * Enemies can define their own allowable actions towards other actors.
     *
     * @param otherActor the actor that might be performing an action on this enemy
     * @param direction  the direction of the other actor
     * @param map        the game map
     * @return the list of allowable actions
     */
    @Override
    public abstract ActionList allowableActions(Actor otherActor, String direction, GameMap map);

    /**
     * Enemies should define their behavior each turn.
     *
     * @param actions    the list of actions the enemy can perform
     * @param lastAction the last action performed by the enemy
     * @param map        the game map
     * @param display    the game display
     * @return the action to perform
     */
    @Override
    public abstract Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display);

}
