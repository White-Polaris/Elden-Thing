package game.location;

import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.Location;
import game.characters.merchant.MerchantTwo;
import game.terrain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An instance of EnhancedGameMap that represents the Map of Belarut, Sewers
 * @author Ian Lai
 * @version 1.0.0
 */

public class BelarutSewers extends EnhancedGameMap {

    /**
     * A String representing the name of a BelarutSewers Map
     */
    private static final String NAME = "Belarut Sewers";
    /**
     * A FancyGroundFactory object representing the Ground instances in a BelarutSewers Map
     */
    private static final FancyGroundFactory GROUND_FACTORY = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(),
            new Puddle(), new PoisonSwamp());
    /**
     * A List of Strings representing the layout of a BelarutSewers Map
     */
    private static final List<String> MAP_LAYOUT = Arrays.asList(
            "##++++++#####++++++++~~~~~++++",
            "##+++++++###+++++++++~~~~~++++",
            "##++++++++++++++++++~~~~~~~++~",
            "###+++++++++++++++.~~~~~~~~.~~",
            "~~~~~.+++++~~~++++~~~~~~~~~..~",
            "~~~~~~~~~~~~~~~++++~~~~+++~...",
            "~~~~+~~~~~~~~~~+++++~~~~~~~###",
            "+~~~~++####~~~~~++++##.~++~###",
            "++~~+++#####~~~~~++###++~~~###",
            "+~~++++######~~~~++###++~~~###"
    );

    /**
     * A Location object storing the Default Spawn, or Location of a Gate of BelarutSewers.
     * Useful to implement with Gates
     */
    private final Location DEFAULT_TRAVEL_LOCATION = this.at(7, 5);

    /**
     * Constructor
     */
    public BelarutSewers(MapManager mapManager){
        super(NAME, GROUND_FACTORY, MAP_LAYOUT, mapManager);
        this.setDefaultTravelLocation(DEFAULT_TRAVEL_LOCATION);
    }

    /**
     * A method that overrides the abstract method in the parent class, and initialises all entities within a
     * BelarutSewersMap
     */
    @Override
    public void initialiseEntities(){
        // Add Weapons, Consumable, Enemies Under Here
        this.at(15, 3).addItem(new Graveyard());
        this.at(26, 1).addItem(new Graveyard());
        this.at(4, 9).addItem(new Graveyard());

        // This merchant NPC sells Quickstep
        MerchantTwo merchantTwo =  new MerchantTwo();
        this.at(12,5).addActor(merchantTwo);


        // Gate
        ArrayList<Location> travelLocations = new ArrayList<>();
        travelLocations.add(mapManager.getGameMap("Gravesite Plains").getDefaultTravelLocation());
        this.addGate(DEFAULT_TRAVEL_LOCATION, travelLocations);
    }

}
