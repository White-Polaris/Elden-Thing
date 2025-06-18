package game.terrain;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * This class manages Terrains and if they can be traversed by an Actor
 * given a certain TerrainRestriction Enum
 * @author Ian Lai
 * @version 1.0.0
 */

public class TerrainRule {

    TerrainRestriction terrainRestriction;

    /**
     * Constructor
     * @param terrainRestriction  An Enum value representing a Terrain that cannot be entered
     */
    public TerrainRule(TerrainRestriction terrainRestriction){
        this.terrainRestriction = terrainRestriction;
    }

    /**
     * A method that determines if an Actor is able to traverse through a Terrain
     * @param actor  The Actor traversing through a Terrain
     * @return  A boolean value representing if an Actor can traverse through a Terrain
     */
    public boolean canActorEnter(Actor actor){
        boolean canEnter = true;
        if (!(terrainRestriction == TerrainRestriction.NONE)){
            if (actor.hasCapability(terrainRestriction)){
                canEnter = false;
            }
        }
        return canEnter;
    }
}
