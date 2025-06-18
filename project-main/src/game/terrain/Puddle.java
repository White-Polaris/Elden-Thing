package game.terrain;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.action.ConsumeWaterAction;

/**
 * A class that represents a random puddle of water.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * @author Ian Lai
 * @author Junyan
 * @version 1.0.0
 */
public class Puddle extends Ground {

    private TerrainRule terrainRule = new TerrainRule(TerrainRestriction.PUDDLE);

    /**
     * Constructor
     */
    public Puddle() {
        super('~', "Puddle");
        this.addCapability(TerrainProperty.NON_BURNABLE);
        this.addCapability(TerrainProperty.BODY_OF_WATER);
    }

    /**
     * This one is actor can consume the water; add by: Junyan
     *
     * @param actor     the Actor acting
     * @param location  the current Location
     * @param direction the direction of the Ground from the Actor
     * @return  an actions list which is allowable to do it
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        // If the actor stands on the puddle, he can drink water
        if (location.containsAnActor() && location.getActor() == actor) {
            actions.add(new ConsumeWaterAction(this, location));
        }
        return actions;
    }

    /**
     * Determines if an Actor can traverse through a Puddle
     * @param actor the Actor to check
     * @return  A boolean value representing if an Actor can traverse through a Puddle
     */
    @Override
    public boolean canActorEnter(Actor actor){
        return terrainRule.canActorEnter(actor);
    }
}
