package game.behaviour;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.action.AttackAction;
import game.characters.Status;
import game.core.Utility;

import java.util.ArrayList;

/**
 * Class representing a Behaviour that allows an Actor to attack
 * @author Ian Lai
 * @version 1.0.0
 */
public class AttackBehaviour implements Behaviour {

    /**
     * A Status Enum that represents if the Enemy is friendly
     */
    private Status friendlyStatus;
    /**
     * An actor representing the enemy to be attacked
     */
    private Actor enemy;
    /**
     *  An interface that detects if an enemy is present
     */
    private EnemyObservable enemyObserver;

    /**
     * Constructor
     *
     * @param friendlyStatus    An Enum value representing if an Enemy is friendly
     * @param enemyObserver     The interface that detects if an enemy is present, and is able to return an enemy
     */
    public AttackBehaviour(Status friendlyStatus, EnemyObservable enemyObserver){
        this.friendlyStatus = friendlyStatus;
        this.enemyObserver = enemyObserver;
    }

    /**
     * Constructor that initializes the {@code friendlyStatus} of this {@code AttackBehaviour}.
     * This constructor can be used when there is no need for an {@code EnemyObservable}.
     *
     * @param friendlyStatus A {@code Status} Enum representing whether the target is considered friendly to the Actor.
     */
    public AttackBehaviour(Status friendlyStatus) {
        this.friendlyStatus = friendlyStatus;
    }

    /**
     * Returns an AttackAction if an enemy is detected
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return  An AttackAction Object that attacks the enemy
     */
    @Override
    public Action getAction(Actor actor, GameMap map){

        Action action = null;
        Location enemyLocation = getEnemyLocation(actor, map);

        // When Enemy Location exists
        if (enemyLocation != null){
            this.enemy = enemyLocation.getActor();
            // Another Class that Implements EnemyObserver is passed in to observe or obtain details of this enemy
            if (enemyObserver != null){
                enemyObserver.enemyDetected(this.enemy);
            }
            System.out.println(actor + " spots an enemy " + enemy + " in its vicinity, and attacks!");
            action = new AttackAction(enemy, enemyLocation.toString());
        }
        return action;
    }

    /**
     * A Method to return the Location where the enemy is at
     * @param actor  An actor object representing the enemy
     * @param map    The current state of the GameMap
     * @return       The Location of the first enemy detected
     */
    private Location getEnemyLocation(Actor actor, GameMap map) {
        ArrayList<Location> potentialEnemylocations = Utility.getSurroundingLocations(actor, map, 1);

        for (Location location : potentialEnemylocations){
            // Do Not Include the Location of the Actor trying to Attack (detection of itself as an "enemy")
            if (!(location.x() == 0) && !(location.y() == 0)){

                if (location.containsAnActor()) {
                    Actor potentialEnemy = location.getActor();
                    if (isAnEnemy(potentialEnemy)) {
                        return location;    // Returns back the first enemy it detects
                    }
                }

            }
        }
        // No enemy in range
        return null;
    }

    /**
     * Determines if an Actor is considered as an Enemy
     * @param potentialEnemy    An Actor object representing a potential enemy
     * @return  A boolean value representing if the Actor object passed in is an enemy or not
     */
    private boolean isAnEnemy(Actor potentialEnemy){
        boolean isEnemy;
        if (!potentialEnemy.hasCapability(friendlyStatus) && potentialEnemy.hasCapability(Status.HOSTILE_TO_ENEMY)){
            isEnemy = true;
        } else {
            isEnemy = false;
        }
        return isEnemy;
    }
}
