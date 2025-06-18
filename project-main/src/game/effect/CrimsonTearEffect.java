package game.effect;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.positions.Location;

/**
 * This one is actor consume the CrimsonTear and get the effect form this item; add by: Junyan
 * @author Junyan
 */
public class CrimsonTearEffect extends StatusEffect {

    private int DURATION = 5;

    private final int HEAL_POINT = 30;

    /**
     * Constructor
     */
    public CrimsonTearEffect() {
        super("Crimson Tear Effect");
    }

    /**
     * Each turn let actor heal HP
     *
     * @param location the location where the actor with the status effect is currently standing
     * @param actor the actor holding the status effect
     */
    @Override
    public void tick(Location location, Actor actor) {
        if (DURATION > 0) {
            actor.heal(HEAL_POINT);
            DURATION--;
        } else {
            actor.removeStatusEffect(this);
        }
    }
}
