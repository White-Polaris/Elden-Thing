package game.characters;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.action.AttackAction;
import game.behaviour.AttackBehaviour;
import game.behaviour.EnemyObservable;
import game.behaviour.FollowBehaviour;
import game.behaviour.WanderBehaviour;
import game.terrain.TerrainRestriction;
import game.weapons.Sting;

import java.util.Map;
import java.util.TreeMap;

/**
 * A class representing a Man-Fly, a hostile character that attacks enemies and follows them upon detection.
 * Created by:
 * @author Yuhang Fei
 */
public class ManFly extends Enemy implements EnemyObservable {

    /**
     * A map storing various behaviours this Man-Fly can perform,
     * where the key is an integer representing the priority of the behaviour.
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
     * Constructor for the Man-Fly.
     * Initializes its name, display character, and hit points.
     * Adds default capabilities and sets its intrinsic weapon to a Sting.
     * Also sets up the default WanderBehaviour and AttackBehaviour.
     */
    public ManFly() {
        super("Man-Fly", '%', 50);
        this.behaviours.put(WANDER_BEHAVIOUR_KEY, new WanderBehaviour());
        this.behaviours.put(ATTACK_BEHAVIOUR_KEY, new AttackBehaviour(Status.MAN_FLY_ALLY, this));
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(Status.MAN_FLY_ALLY);
        this.addCapability(Status.POISON_IMMUNE);
        this.addCapability(TerrainRestriction.FLOOR);
        this.setIntrinsicWeapon(new Sting());
        this.addBalance(50);
    }

    /**
     * Notifies this Man-Fly of an enemy's presence.
     * When the Man-Fly detects an enemy, it stops wandering and begins following the enemy.
     * Also adds the {@code TRACKING_ENEMY} capability to indicate the Man-Fly is actively tracking an enemy.
     *
     * @param enemy The detected enemy actor.
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
     * Determines and performs the next action for the Man-Fly during its turn.
     * It checks the behaviours in order of priority and performs the first available action.
     *
     * @param actions List of possible actions for the actor.
     * @param lastAction The last action performed by the actor.
     * @param map The map the actor is on.
     * @param display The display to show messages.
     * @return The action to be performed, or {@code DoNothingAction} if no valid action is found.
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
     * Provides allowable actions when another actor interacts with this Man-Fly.
     * If the other actor is hostile, it will add an {@code AttackAction} to attack the Man-Fly.
     *
     * @param otherActor The actor interacting with the Man-Fly.
     * @param direction The direction of the other actor relative to the Man-Fly.
     * @param map The current game map.
     * @return A list of allowable actions.
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
     * Adds a {@code FollowBehaviour} to this Man-Fly, causing it to follow a specified enemy.
     *
     * @param enemy The enemy to follow.
     */
    private void addFollowBehaviour(Actor enemy) {
        this.behaviours.put(FOLLOW_BEHAVIOUR_KEY, new FollowBehaviour(enemy));
    }

    /**
     * Removes the {@code WanderBehaviour} from this Man-Fly, stopping it from wandering.
     */
    private void removeWanderBehaviour() {
        this.behaviours.remove(WANDER_BEHAVIOUR_KEY);
    }
}

