package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.Random;

/**
 * Represents the Bite weapon of the Dancing Lion.
 */
public class Bite extends IntrinsicWeapon {

    private String name = "Bites";

    /**
     * Constructor.
     */
    public Bite() {
        super(150, "bites", 30); // Damage, verb, chance to hit
    }

    /**
     * Performs the attack action.
     *
     * @param attacker the actor who performed the attack
     * @param target   the actor who is the target of the attack
     * @param map      the map on which the attack was executed
     * @return a string describing the outcome
     */
    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        return super.attack(attacker, target, map);
    }

    /**
     * Gets the name of the divine power.
     *
     * @return The name of the weapon, bites.
     */
    @Override
    public String toString() {
        return name;
    }
}
