package game.characters;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.remembrances.RemembranceOfFurnaceGolem;
import game.action.AttackAction;
import game.behaviour.AttackBehaviour;
import game.behaviour.EnemyObservable;
import game.behaviour.FollowBehaviour;
import game.behaviour.WanderBehaviour;
import game.terrain.TerrainRestriction;
import game.weapons.FootStomp;

import java.util.Map;
import java.util.TreeMap;

/**
 * Class representing the Furnace Golem
 * For now, it can only wander around the map.
 * @author Adrian Kristanto
 * modified by:
 * @author Ian Lai
 * @version 1.0.0
 */
public class FurnaceGolem extends Enemy implements EnemyObservable {
    /**
     * This TreeMap is change by: lan
     * A TreeMap that maps an integer key to a specific instance of Behaviour
     */
    private Map<Integer, Behaviour> behaviours = new TreeMap<>();
    /**
     * An integer representing the Key for WanderBehaviour
     */
    private final int WANDER_BEHAVIOUR_KEY = 999;
    /**
     * An integer representing the Key for AttackBehaviour
     */
    private final int ATTACK_BEHAVIOUR_KEY = 998;
    /**
     * An integer representing the Key for FollowBehaviour
     */
    private final int FOLLOW_BEHAVIOUR_KEY = 997;

    /**
     * Constructor
     */
    public FurnaceGolem() {
        super("Furnace Golem", 'A', 1000);
        this.behaviours.put(WANDER_BEHAVIOUR_KEY, new WanderBehaviour());
        this.behaviours.put(ATTACK_BEHAVIOUR_KEY, new AttackBehaviour(Status.FURNACE_GOLEM_ALLY, this));
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(Status.FURNACE_GOLEM_ALLY);
        this.addCapability(Status.BURN_IMMUNITY);
        this.addCapability(TerrainRestriction.FLOOR);
        this.setIntrinsicWeapon(new FootStomp());
        this.addBalance(150);
        this.addItemToInventory( new RemembranceOfFurnaceGolem() );
    }

    /**
     * A method that detects an Actor object as an enemy, and the FurnaceGolem will now track it down
     * @param enemy The Actor object detected as an enemy
     */
    @Override
    public void enemyDetected(Actor enemy) {
        if(!this.hasCapability(Status.TRACKING_ENEMY)) {
            addFollowBehaviour(enemy);
            System.out.println(this + " Stops Wandering");
            System.out.println(this + " is hunting down " + enemy);
            removeWanderBehaviour();
        }
        this.addCapability(Status.TRACKING_ENEMY);
    }

    /**
     * Gives a FurnaceGolem its turn
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return  the Action FurnaceGolem takes on this turn
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * A List of Actions
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return  the list of actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

    /**
     * Gives this instance of FurnaceGolem a FollowBehaviour
     * @param enemy  The enemy to follow
     */
    private void addFollowBehaviour(Actor enemy){
        this.behaviours.put(FOLLOW_BEHAVIOUR_KEY, new FollowBehaviour(enemy));
    }

    /**
     * Stops this FurnaceGolem from Wandering
     */
    private void removeWanderBehaviour(){
        this.behaviours.remove(WANDER_BEHAVIOUR_KEY);
    }

}
