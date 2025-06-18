package game.action;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.ActorLocationsIterator;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A Class that allows an Actor to move to another Map, and also checks for Actors blocking the Gate
 * @author Ian Lai
 * @version 1.0.0
 */

public class TeleportActorAction extends MoveActorAction {

    /**
     * A Location object representing the Location the Actor wants to move to
     */
    private final Location MOVE_TO_LOCATION;
    /**
     * An ActorLocationsIterator representing an ActorLocationsIterator Object
     */
    private final ActorLocationsIterator ACTOR_LOCATIONS_ITERATOR = new ActorLocationsIterator();

    /**
     * Constructor
     * @param moveToLocation A Location object representing the Location the Actor will move to
     * @param direction A String representing the Direction in which an Actor will move
     */
    public TeleportActorAction(Location moveToLocation, String direction){
        super(moveToLocation, direction);
        this.MOVE_TO_LOCATION = moveToLocation;
    }

    /**
     * Overrides the execute method of MoveActorAction, and checks for an existing Actor when trying to move to the
     * new Location. Does not allow movement if an Actor is on the Location it wants to move to.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return  A String representing the result of the Actor wanting to move to the new Location
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        String returnString;
        ACTOR_LOCATIONS_ITERATOR.setPlayer(actor);
        try {
            map.moveActor(actor, MOVE_TO_LOCATION);
            returnString = menuDescription(actor);
        } catch (IllegalArgumentException error){
            returnString = "Another Actor is blocking the Gate, " + actor + " may not travel.\n";
        }
        return returnString;
    }
}
