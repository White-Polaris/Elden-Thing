package game.weapons.weaponarts;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;

import java.util.ArrayList;
import java.util.Random;
import static game.core.Utility.getSurroundingLocations;

/**
 * Quickstep class that holds the logic for attacking and using Quickstep
 */
public class Quickstep extends WeaponArt {
    public Quickstep(Actor target, String direction, Weapon weapon){
        super(0, target, direction, weapon);
    }

    /**
     * execute method that is called when executing the Quickstep action
     * @param attacker The actor performing the action.
     * @param map The map the actor is on.
     * @return the result of the action (If the attacker successfully used QuickStep etc.)
     */
    @Override
    public String execute(Actor attacker, GameMap map) {

        Random rand = new Random();
        String result = super.getWeapon().attack(attacker, super.getTarget(), map);
        // Get all valid location using Ian's Utility class getSurroundingLocations method (Thanks Ian :D)
        ArrayList<Location> locations = getSurroundingLocations(super.getTarget(), map, 1);
        // Pick a random location around the target
        Location randLocation = locations.get( rand.nextInt(locations.size()) );

        // Move the player to the new location also checks if location has an actor
        try{
            if ( randLocation.getGround().canActorEnter(attacker) ){
                map.moveActor(attacker, randLocation);
                System.out.println("Teleported to " + randLocation);
            } else {
                System.out.println("Cannot teleport into impassable terrain !");
            }
        } catch (IllegalArgumentException error) {
            System.out.println("Cannot teleport into enemy !");
        }

        if (!super.getTarget().isConscious()) {
            result += "\n" + super.getTarget().unconscious(attacker, map);
        }


        return result;
    }

    /**
     * Description of the weapon that has QuickStep capability
     * @param actor The actor performing the action.
     * @return a description of the weapon and its capability to QuickStep
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + super.getTarget() + " at " + super.getDirection() + " with Quickstep " + super.getWeapon();
    }
}
