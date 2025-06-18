package game.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.action.ConsumeAction;
import game.characters.PlayerActorAttribute;

/**
 * A Class Representing a Consumable that Boosts stats of the Player
 * @author Ian Lai
 * @version 1.0.0
 */

public class ShadowtreeFragment extends Item implements Consumable{

    /**
     * The name of the consumable
     */
    public static final String NAME = "Shadowtree Fragment";
    /**
     * The character of this consumable displayed in the menu
     */
    public static final char DISPLAY_CHAR = 'e';
    /**
     * The number of Max Hit Points to increase by
     */
    public static final int HIT_POINT_INCREASE = 50;
    /**
     * The number of Mana Points to increase by
     */
    public static final int MANA_POINT_INCREASE = 25;
    /**
     * The number of Strength Points to increase by
     */
    public static final int STRENGTH_POINT_INCREASE = 5;

    /***
     * Constructor.
     */
    public ShadowtreeFragment() {
        super(NAME, DISPLAY_CHAR, true);
    }

    /**
     * The act of consuming a Shadowtree Fragment
     * @param actor The Actor drinking the consumable
     * @param map   The Map of which the Item exists on
     * @return  A String describing the current stats of the Actor after drinking a Shadowtree Fragment
     */
    @Override
    public String consume(Actor actor, GameMap map){
        actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, HIT_POINT_INCREASE);
        actor.modifyAttributeMaximum(BaseActorAttributes.MANA, ActorAttributeOperations.INCREASE, MANA_POINT_INCREASE);
        actor.modifyAttributeMaximum(PlayerActorAttribute.STRENGTH, ActorAttributeOperations.INCREASE,
                STRENGTH_POINT_INCREASE);

        // Shadowtree Fragment is consumed and removed
        actor.removeItemFromInventory(this);

        return String.format("%s consumes a Shadowtree Fragment! " +
                        "Maximum Health increased by %d, " +
                        "Maximum Mana increased by %d, " +
                        "Strength increased by %d.",
                actor, HIT_POINT_INCREASE, MANA_POINT_INCREASE, STRENGTH_POINT_INCREASE);
    }

    /**
     * The name of the consumable
     * @return name of the consumable
     */
    @Override
    public String toString(){
        return NAME;
    }

    /**
     * A List of Actions
     * @param owner the actor that owns the item
     * @return  List of Allowable Actions
     */
    @Override
    public ActionList allowableActions(Actor owner){
        ActionList actions = new ActionList();
        actions.add(new ConsumeAction(owner,this));
        return actions;
    }
}
