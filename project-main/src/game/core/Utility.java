package game.core;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.NumberRange;

import java.util.ArrayList;

/**
 * This class holds universal methods that may be useful for future purposes
 */

public class Utility {

    /**
     * Private Constructor
     */
    private Utility() {
    }

    /**
     * A Static Method that gets the Surrounding Location objects of an Actor
     * @param actor The Actor
     * @param map   The current state of the GameMap
     * @param radius The radius around the Actor
     * @return  A List of Locations around the Actor
     */
    public static ArrayList<Location> getSurroundingLocations(Actor actor, GameMap map, int radius) {

        ArrayList<Location> locations = new ArrayList<>();
        Location actorLocation = map.locationOf(actor);
        // For Each Y coordinate surrounding the Actor (to the left, the same x-coordinate, and to the right)
        for (int x = -radius; x <= radius; x++) {
            // For Each Y coordinate surrounding the Actor (to the left, the same y-coordinate, and to the right)
            for (int y = -radius; y <= radius; y++) {

                NumberRange xRangeLimit = map.getXRange();
                NumberRange yRangeLimit = map.getYRange();

                int xCoordinate = actorLocation.x() + x;
                int yCoordinate = actorLocation.y() + y;

                if (xRangeLimit.contains(xCoordinate) && yRangeLimit.contains(yCoordinate)) {
                    Location validLocation = map.at(xCoordinate, yCoordinate);
                    locations.add(validLocation);
                }
            }
        }
        return locations;
    }

    /**
     * A Static Method that gets the Surrounding Location objects of an Actor
     * @param location The location object to get its surrounding locations from
     * @param radius The radius around the Actor
     * @return  A List of Locations around the Location
     */
    public static ArrayList<Location> getSurroundingLocations(Location location, int radius) {

        ArrayList<Location> locations = new ArrayList<>();
        GameMap map = location.map();

        // For Each Y coordinate surrounding the Location (to the left, the same x-coordinate, and to the right)
        for (int x = -radius; x <= radius; x++) {
            // For Each Y coordinate surrounding the Location (to the left, the same y-coordinate, and to the right)
            for (int y = -radius; y <= radius; y++) {

                NumberRange xRangeLimit = map.getXRange();
                NumberRange yRangeLimit = map.getYRange();

                int xCoordinate = location.x() + x;
                int yCoordinate = location.y() + y;

                if (xRangeLimit.contains(xCoordinate) && yRangeLimit.contains(yCoordinate)) {
                    Location validLocation = map.at(xCoordinate, yCoordinate);
                    locations.add(validLocation);
                }
            }
        }
        return locations;
    }
}
