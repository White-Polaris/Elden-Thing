package game.location;

import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract Class that allows for extensibility or adding methods and making modifications to a GameMap
 * @author Ian Lai
 * @version 1.0.0
 */

public abstract class EnhancedGameMap extends GameMap {

    /**
     * A String representing the Map Name
     */
    private final String MAP_NAME;
    /**
     * A FancyGroundFactory instance holding all Ground instances of this Map
     */
    private final FancyGroundFactory MAP_GROUND_FACTORY;
    /**
     * A List of Strings representing the layout of the Map
     */
    private final List<String> MAP_LAYOUT;

    /**
     * A Location object storing the Default Spawn, or Location of a Gate in a Map. Useful to implement with Gates
     */
    private Location defaultTravelLocation = null;

    /**
     *
     */
    protected MapManager mapManager;

    /**
     * Constructor for an EnhancedGameMap
     * @param name  A string representing the name of the GameMap
     * @param groundFactory   A groundFactory instance holding the Ground instances in this Map
     * @param mapLayout   A List of Strings representing the layout of the Map
     */
    public EnhancedGameMap(String name, FancyGroundFactory groundFactory, List<String> mapLayout, MapManager mapManager)
    {
        super(name, groundFactory, mapLayout);
        this.MAP_NAME = name;
        this.MAP_GROUND_FACTORY = groundFactory;
        this.MAP_LAYOUT = mapLayout;
        this.mapManager = mapManager;
    }

    // Accessor Methods
    /**
     * A Method to return the Map's Name
     * @return  A String representing the Map Name
     */
    public String getMapName() {
        return MAP_NAME;
    }

    /**
     * A method to return the Map's Layout
     * @return A List of Strings representing the Map Layout
     */
    public List<String> getMapLayout(){
        return MAP_LAYOUT;
    }

    /**
     * An abstract method defined in subclasses to initialise entities within a Map
     */
    public abstract void initialiseEntities();

    /**
     * A method to add a Gate and one Location to travel to in an EnhancedGameMap
     * @param gateLocation  The Location of the Gate
     * @param travelLocation    The Location where a Gate should take an Actor to
     */
    public void addGate(Location gateLocation, Location travelLocation){
        int x = gateLocation.x();
        int y = gateLocation.y();
        this.at(x, y).addItem(new Gate(gateLocation, travelLocation));
    }

    /**
     * A method to add a Gate with multiple Locations to travel to in an EnhancedGameMap
     * @param gateLocation  The Location of the Gate
     * @param travelLocations   The Locations where a Gate should take an Actor to
     */
    public void addGate(Location gateLocation, ArrayList<Location> travelLocations){
        int x = gateLocation.x();
        int y = gateLocation.y();
        this.at(x, y).addItem(new Gate(gateLocation, travelLocations));
    }

    /**
     * A method that returns the Default Travel Location of this EnhancedGameMap
     * @return  A Location object representing the Default Travel Location of the Map
     */
    public Location getDefaultTravelLocation(){
        return defaultTravelLocation;
    }

    /**
     * A method that can be shared with its child classes to change the default travel location
     * @param location  The Location object representing the new default location of the EnhancedGameMap
     */
    protected void setDefaultTravelLocation(Location location){
        this.defaultTravelLocation = location;
    }

}
