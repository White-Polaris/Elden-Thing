package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.effect.PoisonStatusEffect;
import game.characters.Status;

import java.util.Random;

/**
 * A class representing the Sting weapon, which is an intrinsic weapon that Man-Flies use to attack enemies.
 * The Sting can deal damage and has a chance to poison its target.
 * Created by:
 * @author Yuhang Fei
 */
public class Sting extends IntrinsicWeapon {

    /**
     * The probability (in percentage) that the Sting attack will apply poison to the target.
     */
    private final int ODDS_OF_POISON = 30;

    /**
     * The name of the Sting weapon.
     */
    private static final String NAME = "Sting";

    /**
     * Constructor for the Sting weapon.
     * The Sting weapon deals 20 damage, has a hit chance of 25%, and can poison its target.
     */
    public Sting() {
        super(20, "Sting", 25);
    }

    /**
     * Performs the attack with the Sting weapon.
     * In addition to dealing damage, it has a 30% chance of applying the poison effect to the target if the target is not immune.
     *
     * @param attacker The actor performing the attack.
     * @param target   The actor being attacked.
     * @param map      The game map where the attack takes place.
     * @return A string describing the result of the attack.
     */
    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        String result = super.attack(attacker, target, map);
        boolean executeStingAttack = triggerPoison();

        if (executeStingAttack){
            if (!target.hasCapability(Status.POISON_IMMUNE)) {
                PoisonStatusEffect poison = new PoisonStatusEffect(2, 10);
                target.addStatusEffect(poison);
                result += String.format("\n%s is poisoned!", target);
            }
        }

        return result;
    }

    /**
     * Returns the name of the Sting weapon.
     *
     * @return The name of the weapon, which is "Sting".
     */
    @Override
    public String toString(){
        return NAME;
    }

    /**
     * Determines whether the poison effect should be triggered based on a random chance.
     *
     * @return {@code true} if the poison effect should be applied, {@code false} otherwise.
     */
    private boolean triggerPoison() {
        boolean poisonOccurs = true;
        Random rand = new Random();
        if (!(rand.nextInt(100) < this.ODDS_OF_POISON)) {
            return false;
        }
        return poisonOccurs;
    }
}
