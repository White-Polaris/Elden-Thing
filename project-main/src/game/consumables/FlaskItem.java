package game.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.action.ConsumeAction;

/**
 * An Abstract Class representing consumables in a Flask
 * @author Ian Lai
 * @version 1.0.0
 */

public abstract class FlaskItem extends Item implements Consumable{

    /**
     * A String representing the name of the Consumable
     */
    private String name;
    /**
     * An Integer representing the number of times a Consumable can be used
     */
    private int charges;

    /**
     * Constructor
     * @param name  The name of the consumable
     * @param displayChar   The icon of the consumable to display on the map
     * @param verb  The act of drinking the consumable
     * @param points    The number of points in which drinking the consumable may provide
     * @param charges   The number of times a consumable can be used
     */
    public FlaskItem(String name, char displayChar, String verb, int points, int charges){
        super(name, displayChar, true);
        this.name = name;
        this.charges = charges;
    }

    /**
     * A Method that produces a String representing the act of an Actor drinking a Consumable
     * @param actor The Actor drinking the consumable
     * @param gameMap   The Map of which the Item exists on
     * @return  A String representing the Actor drinking the Consumable
     */
    @Override
    public String consume(Actor actor, GameMap gameMap){

        String result = name + " is ";

        if (charges > 0){
            applyBuff(actor);
            decreaseCharge();
            result += " consumed by " + actor + ", " + charges + " charges remaining";
        } else {
            result += "empty";
        }
        return result;
    }

    /**
     * A List of Actions
     * @param actor the actor that owns the item
     * @return A list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor actor){
        ActionList actions = new ActionList();
        actions.add(new ConsumeAction(actor, this));
        return actions;
    }

    /**
     * Applies a specific buff to an Actor (i.e heal, strengthen, etc.)
     * @param actor The Actor to be buffed
     */
    protected abstract void applyBuff(Actor actor);

    /**
     * A method that decrease the number of uses of a consumable
     */
    protected void decreaseCharge(){
        charges--;
    }



}
