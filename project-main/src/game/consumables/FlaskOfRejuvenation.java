package game.consumables;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;

/**
 * A Class representing a Consumable that Rejuvenates the Player
 * @author Ian Lai
 * @version 1.0.0
 */

public class FlaskOfRejuvenation extends FlaskItem{

    /**
     * The name of the Consumable
     */
    private static final String NAME = "Flask of Rejuvenation";
    /**
     * The number of Mana Points regained in one charge
     */
    private static final int MANA_POINTS = 100;
    /**
     * The act of regaining mana
     */
    private static final String VERB = "rejuvenates";
    /**
     * The number of times a Flask of Rejuvenation can be used
     */
    private static int charges = 3;
    /**
     * The character representing a Flask of Rejuvenation in the menu
     */
    private static final char DISPLAY_CHAR = 'o';

    /**
     * Constructor
     */
    public FlaskOfRejuvenation(){
        super(NAME, DISPLAY_CHAR, VERB, MANA_POINTS, charges);
    }

    /**
     * Rejuvenates the Actor
     * @param actor The Actor to be buffed
     */
    @Override
    public void applyBuff(Actor actor){
        actor.modifyAttribute(BaseActorAttributes.MANA, ActorAttributeOperations.INCREASE, MANA_POINTS);
    }

    /**
     * Returns the name of this consumable
     * @return the name of this consumable
     */
    @Override
    public String toString(){
        return NAME;
    }
}
