package game.terrain;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.characters.Status;

/**
 * A class that represents burning grounds.
 * Created by:
 * @author Ian Lai
 * @version 1.0.0
 */

public class Fire extends Ground{

    private TerrainRule terrainRule = new TerrainRule(TerrainRestriction.FIRE);
    private int burnTicks = 5;
    int damageAmount = 5;
    private Ground originalTerrain;

    /**
     * Constructor
     * @param originalTerrain The Original Ground that was Burned
     */
    public Fire(Ground originalTerrain){
        super('w', "Fire");
        this.originalTerrain = originalTerrain;
    }

    /**
     * A method that times the amount of turns to burn the ground
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location){
        if (burnTicks <= 0){
            location.setGround(originalTerrain);
        }
        burn(location);
        burnTicks--;
    }

    /**
     * Determines if an Actor can traverse through Fire
     * @param actor the Actor to check
     * @return  A boolean value representing if an Actor can traverse through Fire
     */
    @Override
    public boolean canActorEnter(Actor actor){
        return terrainRule.canActorEnter(actor);
    }

    /**
     * A method that burns an Actor if present on a Fire Surface
     * @param fireGround The Location of a Fire Terrain
     */
    private void burn(Location fireGround){
        if (fireGround.containsAnActor()){
            Actor actorOnFire = fireGround.getActor();
            if (!actorOnFire.hasCapability(Status.BURN_IMMUNITY)){
                actorOnFire.hurt(damageAmount);
                System.out.println(actorOnFire + " is burning and takes " + damageAmount + " damage");
            }
        }
    }
}
