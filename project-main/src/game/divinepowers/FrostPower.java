package game.divinepowers;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.*;
import game.terrain.TerrainProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents the Frost Divine Power.
 *
 * <p>This power allows the user to create a freezing effect on the target.
 * If the target is on a body of water, they slip and drop all items in their inventory.</p>
 *
 * @author Junyan
 * @vision 1.0.0
 *
 * <p>The FrostPower also allows for the possibility of switching to the WindPower after execution.</p>
 */
public class FrostPower implements DivinePower {

    private final String NAME = "Frost";

    /**
     * Executes the special attack for the Frost Divine Power.
     *
     * @param attacker The actor using the power.
     * @param target   The target actor to be affected by the attack.
     * @param map      The game map containing the actors.
     */
    @Override
    public void executeSpecialAttack(Actor attacker, Actor target, GameMap map) {
        // If target is on a body of water, they slip and drop all items
        Location targetLocation = map.locationOf(target);
        if (targetLocation.getGround().hasCapability(TerrainProperty.BODY_OF_WATER)) {
            List<Item> inventory = new ArrayList<>(target.getItemInventory());
            // For-loop to get all the inventory with player
            for (Item item : inventory) {
                target.removeItemFromInventory(item);
                targetLocation.addItem(item);
            }
            System.out.println(target + " slips on the frost and drops all their items!");
        } else {
            System.out.println("Frost divine power has no effect on " + target + "!");
        }
    }

    /**
     * Returns the next divine power in the sequence.
     * In this case, it always switches to WindPower.
     *
     * @param rand Random instance for randomness (not used here).
     * @return the next divine power (WindPower).
     */
    @Override
    public DivinePower getNextDivinePower(Random rand) {
        // Always switch to Wind
        return new WindPower();
    }

    /**
     * Gets the name of the divine power.
     *
     * @return The name of the power, "Frost".
     */
    @Override
    public String getName() {
        return NAME;
    }
}
