package game.terrain;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

/**
 * A class that represents bare dirt.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 * @author Ian Lai
 * @version 1.0.0
 */
public class Dirt extends Ground {

    private TerrainRule terrainRule = new TerrainRule(TerrainRestriction.DIRT);

    /**
     * Constructor
     */
    public Dirt() {
        super('.', "Dirt");
    }

    /**
     * Determines if an Actor can traverse through Dirt
     * @param actor the Actor to check
     * @return  A boolean value representing if an Actor can traverse through Dirt
     */
    @Override
    public boolean canActorEnter(Actor actor){
        return terrainRule.canActorEnter(actor);
    }
}