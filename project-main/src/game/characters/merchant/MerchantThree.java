package game.characters.merchant;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.action.BuyAction;
import game.action.SpeakAction;
import game.consumables.FlaskOfHealing;
import game.consumables.ShadowtreeFragment;
import game.weapons.GreatKnife;
import game.weapons.ShortSword;

import java.util.ArrayList;

import static game.characters.PlayerActorAttribute.STRENGTH;
import static game.weapons.weaponarts.WeaponArtsEnum.LIFESTEAL;

/**
 * A MerchantNPC child-class that contains a unique inventory of items to be purchased by the player
 */
public class MerchantThree extends Actor implements MerchantNPC {

    private final ArrayList<Item> inventory = new ArrayList<>();

    /**
     * Constructor for this merchants inventory and name
     */
    public MerchantThree() {
        super("Patches the Belarut Tower Merchant", '$', 0);
        ShortSword lifeStealSS = new ShortSword();
        GreatKnife lifeStealGK = new GreatKnife();
        lifeStealSS.addCapability(LIFESTEAL);
        lifeStealGK.addCapability(LIFESTEAL);

        this.inventory.add ( new ShadowtreeFragment() );
        this.inventory.add( lifeStealSS );
        this.inventory.add( lifeStealGK );
        this.inventory.add( new FlaskOfHealing() );

    }

    /**
     * Merchants action per-turn (Merchant does not have any behaviour other than idle)
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return DoNothingAction()
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * List of BuyAction for items to the player
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a list of purchasable items from the merchant by the player
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        if (otherActor.hasAttribute(STRENGTH)){
            SpeakAction speakAction = new SpeakAction(this);
            System.out.println ( speakAction.execute(otherActor, map) ) ;
            for (Item item : this.inventory){
                actions.add( new BuyAction(otherActor, this, item) );
            }
        }

        return actions;
    }

    /**
     * Greeting message from this particular merchant
     * @return a unique greeting message
     */
    @Override
    public String greet() {
        return this.name +": 'Hail, Tarnished. Observe my weapon and the arts it bestows upon me.' ";
    }

    /**
     * Checks if the merchant is out of sale
     * @return true if out of sale, false otherwise
     */
    @Override
    public Boolean outofsale() {
        if ( this.inventory.isEmpty() ){
            System.out.println( this.name + ": 'It seems I have nothing further to bestow upon you.' " );
            return true;
        }
        return false;
    }

    /**
     * Purchasing logic for the merchant and player
     * @param item items to be removed from the merchant and added to the player
     * @param player the player's inventory for the item to be added
     */
    @Override
    public void purchase( Item item, Actor player ){
        this.inventory.remove( item );
        player.addItemToInventory( item );
    }

}