package game.consumables;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.effect.CrimsonTearEffect;
import game.action.ConsumeAction;

/**
 * This is an item which is CrimsonTear inherit Action and implements Consumable; add by: Junyan
 * @author Junyan
 */
public class CrimsonTear extends Item implements Consumable {

    /**
     * Constructor
     */
    public CrimsonTear() {
        super("Crimson Tear", '*', true);
    }

    /**
     * Consumes the Crimson Tear, triggering the regeneration of health for the actor.
     * The item is removed from the actor's inventory, and a CrimsonTearEffect is applied.
     *
     * @param actor The actor consuming the Crimson Tear.
     * @param map The game map where the consumption occurs.
     * @return A description of the consumption effect.
     */
    @Override
    public String consume(Actor actor, GameMap map) {
        actor.removeItemFromInventory(this);
        actor.addStatusEffect(new CrimsonTearEffect());
        String result = actor + " consumes " + this + " and starts regenerating health in 5 turns";
        return result;
    }

    /**
     * Returns the pick-up action for the Crimson Tear.
     *
     * @param actor The actor who picks up the Crimson Tear.
     * @return A new PickUpAction for this item.
     */
    @Override
    public PickUpAction getPickUpAction(Actor actor) {
        return new PickUpAction(this);
    }

    /**
     * Returns a list of actions that can be performed with the Crimson Tear.
     *
     * @param actor The actor interacting with the Crimson Tear.
     * @return An ActionList containing a ConsumeAction for this item.
     */
    @Override
    public ActionList allowableActions(Actor actor) {
        ActionList actions = new ActionList();
        actions.add(new ConsumeAction(actor, this));
        return actions;
    }
}
