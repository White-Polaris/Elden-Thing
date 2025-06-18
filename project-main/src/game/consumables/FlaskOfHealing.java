package game.consumables;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * A Class representing a Consumable that Heals the Player
 * @author Ian Lai
 * @version 1.0.0
 */

public class FlaskOfHealing extends FlaskItem {

    /**
     * The name of the Consumable
     */
    private static final String NAME = "Flask of Healing";
    /**
     * The amount of points it can heal with one charge
     */
    private static final int HEAL_POINTS = 150;
    /**
     * The act of healing
     */
    private static final String VERB = "heals";
    /**
     * The number of times a Flask of Healing can be used
     */
    private static int charges = 5;
    /**
     * The character representing a Flask of Healing in the menu
     */
    private static final char DISPLAY_CHAR = 'u';

    /**
     * Constructor
     */
    public FlaskOfHealing(){
        super(NAME, DISPLAY_CHAR, VERB, HEAL_POINTS, charges);
    }

    /**
     * A Method that Heals the Actor
     * @param actor The Actor to be buffed
     */
    @Override
    public void applyBuff(Actor actor){
        actor.heal(HEAL_POINTS);
    }

    /**
     * Returns the name of this Consumable
     * @return  the name of this Consumable
     */
    @Override
    public String toString(){
        return NAME;
    }

}
