package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.characters.merchant.MerchantNPC;

import static game.weapons.weaponarts.WeaponArtsEnum.LIFESTEAL;
import static game.weapons.weaponarts.WeaponArtsEnum.QUICKSTEP;

/**
 * BuyAction Action class that handles the purchasing logic of the merchant and player
 */
public class BuyAction extends Action {

    private int price;
    private final Actor player;
    private final MerchantNPC merchantNPC;
    private String itemName;
    private final Item item;

    private final char[] flaskSymbols = {'u', 'o'};
    private final char[] weaponSymbols = {'†', '!'};

    public BuyAction( Actor Player, MerchantNPC merchantNPC, Item item ){
        this.itemName = item.toString();
        this.item = item;
        this.player = Player;
        this.merchantNPC = merchantNPC;
        this.price = 50;

        // Checks if item is a flask or weapon and assigns correct price to it
        this.isFlask(this.item);
        this.isWeapon(this.item);

    }

    /**
     * Checks if the current item object is a flask and assign it the appropriate price
     * @param item the item to be checked
     */
    protected void isFlask(Item item){
        for (char displayChar : this.flaskSymbols ){
            if ( item.getDisplayChar() == displayChar ){
                this.price = 25;
                break;
            }
        }
    }

    /**
     * Checks if the current item object is a weapon and assign it the appropriate price
     * @param item the item to be checked
     */
    protected void isWeapon(Item item){
        for (char displayChar : this.weaponSymbols ){
            if ( item.getDisplayChar() == displayChar ){

                // Checks which weapon it is (short sword is 100 gold more expensive)
                if ( item.getDisplayChar() == '!'){
                    this.price = 200;
                } else {
                    this.price = 100;
                }

                // Checks if the weapon has a weaponart and adds 50 gold if it has Lifesteal, 25 if it has Quickstep
                if ( item.hasCapability(LIFESTEAL) ){
                    this.itemName = this.item.toString() + " with Lifesteal Weapon Art";
                    this.price += 50;
                } else if ( item.hasCapability(QUICKSTEP) ){
                    this.itemName = this.item.toString() + " with Quickstep Weapon Art";
                    this.price += 25;
                }
            }
        }
    }

    /**
     * The main BuyAction logic that handles purchasing item (reducing gold, adding items into player inventory etc.)
     * @param merchant The actor performing the action.
     * @param map The map the actor is on.
     * @return a dialogue notifying the player if the purchase was successful
     */
    @Override
    public String execute(Actor merchant, GameMap map) {
        // Checks if player has enough balance
        if ( this.player.getBalance() >= this.price ){
            // Deducts the player's balance
            this.player.deductBalance( this.price );
            // Remove item from the merchant's inventory and add them into player's inventory
            this.merchantNPC.purchase(this.item, this.player);
            System.out.println("You have purchased " + this.item.toString() + " for " + this.price + " gold.");
            return this.merchantNPC + ": 'A fruitful exchange, indeed.'\n";
        } else {
            return this.merchantNPC + ": 'A lack of gold, I see. You'd best gather more, if you seek my wares.'\n";
        }


    }

    /**
     * Description and gold cost for each item
     * @param actor The actor performing the action.
     * @return the description of the item and its gold cost
     */
    @Override
    public String menuDescription(Actor actor) {
        return "- Buy " + this.itemName + " for " + this.price + " gold ";
    }
}
