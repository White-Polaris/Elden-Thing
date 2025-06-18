package game.divinepowers;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.*;
import game.terrain.TerrainProperty;

import java.util.Random;

/**
 * Represents the Lightning Divine Power.
 *
 * <p>This power allows the user to create a powerful lightning strike that deals a set amount of damage
 * and creates an aftershock in the surroundings.</p>
 *
 * @author Junyan
 * @vision 1.0.0
 *
 * <p>The LightningPower may transition to another divine power after use, with probabilities for switching.</p>
 */
public class LightningPower implements DivinePower {

    private final int DAMAGE = 50;
    private final String NAME = "Lightning";

    /**
     * Executes the special attack for the Lightning Divine Power.
     *
     * @param attacker The actor using the power.
     * @param target   The target actor to be affected by the attack.
     * @param map      The game map containing the actors.
     */
    @Override
    public void executeSpecialAttack(Actor attacker, Actor target, GameMap map) {
        // Create an aftershock of lightning pool within the surroundings
        int baseDamage = DAMAGE;
        // Check attacker around to make a damage
        Location attackerLocation = map.locationOf(attacker);
        for (Exit exit : attackerLocation.getExits()) {
            Location adjacentLocation = exit.getDestination();
            if (adjacentLocation.containsAnActor()) {
                Actor adjacentActor = adjacentLocation.getActor();
                int damage = baseDamage;

                if (adjacentLocation.getGround().hasCapability(TerrainProperty.BODY_OF_WATER)) {
                    damage *= 2;
                }

                adjacentActor.hurt(damage);
                System.out.println(adjacentActor + " is struck by lightning and takes " + damage + " damage!");

                // Give the output and remove actor if actor health is less than 0
                if (!adjacentActor.isConscious()) {
                    adjacentActor.unconscious(attacker, map);
                }
            }
        }
    }

    /**
     * Returns the next divine power in the sequence.
     * Randomly selects between Lightning, Wind, and Frost powers with defined probabilities.
     *
     * @param rand Random instance for determining the next power.
     * @return the next divine power (may be Lightning, Wind, or Frost).
     */
    @Override
    public DivinePower getNextDivinePower(Random rand) {
        int chance = rand.nextInt(100);
        if (chance < 40) { // 40% chance to switch to Frost
            return new FrostPower();
        } else if (chance < 80) { // 40% chance to switch to Wind
            return new WindPower();
        } else { // 20% chance to remain in Lightning
            return new LightningPower();
        }
    }

    /**
     * Gets the name of the divine power.
     *
     * @return The name of the power, "Lightning".
     */
    @Override
    public String getName() {
        return NAME;
    }
}
