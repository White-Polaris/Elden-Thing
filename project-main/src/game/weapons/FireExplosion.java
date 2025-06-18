package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.core.Utility;
import game.terrain.Fire;
import game.terrain.TerrainProperty;

import java.util.ArrayList;

/**
 * A Class representing a FireExplosion
 * @author Ian Lai
 * @version 1.0.0
 */

public class FireExplosion extends IntrinsicWeapon {

    private String name = "Fire Explosion";

    /**
     * Constructor
     */
    public FireExplosion() {
        super(50, "burns and explodes", 100);
    }

    /**
     * A String representing the name of the attack
     * @return  the name of the attack
     */
    @Override
    public String toString(){
        return name;
    }

    /**
     * Method to get a String representing the act of a FireExplosion attack occurring
     * @param attacker the actor who performed the attack
     * @param target   the actor who is the target of the attack
     * @param map      the map on which the attack was executed
     * @return  A String representing the act of a FireExplosion attack occurring
     */
    @Override
    public String attack(Actor attacker, Actor target, GameMap map){
        triggerExplosion(attacker, map);
        String result = "Furnace Golem's stomp attack " + verb + " , resulting in shockwave in the surrounding areas, " +
                "dealing " + damage + " damage";
        return result;
    }

    /**
     * A method that triggers an explosion attack
     * @param attacker  The Actor triggering the Fire Explosion
     * @param map       The current state of GameMap
     */
    private void triggerExplosion(Actor attacker, GameMap map) {
        ArrayList<Location> locations = Utility.getSurroundingLocations(attacker, map, 1);

        for (Location location: locations){
            Ground originalTerrain = location.getGround();
            // Shockwave Damage Logic
            // Ensures the FurnaceGolem attacking does not hurt itself with its own stomp
            if (location.containsAnActor() && location.getActor() != attacker){
                location.getActor().hurt(this.damage);
            }
            // If Ground is Burnable, change to Fire Surface
            if (!location.getGround().hasCapability(TerrainProperty.NON_BURNABLE)){
                location.setGround(new Fire(originalTerrain));
            }
        }
    }
}
