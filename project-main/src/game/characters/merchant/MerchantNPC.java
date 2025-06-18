package game.characters.merchant;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

/**
 * Interface for all merchant to implement
 */
public interface MerchantNPC {
    String greet();
    Boolean outofsale();
    void purchase(Item item, Actor player);
}
