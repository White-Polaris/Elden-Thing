package game.action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.terrain.Puddle;
import game.characters.Scarab;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This is an action which is ConsumerWaterAction inherit Action; add by: Junyan
 * @author Junyan
 */
public class ConsumeWaterAction extends Action {

    /**
     * The puddle from which the actor is consuming water.
     */
    private Puddle puddle;
    /**
     * The location of the puddle where the action is taking place.
     */
    private Location location;

    /**
     * Constructs a new ConsumeWaterAction with the specified puddle and location.
     */
    public ConsumeWaterAction(Puddle puddle, Location location) {
        this.puddle = puddle;
        this.location = location;
    }

    /**
     * Executes the consume water action by the specified actor on the game map.
     * <p>
     * This action will cause the actor to restore a set amount of mana and has a 10%
     * chance to spawn a scarab in an adjacent location.
     * </p>
     *
     * @param actor The actor who is consuming the water.
     * @param map The game map on which the action is executed.
     * @return A description of the action's effect.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Restore 5 mana points
        actor.modifyAttribute(BaseActorAttributes.MANA, ActorAttributeOperations.INCREASE, 5);

        // 10% chance to spawn a scarab
        Random rand = new Random();
        if (rand.nextInt(100) < 10) {
            // Collect possible spawn locations around the puddle
            List<Location> possibleLocations = new ArrayList<>();
            for (int x = location.x() - 1; x <= location.x() + 1; x++) {
                for (int y = location.y() - 1; y <= location.y() + 1; y++) {
                    if (map.getXRange().contains(x) && map.getYRange().contains(y)) {
                        Location spawnLocation = map.at(x, y);
                        if (!spawnLocation.containsAnActor() && spawnLocation.canActorEnter(new Scarab())) {
                            possibleLocations.add(spawnLocation);
                        }
                    }
                }
            }
            if (!possibleLocations.isEmpty()) {
                // Randomly select one location
                Location chosenLocation = possibleLocations.get(rand.nextInt(possibleLocations.size()));
                map.addActor(new Scarab(), chosenLocation);
                return actor + " consumes water and a scarab appears nearby!";
            } else {
                return actor + " consumes water but no scarab appear.";
            }
        }

        return actor + " consumes water and restores 5 mana.";
    }

    /**
     * Provides a description of the consume water action suitable for display in a menu.
     *
     * @param actor The actor who would consume the water.
     * @return A string describing the action in menu format.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes water from the puddle.";
    }
}
