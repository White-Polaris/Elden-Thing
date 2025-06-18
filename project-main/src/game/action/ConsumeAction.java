package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.consumables.Consumable;

/**
 * Class representing an action to consume a consumable
 * The Actor must have a consumable, e.g.,
 * a Flask Item or Shadowtree Fragment.
 * @author Ian Lai
 * @version 1.0.0
 *
 */
public class ConsumeAction extends Action {

    /**
     * The Actor that is going to drink the consumable
     */
    private Actor actor;
    /**
     * The consumable that is going to be drunk
     */
    private Consumable consumable;

    /**
     * Constructor
     *
     * @param actor the Actor to consume the drink
     * @param consumable the Consumable that is to be drunk
     */
    public ConsumeAction(Actor actor, Consumable consumable){
        this.actor = actor;
        this.consumable = consumable;
    }

    /**
     * Performs a ConsumeAction
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happens after a ConsumeAction that can be displayed to the user
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = consumable.consume(this.actor, map);
        return result;
    }

    /**
     * Describes a ConsumeAction if it is chosen in the option meny
     * @param actor The actor performing the action.
     * @return  the description of a ConsumeAction in the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return this.actor + " consumes " + consumable;
    }
}
