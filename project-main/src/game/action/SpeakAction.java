package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.characters.merchant.MerchantNPC;


/**
 * The SpeakAction class that merchant uses to greet or notify user when they are out of items
 */
public class SpeakAction extends Action {
    private final MerchantNPC merchantNPC;

    /**
     * Constructor for SpeakAction
     * @param merchant the merchant to carry out the SpeakAction
     */
    public SpeakAction( MerchantNPC merchant ){
        this.merchantNPC = merchant;
    }

    /**
     * Checks if the merchant is out of sale
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return an out of sale message or greeting
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (!this.merchantNPC.outofsale()) {
            return this.merchantNPC.greet();
        }
        return "\n";
    }

    /**
     * Description of option to talk to the merchant
     * @param actor The actor performing the action.
     * @return a description of the option to talk to this merchant
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Speak to " + this.merchantNPC.toString() ;
    }
}
