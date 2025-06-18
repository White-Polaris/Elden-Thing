package game.location;

import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.Location;
import game.characters.DancingLion;
import game.terrain.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Stagefront extends EnhancedGameMap {

    /**
     * A String representing the name of a Stagefront Map
     */
    private static final String NAME = "Stagefront";
    /**
     * A FancyGroundFactory object representing the Ground instances in a Stagefront Map
     */
    private static final FancyGroundFactory GROUND_FACTORY = new FancyGroundFactory(new Dirt(), new Wall(),
            new Puddle());
    /**
     * A List of Strings representing the layout of a Stagefront Map
     */
    private static final List<String> MAP_LAYOUT = Arrays.asList(
            "#################",
            "#~~~..........~~#",
            "#~~~...........~#",
            "#~~.............#",
            "#............~~~#",
            "#..........~~~~~#",
            "#######...#######"
    );

    /**
     * A Location object storing the Default Spawn, or Location of a Gate of Stagefront.
     * Useful to implement with Gates
     */
    private final Location DEFAULT_TRAVEL_LOCATION = this.at(8, 6);

    public Stagefront(MapManager mapManager){
        super(NAME, GROUND_FACTORY, MAP_LAYOUT, mapManager);
        this.setDefaultTravelLocation(DEFAULT_TRAVEL_LOCATION);
    }

    /**
     * An abstract method defined in subclasses to initialise entities within a Map
     */
    @Override
    public void initialiseEntities() {
        // Initialise Entities Here
        this.at(8, 2).addActor(new DancingLion(this.mapManager));
    }
}
