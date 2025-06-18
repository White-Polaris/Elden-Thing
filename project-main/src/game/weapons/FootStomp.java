package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

import java.util.Random;

/**
 * A Class representing a Foot Stomp
 * @author Ian Lai
 * @version 1.0.0
 */

public class FootStomp extends IntrinsicWeapon {

    private String name = "Foot Stomp";
    private final int ODDS_OF_EXPLOSION = 10;

    /**
     * Constructor
     */
    public FootStomp(){
        super(100, "stomps", 5);
    }

    /**
     * Method to get a String representing the act of a FootStomp Attack
     * @param attacker the actor who performed the attack
     * @param target   the actor who is the target of the attack
     * @param map      the map on which the attack was executed
     * @return   A String representing the act of a FireExplosion attack occurring
     */
    @Override
    public String attack(Actor attacker, Actor target, GameMap map){
        String result = super.attack(attacker, target, map);
        String fireExplosionResult;
        boolean executeExplosionAttack = triggerFireExplosion();

        if (executeExplosionAttack){
            fireExplosionResult = new FireExplosion().attack(attacker, target, map);
            result += "\n" + fireExplosionResult;
        }
        return result;
    }

    /**
     * Returns the name of a Foot Stomp
     * @return the name of a Foot Stomp
     */
    @Override
    public String toString(){
        return name;
    }

    /**
     * Method to determine if a FireExplosion triggers
     * @return  a boolean value representing if a FireExplosion Attack will occur
     */
    private boolean triggerFireExplosion(){
        boolean explosionOccurs = true;
        Random rand = new Random();
        if (!(rand.nextInt(100) < this.ODDS_OF_EXPLOSION)) {
            return false;
        }
        return explosionOccurs;
    }
}
