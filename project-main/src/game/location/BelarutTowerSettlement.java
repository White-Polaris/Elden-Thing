package game.location;

import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.Location;
import game.characters.merchant.MerchantThree;
import game.terrain.Dirt;
import game.terrain.Floor;
import game.terrain.Puddle;
import game.terrain.Wall;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An instance of EnhancedGameMap that represents the Map of Belarut, Tower Settlement
 * @author Ian Lai
 * @version 1.0.0
 */

public class BelarutTowerSettlement extends EnhancedGameMap {

    /**
     * A String representing the name of a BelarutTowerSettlement Map
     */
    private static final String NAME = "Belarut, Tower Settlement";
    /**
     * A FancyGroundFactory object representing the Ground instances in a BelarutTowerSettlement Map
     */
    private static final FancyGroundFactory GROUND_FACTORY = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(),
            new Puddle());
    /**
     * A List of Strings representing the layout of BelarutTowerSettlement Map
     */
    private static final List<String> MAP_LAYOUT = Arrays.asList(
            "###########........................##########",
            "#____#____#......................._____#____#",
            "#____#_.._#.#...~~~.......~~~....#____#____##",
            "###_~~____###...~~~..~~~..~~~...####______###",
            "###...____###..~~~~..~~~~..~~~...######_____#",
            "##~~###..####..~~~...~~~.....~~~..####..#####",
            "##__.....####..~~~.~~~~~..~~~....#####____###",
            "###..##..##.#..~~..~~~~~..~~~~....####~..####",
            "#....__..__.#..~~..~~~~~~..~~....__~~~~######",
            "###########....................##############");

    /**
     * A Location object storing the Default Spawn, or Location of a Gate of BelarutTowerSettlement.
     * Useful to implement with Gates
     */
    private final Location DEFAULT_TRAVEL_LOCATION = this.at(7, 5);

    /**
     * Constructor
     */
    public BelarutTowerSettlement(MapManager mapManager){
        super(NAME, GROUND_FACTORY, MAP_LAYOUT, mapManager);
        this.setDefaultTravelLocation(DEFAULT_TRAVEL_LOCATION);
    }

    /**
     * A method that overrides the abstract method in the parent class, and initialises all entities within a
     * BelarutTowerSettlement Map
     */
    @Override
    public void initialiseEntities() {
        // Add Weapons, Consumable, Enemies Under Here
        this.at(27, 5).addItem(new Graveyard());
        this.at(33, 7).addItem(new Graveyard());
        this.at(15, 8).addItem(new Graveyard());

        // This merchant NPC sells Lifesteal
        MerchantThree merchantThree = new MerchantThree();
        this.at(15,6).addActor( merchantThree );

        // Gate
        ArrayList<Location> travelLocations = new ArrayList<>();
        travelLocations.add(mapManager.getGameMap("Gravesite Plains").getDefaultTravelLocation());

        Location stagefrontGateLocation = this.at(7, 1);

        this.addGate(DEFAULT_TRAVEL_LOCATION, travelLocations);
        this.addGate(stagefrontGateLocation, mapManager.getGameMap("Stagefront").getDefaultTravelLocation());
    }
}
