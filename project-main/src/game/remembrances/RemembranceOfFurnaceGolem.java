package game.remembrances;

import edu.monash.fit2099.engine.actors.Actor;
import game.weapons.FurnaceEngine;
import game.characters.SuspiciousTrader;
import game.effect.CrimsonTearEffect;

/**
 * Represents the "Remembrance of the Furnace Golem".
 * <p>
 * When traded, this remembrance grants the player the Furnace Engine weapon and
 * adds a healing effect (Crimson Tear).
 * </p>
 *
 * @author Yuhang
 * @version 1.0.0
 */
public class RemembranceOfFurnaceGolem extends Remembrance {

    /**
     * Constructor for RemembranceOfFurnaceGolem.
     */
    public RemembranceOfFurnaceGolem() {
        super("Remembrance of the Furnace Golem", '*');
    }

    /**
     * Apply the effects of trading this remembrance, granting a weapon and a healing effect.
     *
     * @param trader The Suspicious Trader.
     * @param actor  The player character.
     */
    @Override
    public void applyTradeEffect(SuspiciousTrader trader, Actor actor) {
        // Grant the Furnace Engine weapon to the player
        actor.addItemToInventory(new FurnaceEngine());

        // Add the Crimson Tear healing effect to the player
        actor.addStatusEffect(new CrimsonTearEffect());

        System.out.println("You have traded the Remembrance of the Furnace Golem and received the Furnace Engine!");

    }
}
