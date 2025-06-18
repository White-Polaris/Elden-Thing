package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.characters.SuspiciousTrader;
import game.remembrances.Remembrance;

import java.util.List;
import java.util.Scanner;

/**
 * TradeAction class represents the action of trading items between the player and the Suspicious Trader.
 * <p>
 * When executed, the player is prompted to select an item to trade from their inventory, provided it is tradable.
 * Once selected, the item is removed from the player's inventory, and the item's unique trade effect is applied.
 * </p>
 *
 * @author Yuhang
 * @version 1.0.0
 */
public class TradeAction extends Action {

    private SuspiciousTrader trader;
    private List<Item> tradableItems;

    /**
     * Constructor for TradeAction.
     *
     * @param trader        The Suspicious Trader with whom the player is trading
     * @param tradableItems The list of items that can be traded by the player
     */
    public TradeAction(SuspiciousTrader trader, List<Item> tradableItems) {
        this.trader = trader;
        this.tradableItems = tradableItems;
    }


    /**
     * Executes the trading action by prompting the player to select an item for trade.
     * <p>
     * The player is presented with a list of tradable items and given the option to choose
     * one for trading or cancel. If an item is selected, it is removed from the inventory, and
     * the item's specific trade effect is applied via the {@link Remembrance#applyTradeEffect}.
     * </p>
     *
     * @param actor The player actor executing the trade action
     * @param map   The game map on which the action takes place
     * @return a description of the trade action outcome
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Present options to the player for selecting a tradable item
        Display display = new Display();
        display.println("Select an item to trade:");
        for (int i = 0; i < tradableItems.size(); i++) {
            display.println((i + 1) + ". " + tradableItems.get(i));
        }
        display.println("0. Cancel");

        // Get player's choice
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while (choice < 0 || choice > tradableItems.size()) {
            display.println("Enter choice: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                display.println("Invalid input.");
            }
        }

        // Handle cancelation of trade
        if (choice == 0) {
            return actor + " decides not to trade.";
        } else {
            // Execute the trade by removing the item and applying its effect
            Item itemToTrade = tradableItems.get(choice - 1);
            actor.removeItemFromInventory(itemToTrade);
            ((Remembrance) itemToTrade).applyTradeEffect(trader, actor);
            return actor + " trades " + itemToTrade + " with the Suspicious Trader.";
        }
    }

    /**
     * Provides a description of the trade action for menu display.
     *
     * @param actor the actor performing the action
     * @return the description of the action as it appears in the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Trade with Suspicious Trader";
    }
}
