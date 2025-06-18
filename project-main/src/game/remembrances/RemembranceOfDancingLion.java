package game.remembrances;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.weapons.DivineBeastHead;
import game.characters.SuspiciousTrader;

/**
 * Represents the "Remembrance of the Dancing Lion".
 * <p>
 * When traded, this remembrance provides the player with the Divine Beast Head weapon
 * and increases their maximum health and mana.
 * </p>
 *
 * @author Yuhang
 * @version 1.0.0
 */
public class RemembranceOfDancingLion extends Remembrance {

    /** The amount of health points to increase when traded. */
    public static final int HIT_POINT_INCREASE = 50;

    /** The amount of mana points to increase when traded. */
    public static final int MANA_POINT_INCREASE = 100;

    /**
     * Constructor for RemembranceOfDancingLion.
     */
    public RemembranceOfDancingLion() {
        super("Remembrance of the Dancing Lion", '*');
    }

    /**
     * Apply the effects of trading this remembrance, granting a weapon and increasing stats.
     *
     * @param trader The Suspicious Trader.
     * @param actor  The player character.
     */
    @Override
    public void applyTradeEffect(SuspiciousTrader trader, Actor actor) {
        // Grant the Divine Beast Head weapon to the player
        actor.addItemToInventory(new DivineBeastHead());

        // Increase maximum health and mana for the player
        actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, HIT_POINT_INCREASE);
        actor.modifyAttributeMaximum(BaseActorAttributes.MANA, ActorAttributeOperations.INCREASE, MANA_POINT_INCREASE);

        System.out.println("You have traded the Remembrance of the Dancing Lion and received the Divine Beast Head!");
    }
}
