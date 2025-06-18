package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.Random;

/**
 * FurnaceEngine class, representing a powerful weapon capable of triggering explosions.
 * <p>
 * This weapon can be obtained from the "Remembrance of the Furnace Golem" and is imbued with the power
 * to cause fiery explosions, dealing additional damage and affecting the surrounding area.
 * The chance to trigger an explosion is governed by a set probability.
 * </p>
 *
 * @author Yuhang
 * @version 1.0.0
 */
public class FurnaceEngine extends WeaponItem {

    /** The percentage chance for triggering an explosion with each attack. */
    private static final int EXPLOSION_ODDS = 10;


    /**
     * Constructor for FurnaceEngine.
     */
    public FurnaceEngine() {
        super("Furnace Engine", 'E', 100, "stomps", 5);
    }

    /**
     * Executes an attack with the FurnaceEngine, with a chance to trigger an explosion.
     * <p>
     * When an explosion is triggered, it creates an additional effect that deals extra damage to the target
     * and may impact other actors or elements in the surrounding area.
     * </p>
     *
     * @param attacker the actor using the Furnace Engine
     * @param target   the target of the attack
     * @param map      the map where the attack occurs
     * @return a description of the attack result, including any explosion effect
     */
    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        // Perform the primary attack
        String result = super.attack(attacker, target, map);

        // Determine if an explosion should occur
        if (triggerFireExplosion()) {
            // Trigger explosion effect and append result to attack description
            String fireExplosionResult = new FireExplosion().attack(attacker, target, map);
            result += "\n" + fireExplosionResult;
        }
        return result;
    }

    /**
     * Determines if a fire explosion should be triggered based on EXPLOSION_ODDS.
     *
     * @return true if an explosion is triggered, false otherwise
     */
    private boolean triggerFireExplosion() {
        Random rand = new Random();
        return rand.nextInt(100) < EXPLOSION_ODDS;
    }
}
