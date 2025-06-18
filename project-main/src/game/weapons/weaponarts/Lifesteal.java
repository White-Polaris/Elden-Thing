package game.weapons.weaponarts;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;

/**
 * Lifesteal class that holds the logic for attacking and Lifestealing
 */
public class Lifesteal extends WeaponArt {
    public Lifesteal(Actor target, String direction, Weapon weapon){
        super(10, target, direction, weapon);
    }

    /**
     * execute method that is called when executing the Lifesteal action
     * @param attacker The actor performing the action.
     * @param map The map the actor is on.
     * @return the result of the action (If the attack successfully used LifeSteal etc.)
     */
    @Override
    public String execute(Actor attacker, GameMap map) {
        int targetHealth = getTarget().getAttribute(BaseActorAttributes.HEALTH);
        String result = super.getWeapon().attack(attacker, super.getTarget(), map);

        if ( attacker.getAttribute(BaseActorAttributes.MANA) >= super.getManaCost() ){
            // Reduction of Mana regardless if the attack hits
            attacker.modifyAttribute(BaseActorAttributes.MANA, ActorAttributeOperations.DECREASE, super.getManaCost());
            // Checks if the attack lands
            if ( getTarget().getAttribute(BaseActorAttributes.HEALTH) < targetHealth ){
                // Flair text to notify player when they have successfully Lifesteal
                System.out.println( attacker + " has Lifesteal 20 HP");
                attacker.heal( 20 );
            }
        } else {
            // Flair text to notify player when they're out of mana
            System.out.println("Out of mana for life steal");
        }

        if (!super.getTarget().isConscious()) {
            result += "\n" + super.getTarget().unconscious(attacker, map);
        }

        return result;
    }

    /**
     *  Description of the weapon that has Lifesteal capability
     * @param actor The actor performing the action.
     * @return a description of the weapon and its capability to Lifesteal
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + super.getTarget() + " at " + super.getDirection() + " with Lifesteal " + super.getWeapon();
    }
}
