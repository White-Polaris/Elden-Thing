package game.behaviour;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Interface representing an entity that can observe or detect its enemies
 */

public interface EnemyObservable {
    /**
     * Tells the Observer that an enemy has been found
     *
     * @param enemy The Actor object detected as an enemy
     */
    void enemyDetected(Actor enemy);
}
