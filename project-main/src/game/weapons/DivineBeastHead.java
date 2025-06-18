package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.divinepowers.DivinePower;
import game.divinepowers.WindPower;

import java.util.Random;

/**
 * DivineBeastHead class, representing the weapon that grants the Tarnished access to the Divine Beast's powers.
 * <p>
 * This weapon allows the user to switch between different divine powers, each with unique special abilities.
 * It has a chance to transform its divine power during each attack, enhancing the variability of the combat experience.
 * </p>
 * <p>
 * By default, the weapon starts with the power of Wind and has a set transformation rate.
 * </p>
 * <p>
 * Instances of this class use {@link DivinePower} objects to represent the current active divine power, which can be
 * switched randomly to other divine powers during each attack.
 * </p>
 *
 * @author Yuhang
 * @version 1.0.0
 */
public class DivineBeastHead extends WeaponItem {

    /** The current divine power active in the weapon, initially set to WindPower. */
    private DivinePower currentPower = new WindPower();

    /** The percentage chance of transforming to another divine power with each attack. */
    private final int TRANSFORM_RATE = 25;

    /**
     * Constructor for DivineBeastHead.
     */
    public DivineBeastHead() {
        super("Divine Beast Head", '$', 150, "bites", 30);
    }

    /**
     * Executes an attack with the DivineBeastHead weapon.
     * <p>
     * Each time this method is called, there is a chance to switch to a new divine power,
     * making the weapon behave unpredictably. The special attack of the active divine power
     * is also executed as part of the attack.
     * </p>
     *
     * @param attacker the actor who is using the DivineBeastHead
     * @param target   the target actor of the attack
     * @param map      the game map on which the attack occurs
     * @return a string description of the attack, including the transformation and special attack if applicable
     */
    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        Random rand = new Random();
        StringBuilder result = new StringBuilder(super.attack(attacker, target, map));

        // Divine power transformation logic, switches to a new power with a chance based on TRANSFORM_RATE
        if (rand.nextInt(100) < TRANSFORM_RATE) {
            DivinePower previousPower = currentPower;
            currentPower = currentPower.getNextDivinePower(rand);
            result.append("\n").append(attacker).append(" channels the power of ").append(previousPower.getName())
                    .append(" and transforms to ").append(currentPower.getName()).append("!");
        } else {
            result.append("\n").append(attacker).append(" maintains the power of ").append(currentPower.getName()).append(".");
        }

        // Execute the special attack associated with the current divine power
        currentPower.executeSpecialAttack(attacker, target, map);
        result.append("\n").append(currentPower.getName()).append(" divine power special attack executed!");

        return result.toString();
    }
}
