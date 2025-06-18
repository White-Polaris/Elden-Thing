package game.terrain;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

/**
 * A class representing a wall that cannot be entered by any actor
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 * @author Ian Lai
 * @version 1.0.0
 *
 */
public class Wall extends Ground {

    /**
     * Constructor
     */
    public Wall() {
        super('#', "Wall");
    }

    /**
     * A method that determines if an Actor can traverse through a Wall
     * @param actor the Actor to check
     * @return  a boolean value representing if an Actor can traverse through a Wall
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}
