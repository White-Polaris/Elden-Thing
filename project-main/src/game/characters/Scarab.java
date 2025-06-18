package game.characters;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.action.AttackAction;
import game.action.ConsumeScarabAction;
import game.behaviour.WanderBehaviour;
import game.consumables.CrimsonTear;
import game.terrain.TerrainRestriction;

import java.util.*;

/**
 * This one is actor Scarab inherit by Actor; add by: Junyan
 * @author Junyan
 */
public class Scarab extends Enemy {

    private final int WANDER_BEHAVIOUR_KEY = 999;
    private final int EXPLODE_DAMAGE = 25;

    private Map<Integer, Behaviour> behaviours = new HashMap<>();

    /**
     * Constructs a new Scarab actor.
     */
    public Scarab() {
        super("scarabs", 'b', 25);
        this.behaviours.put(WANDER_BEHAVIOUR_KEY, new WanderBehaviour());
        this.addCapability(Ability.PLAYER_ATTACK_ONLY);
        this.addCapability(Status.POISON_IMMUNE);   // Scarab is immune to poison
        this.addCapability(Status.SCARAB_ALLY);
        this.addCapability(TerrainRestriction.FLOOR);
    }

    /**
     * Determines the action the Scarab will perform on its turn.
     *
     * @param actions Possible actions the Scarab can perform.
     * @param lastAction The last action performed by the Scarab.
     * @param map The current game map.
     * @param display The display component of the game.
     * @return The action the Scarab will perform.
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
     * Returns a list of actions that can be performed by the player on the Scarab.
     *
     * @param otherActor The actor interacting with the Scarab.
     * @param direction The direction from which the interaction is coming.
     * @param map The current game map.
     * @return A list of allowable actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Ability.PLAYER_ATTACK_ONLY)) {
            actions.add(new AttackAction(this, direction));
            if (isAdjacent(otherActor, map)) {
                actions.add(new ConsumeScarabAction(this));
            }
        }
        return actions;
    }

    /**
     * Checks if another actor is adjacent to the Scarab.
     *
     * @param actor The actor to check against.
     * @param map The current game map.
     * @return True if the actor is adjacent, false otherwise.
     */
    private boolean isAdjacent(Actor actor, GameMap map) {
        // check actor is nearly by scarab
        int x = map.locationOf(this).x();
        int y = map.locationOf(this).y();
        int actorX = map.locationOf(actor).x();
        int actorY = map.locationOf(actor).y();
        return Math.abs(x - actorX) <= 1 && Math.abs(y - actorY) <= 1;
    }

    /**
     * Defines what happens when the Scarab is unconscious (defeated).
     *
     * @param actor The actor that caused the Scarab to become unconscious.
     * @param map The current game map.
     * @return A description of the effect.
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        // Set to keep track of Scarabs that have already exploded
        Set<Actor> explodedScarabs = new HashSet<>();
        // Queue to keep track of Scarabs that need to explode
        Queue<Actor> scarabsToExplode = new LinkedList<>();

        // Add this Scarab to the queue
        scarabsToExplode.add(this);

        // StringBuilder to accumulate result messages
        StringBuilder result = new StringBuilder();

        while (!scarabsToExplode.isEmpty()) {
            Actor explodingScarab = scarabsToExplode.poll();
            // If it has already exploded, skip
            if (explodedScarabs.contains(explodingScarab)) {
                continue;
            }
            explodedScarabs.add(explodingScarab);

            // Get the location of the Scarab
            Location scarabLocation = map.locationOf(explodingScarab);

            // Scarab explodes, dealing damage to adjacent actors
            for (int x = scarabLocation.x() - 1; x <= scarabLocation.x() + 1; x++) {
                for (int y = scarabLocation.y() - 1; y <= scarabLocation.y() + 1; y++) {
                    if (map.getXRange().contains(x) && map.getYRange().contains(y)) {
                        Location location = map.at(x, y);
                        if (map.isAnActorAt(location)) {
                            Actor nearbyActor = map.getActorAt(location);
                            // Don't damage self if it's the same actor
                            if (nearbyActor != explodingScarab) {
                                nearbyActor.hurt(EXPLODE_DAMAGE);
                                // Check if the actor died
                                if (!nearbyActor.isConscious()) {
                                    // If the actor is a Scarab and hasn't exploded yet, add to queue
                                    if (nearbyActor.hasCapability(Status.SCARAB_ALLY) && !explodedScarabs.contains(nearbyActor)) {
                                        scarabsToExplode.add(nearbyActor);
                                    } else {
                                        // Handle other actors' death
                                        String deathMessage = nearbyActor.unconscious(explodingScarab, map);
                                        result.append(deathMessage).append("\n");
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // If the exploding actor has SCARAB capability, drop Crimson Tear
            if (explodingScarab.hasCapability(Status.SCARAB_ALLY)) {
                // Drop Crimson Tear at Scarab's location
                scarabLocation.addItem(new CrimsonTear());
            }
            // Remove the Scarab from the map
            map.removeActor(explodingScarab);
            result.append(explodingScarab).append(" explodes upon defeat!\n");
        }

        return result.toString();
    }

}
