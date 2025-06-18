package game.location;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.action.TeleportActorAction;

import java.util.ArrayList;

/**
 * This class represents a Gate, and allows Actors to travel to other Maps of the World. It is a subclass of WorldItem.
 * @author Ian Lai
 * @version 1.0.0
 */

public class Gate extends WorldItem {

    /**
     * A String representing the name of a Gate object
     */
    private static final String NAME = "Gate";
    /**
     * A char representing the Icon of a Gate
     */
    private static final char DISPLAY_CHAR = 'H';
    /**
     * A Boolean value representing if a Gate can be picked up and moved in the World
     */
    private static final boolean IS_PORTABLE = false;
    /**
     * A List of Locations a Gate will take a Player to
     */
    private ArrayList<Location> travelLocations = new ArrayList<>();

    /**
     * Constructor that accepts a List of Locations
     * @param gateLocation A Location Object of where the Gate lies
     * @param travelLocations An Array of Location Objects to where a Gate will take an Actor
     */
    public Gate(Location gateLocation, ArrayList<Location> travelLocations) {
        super(NAME, DISPLAY_CHAR, IS_PORTABLE, gateLocation);
        this.travelLocations = travelLocations;
        compileTeleportAction();
    }

    /**
     * Constructor that accepts a Single Location
     * @param gateLocation A Location Object of where the Gate lies
     * @param travelLocation A Location Objects to where a Gate will take an Actor
     */
    public Gate(Location gateLocation, Location travelLocation) {
        super(NAME, DISPLAY_CHAR, IS_PORTABLE, gateLocation);
        this.travelLocations.add(travelLocation);
        compileTeleportAction();
    }

    /**
     * Method to add a Location a Gate can take an Actor to
     * @param location  The Location to travel to
     */
    public void addTravelLocation(Location location){
        this.travelLocations.add(location);
        this.addAction(new TeleportActorAction(location, "to " + location.map().toString()));
    }

    /**
     * Method to check if a Gate travels to a specific Location
     * @param location  The Location to check
     * @return boolean A boolean representing if a Gate has a specific Location it travels to
     */
    public boolean containsTravelLocation(Location location){
        return this.travelLocations.contains(location);
    }

    /**
     * Method to initialise TeleportActorActions to allow an Actor to use it
     */
    private void compileTeleportAction(){
        if (!this.travelLocations.isEmpty()) {
            for (Location travelLocation : travelLocations) {
                GameMap map = travelLocation.map();
                this.addAction(new TeleportActorAction(travelLocation, "to " + map.toString()));
            }
        }
    }
}
