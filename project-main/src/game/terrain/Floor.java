package game.terrain;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

/**
 * A class that represents the floor inside a building.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 * @author Ian Lai
 */
public class Floor extends Ground {

    private TerrainRule terrainRule = new TerrainRule(TerrainRestriction.FLOOR);

    /**
     * Constructor
     */
    public Floor() {
        super('_', "Floor");
    }

    /**
     * Determines if an Actor can traverse through a Floor
     * @param actor the Actor to check
     * @return  A boolean value representing if an Actor can traverse through a Floor
     */
    @Override
    public boolean canActorEnter(Actor actor){
        return terrainRule.canActorEnter(actor);
    }
}