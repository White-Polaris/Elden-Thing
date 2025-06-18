package game.divinepowers;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents the Wind Divine Power.
 *
 * <p>This power allows the user to move the target actor to a random adjacent location,
 * simulating a powerful wind blowing the target away.</p>
 *
 * @author Junyan
 * @vision 1.0.0
 *
 * <p>The WindPower may transition to another divine power after execution, depending on probabilities.</p>
 */
public class WindPower implements DivinePower {

    private final String NAME = "Wind";

    /**
     * Executes the special attack for the Wind Divine Power.
     * Moves the target to a random adjacent location if possible.
     *
     * @param attacker The actor using the power.
     * @param target   The target actor to be moved by the wind.
     * @param map      The game map containing the actors.
     */
    @Override
    public void executeSpecialAttack(Actor attacker, Actor target, GameMap map) {
        // Move the target to a random adjacent location around the lion
        List<Location> adjacentLocations = new ArrayList<>();
        // Get adjacent of lion into destination
        for (Exit exit : map.locationOf(attacker).getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(target) && !destination.containsAnActor()) {
                adjacentLocations.add(destination);
            }
        }

        if (!adjacentLocations.isEmpty()) {
            // Use random to check which position will move it
            Random rand = new Random();
            Location newLocation = adjacentLocations.get(rand.nextInt(adjacentLocations.size()));
            map.moveActor(target, newLocation);
            System.out.println(target + " is blown by the wind to " + newLocation + "!");
        } else {
            System.out.println("The wind fails to move " + target + ".");
        }
    }

    /**
     * Returns the next divine power in the sequence.
     * Randomly selects between Frost and Lightning powers with defined probabilities.
     *
     * @param rand Random instance for determining the next power.
     * @return the next divine power (may be Frost or Lightning).
     */
    @Override
    public DivinePower getNextDivinePower(Random rand) {
        int chance = rand.nextInt(100);
        if (chance < 30) { // 30% chance to switch to Frost
            return new FrostPower();
        } else { // 70% chance to switch to Lightning
            return new LightningPower();
        }
    }

    /**
     * Gets the name of the divine power.
     *
     * @return The name of the power, "Wind".
     */
    @Override
    public String getName() {
        return NAME;
    }
}
