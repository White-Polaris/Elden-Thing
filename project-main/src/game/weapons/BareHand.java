package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * A class representing the intrinsic weapon "Bare Hand".
 * The Bare Hand weapon allows an actor to punch for 25 damage with a 50% hit chance.
 * Created by:
 * @author Yuhang Fei
 */
public class BareHand extends IntrinsicWeapon {

    /**
     * The name of the Bare Hand weapon.
     */
    private final String NAME = "Bare Hand";

    /**
     * Constructor for the Bare Hand weapon.
     * This intrinsic weapon deals 25 damage and has a 50% chance to hit.
     */
    public BareHand() {
        super(25, "punches", 50);
    }

    /**
     * Executes the attack with Bare Hand, dealing damage to the target.
     * This method leverages the {@code attack} method of the {@link IntrinsicWeapon} class.
     *
     * @param attacker The actor performing the attack.
     * @param target   The actor being attacked.
     * @param map      The map where the attack takes place.
     * @return A string describing the result of the attack.
     */
    @Override
    public String attack(Actor attacker, Actor target, GameMap map){
        return super.attack(attacker, target, map);
    }

    /**
     * Returns the name of the Bare Hand weapon.
     *
     * @return The name of the weapon, which is "Bare Hand".
     */
    @Override
    public String toString(){
        return NAME;
    }
}
