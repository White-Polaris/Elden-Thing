package game.weapons.weaponarts;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.characters.PlayerActorAttribute;
import game.core.State;
import game.core.StateManager;

import java.util.Random;

/**
 * A Class that represents a Memento WeaponArt, allows a Weapon to store states of Actors and return them to stored
 * states
 * @author Ian Lai
 * @version 1.0.0
 */

public class Memento extends WeaponArt {

    private static final int HEALTH_LOSS = 5;
    private static final int EXECUTE_RATE = 50;
    private StateManager stateManager;

    /**
     * Default Constructor
     * @param target The target to be attacked
     * @param direction The direction of the attack
     * @param weapon The weapon used in the attack
     * @param stateManager  A StateManager that manages the States of Actors
     */
    public Memento(Actor target, String direction, Weapon weapon, StateManager stateManager){
        super(0, target, direction, weapon);
        this.stateManager = stateManager;
    }

    /**
     * A method that executes the AttackAction of a WeaponItem with Memento WeaponArt
     * @param attacker The actor performing the action.
     * @param map The map the actor is on.
     * @return  The result of the attack
     */
    @Override
    public String execute(Actor attacker, GameMap map) {

        String result = super.getWeapon().attack(attacker, super.getTarget(), map);
        Random rand = new Random();
        int randomInteger = rand.nextInt(100);

        if (attacker.getAttribute(BaseActorAttributes.HEALTH) > HEALTH_LOSS) {
            if (randomInteger > EXECUTE_RATE) {
                hurtActor(attacker);
                mementoSaveAttributes(attacker, map);
            } else if (stateManager.hasSavedState(attacker)) {
                hurtActor(attacker);
                mementoRestoreAttributes(attacker, map);
            } else {
                hurtActor(attacker);
                new Display().println("Memento did not restore, as there is no saved state for " + attacker);
            }
        } else {
            new Display().println("Memento was not triggered, as " + attacker + " is low on health");
        }

        if (!super.getTarget().isConscious()) {
            result += "\n" + super.getTarget().unconscious(attacker, map);
        }

        return result;
    }

    /**
     * A method that returns the description of the choice to attack with this WeaponItem
     * @param actor The actor performing the action.
     * @return  A String to be displayed in the Menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + super.getTarget() + " at " + super.getDirection() + " with Memento " +
                super.getWeapon();
    }

    /**
     * A Private method that saves the State of an Actor
     * @param attacker  The Actor who's state is to be saved
     * @param map   The map the Actor is on
     */
    private void mementoSaveAttributes(Actor attacker, GameMap map) {
        // State of the Actor is Saved
        stateManager.saveState(attacker, map);
        new Display().println("The effects of Memento activate! " + attacker + "'s state is saved");
    }

    /**
     * A Private method that restores the State of an Actor
     * @param attacker The Actor who's state is to be restored
     * @param map   The map the Actor is on
     */
    private void mementoRestoreAttributes(Actor attacker, GameMap map) {

        State restoredState = stateManager.restoreState(attacker);

        if (restoredState != null) {
            attacker.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.UPDATE,
                    restoredState.getHealth());
            attacker.modifyAttribute(BaseActorAttributes.MANA, ActorAttributeOperations.UPDATE,
                    restoredState.getMana());
            attacker.modifyAttribute(PlayerActorAttribute.STRENGTH, ActorAttributeOperations.UPDATE,
                    restoredState.getStrength());

            // Last saved state is restored
            // Not Required to Implement for this Assignment (Restoring the previous Location)
//            try {
//                map.moveActor(attacker, restoredState.getLocation());
//            } catch (IllegalArgumentException error){
//                new Display().println("Another Actor is on this Location, " + attacker + " may not travel.\n");
//            }
            new Display().println("The effects of Memento activate! " + attacker + "'s state is restored to their" +
                    " last point");
//            new Display().println(attacker + " has moved to " + restoredState.getLocation());
        }
    }

    /**
     * A method to deal damage to the Actor upon use of the Memento WeaponArt
     * @param actor  The Actor to be hurt
     */
    private void hurtActor(Actor actor){
        actor.hurt(HEALTH_LOSS);
        new Display().println(actor + " loses " + HEALTH_LOSS + " health due to the effects of Memento");
    }
}

