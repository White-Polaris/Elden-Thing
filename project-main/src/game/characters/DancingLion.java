package game.characters;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.action.AttackAction;
import game.behaviour.AttackBehaviour;
import game.behaviour.EnemyObservable;
import game.behaviour.FollowBehaviour;
import game.behaviour.WanderBehaviour;
import game.divinepowers.DivinePower;
import game.divinepowers.WindPower;
import game.location.*;
import game.terrain.TerrainRestriction;
import game.weapons.Bite;
import game.remembrances.RemembranceOfDancingLion;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * Class representing the Dancing Lion
 * For now, it can only wander around the map.
 * @author Adrian Kristanto
 * @author Junyan
 * @version 1.0.0
 */
public class DancingLion extends Enemy implements EnemyObservable {
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
     * Current divine power of the Dancing Lion.
     */
    private DivinePower currentPower = new WindPower();

    /**
     * DancingLion rate of divine power
     */
    private final int TRANSFORM_RATE = 25;
    /**
     *
     */
    private MapManager mapManager;

    /**
     * Constructor
     */
    public DancingLion(MapManager mapManager) {
        super("Divine Beast Dancing Lion", 'S', 10000);
        this.behaviours.put(WANDER_BEHAVIOUR_KEY, new WanderBehaviour());
        this.behaviours.put(ATTACK_BEHAVIOUR_KEY, new AttackBehaviour(Status.DANCING_LION_ALLY, this));
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(Status.DANCING_LION_ALLY);
        this.addCapability(Status.BURN_IMMUNITY);
        this.addCapability(TerrainRestriction.FLOOR);
        this.setIntrinsicWeapon(new Bite());
        this.addBalance(250);
        this.mapManager = mapManager;
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
        Random rand = new Random();
        // Check for divine power transition
        if (rand.nextInt(100) < TRANSFORM_RATE) { // 25% chance to change divine power
            DivinePower previousPower = currentPower;
            currentPower = currentPower.getNextDivinePower(rand);
            display.println(this + " changes its divine power from " + previousPower.getName() + " to " +
                    currentPower.getName() + "!");
        } else {
            System.out.println(this + " have not change the divine power," + " now is still with " +
                    currentPower.getName() + " divine power.");
        }

        // Execute special attack based on current divine power
        Actor target = findTarget(map);
        if (target != null) {
            currentPower.executeSpecialAttack(this, target, map);
        }
        // Proceed with normal behaviors (Attack, Follow, Wander)
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
     *  Handles the event when the DancingLion is defeated by an actor.
     *
     * @param actor the Actor that defeated the DancingLion.
     * @param map the GameMap on which the defeat occurred.
     * @return a String message describing the defeat of the DancingLion.
     */
    public String unconscious(Actor actor, GameMap map){
        // Get the location of DancingLion
        Location deathLocation = map.locationOf(this);

        // Remove the Gate at the death Location
        map.removeActor(this);

        // Create the Gate at the death Location
        createGateAtLocation(deathLocation);

        // Drop the Remembrance Of Dancing Lion
        deathLocation.addItem(new RemembranceOfDancingLion());

        return this + " has been defeated by " + actor + "!";
    }

    /**
     * Creates a Gate at the specified location, which teleports back to BelarutTowerSettlement.
     *
     * @param location    The location where the Gate will be placed.
     */
    private void createGateAtLocation(Location location){
        // Get belarutMap implementation
        EnhancedGameMap belarutMap = mapManager.getGameMap("Belarut, Tower Settlement");
        // Get the Map of where DancingLion died
        GameMap map = location.map();
        // Select a specific location on the BelarutTowerSettlement map as the transfer destination
        Location teleportLocation = belarutMap.at(7, 1);

        // Add the Gate to the Map using the Death Location
        for (EnhancedGameMap enhancedMap : mapManager.getGameMaps()){
            if (map.equals(enhancedMap)){
                enhancedMap.addGate(location, teleportLocation);
            }
        }
        System.out.println(this + " has died and a Gate has been created at " + location + "!");
    }

    /**
     * Finds the target (player) if adjacent to the Dancing Lion.
     *
     * @param map The current game map.
     * @return The target Actor if adjacent; otherwise, null.
     */
    private Actor findTarget(GameMap map) {
        Location lionLocation = map.locationOf(this);
        for (Exit exit : lionLocation.getExits()) {
            Location adjacent = exit.getDestination();
            if (adjacent.containsAnActor()) {
                Actor actor = adjacent.getActor();
                if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                    return actor;
                }
            }
        }
        return null;
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
