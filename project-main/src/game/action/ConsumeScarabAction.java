package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.characters.Scarab;
import game.effect.TemporaryBoostStatusEffect;

/**
 * This is an action which is ConsumerScarabAction inherit Action; add by: Junyan
 * @author Junyan
 */
public class ConsumeScarabAction extends Action {

    private Scarab scarab;

    /**
     * Constructs a new ConsumeScarabAction with the specified Scarab.
     *
     * @param scarab The Scarab item that will be consumed.
     */
    public ConsumeScarabAction(Scarab scarab) {
        this.scarab = scarab;
    }

    /**
     * Executes the action of consuming the Scarab.
     * Increases the actor's maximum health and mana by specified amounts, applies a temporary boost status effect, and removes the Scarab from the map.
     *
     * @param actor The actor who consumes the Scarab.
     * @param map The game map on which the action is executed.
     * @return A description of the action's effect.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Increase Tarnished's maximum health and maximum mana
        actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, 30);
        actor.modifyAttributeMaximum(BaseActorAttributes.MANA, ActorAttributeOperations.INCREASE, 50);
        // Add a status effect and remove this bonus after 10 turns
        actor.addStatusEffect(new TemporaryBoostStatusEffect(10, 30, 50));
        // Remove Scarab from the map
        map.removeActor(scarab);
        return actor + " consumes " + scarab + " and feels empowered!";
    }

    /**
     * Provides a description of the action for display in a menu.
     *
     * @param actor The actor who would consume the Scarab.
     * @return A description of the action suitable for a menu interface.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + scarab;
    }
}
