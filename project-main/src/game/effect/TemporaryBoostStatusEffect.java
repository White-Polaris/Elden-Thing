package game.effect;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;

/**
 * This one is actor consume the Scarab and get the effect form Scarab; add by: Junyan
 * @author Junyan
 */
public class TemporaryBoostStatusEffect extends StatusEffect {

    private int duration;
    private int health;
    private int mana;

    /**
     * Constructs a new TemporaryBoostStatusEffect.
     *
     * @param duration The duration of the effect in ticks.
     * @param health The amount of health to boost temporarily.
     * @param mana The amount of mana to boost temporarily.
     */
    public TemporaryBoostStatusEffect(int duration, int health, int mana) {
        super("Temporary Boost");
        this.duration = duration;
        this.health = health;
        this.mana = mana;
    }

    /**
     * Called each tick to update the effect.
     * Decreases the duration by one.
     * If the duration expires, it decreases the actor's health and mana attributes by the boosted amount and removes the effect from the actor.
     *
     * @param location the location where the actor with the status effect is currently standing
     * @param actor the actor holding the status effect
     */
    @Override
    public void tick(Location location, Actor actor) {
        duration--;
        if (duration <= 0) {
            // Remove markup
            actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.DECREASE, health);
            actor.modifyAttributeMaximum(BaseActorAttributes.MANA, ActorAttributeOperations.DECREASE, mana);
            actor.removeStatusEffect(this);
        }
    }
}
