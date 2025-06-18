package game.divinepowers;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.Random;

/**
 * Interface representing a Divine Power.
 */
public interface DivinePower {

    /**
     * Executes the special attack associated with this divine power.
     *
     * @param attacker The attacking actor (e.g., the Dancing Lion).
     * @param target   The target actor.
     * @param map      The game map.
     */
    void executeSpecialAttack(Actor attacker, Actor target, GameMap map);

    /**
     * Determines the next divine power based on transition probabilities.
     *
     * @param rand Random object for probability calculations.
     * @return The next DivinePower.
     */
    DivinePower getNextDivinePower(Random rand);

    /**
     * Gets the name of the divine power.
     *
     * @return The name of the divine power.
     */
    String getName();
}
