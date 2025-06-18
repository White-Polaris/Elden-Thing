package game.terrain;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.characters.Status;
import game.effect.PoisonStatusEffect;

/**
 * A Class that represents a PoisonSwamp
 * @author Ian Lai
 * @version 1.0.0
 */

public class PoisonSwamp extends Ground {

    private TerrainRule terrainRule = new TerrainRule(TerrainRestriction.POISON_SWAMP);

    /**
     * Constructor
     */
    public PoisonSwamp(){
        super('+', "Poison Swamp");
    }

    /**
     * Determines if an Actor can traverse through PoisonSwamps
     * @param actor the Actor to check
     * @return  A boolean value representing if an Actor can traverse through a PoisonSwamp
     */
    @Override
    public boolean canActorEnter(Actor actor){
        return terrainRule.canActorEnter(actor);
    }

    /**
     * A method that constantly Poisons the Ground
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location){
        poisonActor(location);
    }

    /**
     * This method checks if an Actor exists on any instance of PoisonSwamp. If it is not immune to Poison,
     * the Actor on it will be poisoned.
     * @param poisonGround  A Location object representing a ground that is a PoisonSwamp
     */
    private void poisonActor(Location poisonGround){
        if (poisonGround.containsAnActor()){
            Actor poisonedActor = poisonGround.getActor();
            if (!poisonedActor.hasCapability(Status.POISON_IMMUNE)){
                poisonedActor.addStatusEffect(new PoisonStatusEffect());
            }
        }
    }
}
