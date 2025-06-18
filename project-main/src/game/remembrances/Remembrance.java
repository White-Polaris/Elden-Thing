package game.remembrances;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.characters.Ability;
import game.characters.SuspiciousTrader;

/**
 * Abstract class representing a Remembrance item.
 * <p>
 * Remembrances are special items that can be traded with the Suspicious Trader
 * for unique rewards. Each specific Remembrance grants different effects to the player.
 * </p>
 *
 * @author Yuhang
 * @version 1.0.0
 */
public abstract class Remembrance extends Item {

    /**
     * Constructor for a Remembrance item.
     *
     * @param name        Name of the remembrance item.
     * @param displayChar Character to represent the item on the game map.
     */
    public Remembrance(String name, char displayChar) {
        super(name, displayChar, true);
        // Sets the remembrance item as tradable
        this.addCapability(Ability.TRADABLE);
    }

    /**
     * Apply special effects when this Remembrance item is traded.
     * This method is overridden by subclasses to define specific trade effects.
     *
     * @param trader The Suspicious Trader who receives the trade.
     * @param actor  The player character performing the trade.
     */
    public abstract void applyTradeEffect(SuspiciousTrader trader, Actor actor);
}
