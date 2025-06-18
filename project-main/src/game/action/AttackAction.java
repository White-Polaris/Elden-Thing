package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;

/**
 * Class representing an action to attack
 * Note that the attacker must have a weapon, e.g.,
 * an intrinsic weapon or a weapon item.
 * Otherwise, the execute method will throw an error.
 * @author Adrian Kristanto
 * modified by:
 * @author Ian Lai
 * @version 1.0.0
 *
 */
public class AttackAction extends Action {

    /**
     * The Actor that is to be attacked
     */
    private Actor target;

    /**
     * The direction of incoming attack.
     */
    private String direction;

    /**
     * Weapon used for the attack
     */
    private Weapon weapon;

    /**
     * Constructor.
     *
     * @param weapon the Weapon to be used
     * @param target the Actor to attack
     * @param direction the direction where the attack should be performed (only used for display purposes)
     */
    public AttackAction(Actor target, String direction, Weapon weapon) {
        this.target = target;
        this.direction = direction;
        this.weapon = weapon;
    }

    /**
     * Constructor with intrinsic weapon as default
     *
     * @param target the actor to attack
     * @param direction the direction where the attack should be performed (only used for display purposes)
     */
    public AttackAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    /**
     * Performs an AttackAction
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happens after an AttackAction that can be displayed to the user
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (weapon == null) {
            weapon = actor.getIntrinsicWeapon();
        }
        String result = weapon.attack(actor, target, map);
        if (!target.isConscious()) {
            // Adds the gold the enemy "dropped" into player inventory
            actor.addBalance( target.getBalance() );
            try {
                actor.addItemToInventory(target.getItemInventory().get(0));
            } catch (IndexOutOfBoundsException e) {
                // Target has no items to drop; nothing to add to inventory
            }
            result += "\n" + target.unconscious(actor, map);
        }

        return result;
    }

    /**
     * Describes an AttackAction if it is chosen as an option in the menu
     * @param actor The actor performing the action.
     * @return the description of an AttackAction in the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " at " + direction + " with " + (weapon != null ? weapon : actor.getIntrinsicWeapon());
    }
}

