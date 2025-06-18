package game.core;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.characters.PlayerActorAttribute;

import java.util.*;

/**
 * A Class that manages the States of Actors, typically used with the Memento Class
 * @author Ian Lai
 * @version 1.0.0
 */

public class StateManager {

    private Map<Actor, List<State>> stateMap;

    /**
     * Default Constructor
     */
    public StateManager() {
        stateMap = new HashMap<>();
    }

    /**
     * A method that accepts attributes of an Actor, as well as the Location the Actor is on and saves it as a State
     * object into a HashMap, where the Actor is the key
     * @param actor     An actor object representing the actor in which it's state is to be saved
     * @param map       A Map object representing the Map the Actor is on
     */
    public void saveState(Actor actor, GameMap map) {

        // Attributes of an Actor to be Saved in the game's current turn
        int actorHealth = actor.getAttribute(BaseActorAttributes.HEALTH);
        int actorMana = actor.getAttribute(BaseActorAttributes.MANA);
        int actorStrength = actor.getAttribute(PlayerActorAttribute.STRENGTH);
        Location actorLocation = map.locationOf(actor);
        ArrayList<StatusEffect> statusEffects = new ArrayList<>(actor.getStatusEffects());

        // Create a new State object to store the state of the actor
        State newState = new State(actorHealth, actorMana, actorStrength, actorLocation, statusEffects);

        // Initialize the actor's state list if not already present
        // Creates a LinkedList of State Objects as values, where an Actor is the key
        stateMap.computeIfAbsent(actor, k -> new LinkedList<>()).add(newState);
    }

    /**
     * A method that returns a State object, containing attributes and relevant information of an Actor
     * @param actor The actor's state to be restored
     * @return  A State object containing relevant data of an Actor
     */
    public State restoreState(Actor actor) {
        List<State> states = stateMap.get(actor);

        // If the states of an Actor are not empty, remove the last saved state of this Actor
        if (states != null && !states.isEmpty()) {
            return states.remove(states.size() - 1);
        }
        // When there is no State to Restore
        return null;
    }

    /**
     * A method that returns a boolean value on if the Actor passed in has a saved state
     * @param actor  The Actor to check for a saved state
     * @return  A boolean value on if the Actor passed in has a saved state
     */
    public boolean hasSavedState(Actor actor) {
        List<State> states = stateMap.get(actor);
        return states != null && !states.isEmpty();
    }
}
