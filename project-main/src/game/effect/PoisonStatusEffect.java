package game.effect;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A Class that represents a Poison Effect that hurts Actors with this Status.
 * @author Ian Lai
 * @version 1.0.0
 */

public class PoisonStatusEffect extends StatusEffect {

    /**
     * A String representing the name of the Effect
     */
    private static final String NAME = "Poison";
    /**
     * An integer representing the duration of the Effect
     */
    private int duration = 3;
    /**
     * An integer representing the damage of this Effect
     */
    private int damage = 5;

    /**
     * Default Constructor
     */
    public PoisonStatusEffect(){
        super(NAME);
    }

    /**
     * Non-Default Constructor that allows for custom duration and damage of a PoisonEffect
     * @param duration  An Integer representing the Duration of a PoisonEffect
     * @param damagePerTurn An Integer representing the Damage of a PoisonEffect
     */
    public PoisonStatusEffect(int duration, int damagePerTurn){
        super(NAME);
        this.duration = duration;
        this.damage = damagePerTurn;
    }

    /**
     * This method Poisons an Actor with a PoisonStatusEffect with a set duration. Once finished, the PoisonStatusEffect
     * is removed.
     * @param location the location where the actor with the status effect is currently standing
     * @param actor the actor holding the status effect
     */
    @Override
    public void tick(Location location, Actor actor){
        if (duration > 0) {
            actor.hurt(damage);

            GameMap currentMap = location.map();
            // Checks if Actor has reached 0 hitpoints
            if (!actor.isConscious()){
                actor.unconscious(actor, currentMap);
                System.out.println(actor + " has been poisoned to death");
            } else {
                System.out.println(actor + " is poisoned and takes " + damage + " damage!");
            }
            duration--;
        } else {
            actor.removeStatusEffect(this);
        }
    }
}