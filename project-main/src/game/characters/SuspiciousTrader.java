package game.characters;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.action.TradeAction;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a suspicious trader who interacts with the player by offering trades.
 * <p>
 * The SuspiciousTrader character doesn't engage in direct combat or movement; instead, it allows the player
 * to trade items that are marked as "tradable." When the player approaches, this trader checks if there are any
 * items in the player's inventory that can be exchanged, and if so, offers a {@link TradeAction}.
 * </p>
 *
 * @author Yuhang
 * @version 1.0.0
 */
public class SuspiciousTrader extends Actor {

    private static final String NAME = "Suspicious Trader";

    /**
     * Constructor for SuspiciousTrader, setting its name, display character, and ally capability.
     */
    public SuspiciousTrader() {
        super(NAME, 'ඞ', Integer.MAX_VALUE);
        this.addCapability(Status.TARNISHED_ALLY);
    }

    /**
     * Defines the action taken by the trader each turn, which is to do nothing.
     *
     * @param actions    List of possible actions the trader can take
     * @param lastAction The last action taken
     * @param map        The current game map
     * @param display    The display object for message output
     * @return a DoNothingAction as the trader remains idle each turn
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * Provides actions that the player can perform with the trader, specifically checking
     * if the player has any items that are tradable.
     *
     * @param otherActor the actor interacting with the trader, typically the player
     * @param direction  the direction from the other actor to this trader
     * @param map        the current game map
     * @return a list of actions the player can perform, such as trading
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        // Checks if the interacting actor is the player
        if (otherActor.hasCapability(Status.IS_PLAYER)) {
            List<Item> tradableItems = new ArrayList<>();
            for (Item item : otherActor.getItemInventory()) {
                if (item.hasCapability(Ability.TRADABLE)) {
                    tradableItems.add(item);
                }
            }

            // If tradable items exist, add a TradeAction
            if (!tradableItems.isEmpty()) {
                actions.add(new TradeAction(this, tradableItems));
            }
        }

        return actions;
    }
}
