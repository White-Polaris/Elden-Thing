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
import game.behaviour.WanderBehaviour;
import game.terrain.TerrainRestriction;
import game.weapons.BareHand;

import java.util.Map;
import java.util.TreeMap;

/**
 * A class representing a Spirit, a hostile enemy that wanders and attacks enemies on sight.
 * Created by:
 * @author Yuhang Fei
 */
public class Spirit extends Enemy {

    /**
     * A map storing various behaviours this Spirit can perform,
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
     * Constructor for the Spirit.
     * Initializes its name, display character, and hit points.
     * Adds default capabilities and sets its intrinsic weapon to bare hands.
     * Also sets up the default WanderBehaviour and AttackBehaviour.
     */
    public Spirit() {
        super("Spirit", '&', 100);
        this.behaviours.put(WANDER_BEHAVIOUR_KEY, new WanderBehaviour());
        this.behaviours.put(ATTACK_BEHAVIOUR_KEY, new AttackBehaviour(Status.SPIRIT_ALLY));
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(Status.SPIRIT_ALLY);
        this.addCapability(TerrainRestriction.FLOOR);
        this.setIntrinsicWeapon(new BareHand());
        this.addBalance(25);
    }

    /**
     * Determines and performs the next action for the Spirit during its turn.
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
     * Provides allowable actions when another actor interacts with this Spirit.
     * If the other actor is hostile, it will add an {@code AttackAction} to attack the Spirit.
     *
     * @param otherActor The actor interacting with the Spirit.
     * @param direction The direction of the other actor relative to the Spirit.
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
}

