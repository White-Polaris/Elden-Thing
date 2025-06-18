package edu.monash.fit2099.engine.actors;

import edu.monash.fit2099.engine.positions.Location;

/**
 * Abstract class that represents a status effect that an actor may have
 * For example: an actor might be poisoned after consuming an item.
 * The item might attach a Poison object to the actor's status effect list
 * to deal damage over time. After a certain amount of turns, the status
 * effect can remove itself from the actor's list of status effects.
 */
public abstract class StatusEffect {
    private final String name;

    public StatusEffect(String name) {
        this.name = name;
    }

    /**
     * Inform a status effect of the passage of time.
     *
     * @param location the location where the actor with the status effect is currently standing
     * @param actor the actor holding the status effect
     */
    public void tick(Location location, Actor actor) {}

    @Override
    public String toString() {
        return name;
    }
}
