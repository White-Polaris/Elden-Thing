package game.location;

import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.Location;
import game.characters.SuspiciousTrader;
import game.characters.FurnaceGolem;
import game.characters.merchant.MerchantOne;
import game.consumables.FlaskOfHealing;
import game.consumables.FlaskOfRejuvenation;
import game.consumables.ShadowtreeFragment;
import game.terrain.*;
import game.weapons.GreatKnife;
import game.weapons.ShortSword;
import game.weapons.WeaponItem;
import game.weapons.weaponarts.WeaponArtsEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An instance of EnhancedGameMap that represents the Map of Gravesite Plains
 * @author Ian Lai
 * @version 1.0.0
 */

public class GravesitePlains extends EnhancedGameMap{

    /**
     * A String representing the name of a GravesitePlains Map
     */
    private static final String NAME = "Gravesite Plains";
    /**
     * A FancyGroundFactory object representing the Ground instances in a GravesitePlains Map
     */
    private static final FancyGroundFactory GROUND_FACTORY = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(),
            new Puddle());
    /**
     * A List of Strings representing the layout of a GravesitePlains Map
     */
    private static final List<String> MAP_LAYOUT = Arrays.asList(
            "..........~~~~~~~...~~~~~~~......~...........",
            "~..........~~~~~....~~~~~~...................",
            "~~.........~~~~.....~~~~~~...................",
            "~~~..#####..~~.....~~~~~~~...................",
            "~~~..#___#........~~~~~~~~~..................",
            "~~~..#___#.......~~~~~~.~~~..................",
            "~~~..##_##......~~~~~~.......................",
            "~~~~...........~~~~~~~...........~~..........",
            "~~~~~.........~~~~~~~~.......~~~~~~~.........",
            "~~~~~~.......~~~~~~~~~~.....~~~~~~~~.........");

    /**
     * A Location object storing the Default Spawn, or Location of a Gate of GravesitePlains.
     * Useful to implement with Gates
     */
    private final Location DEFAULT_TRAVEL_LOCATION = this.at(44, 4);

    /**
     * Constructor
     */
    public GravesitePlains(MapManager mapManager){
        super(NAME, GROUND_FACTORY, MAP_LAYOUT, mapManager);
        this.setDefaultTravelLocation(DEFAULT_TRAVEL_LOCATION);
    }

    /**
     * A method that overrides the abstract method in the parent class, and initialises all entities within a
     * GravesitePlains
     */
    @Override
    public void initialiseEntities(){
        // Enemies
        this.at(40, 8).addActor(new FurnaceGolem());

        // Instantiate Weapon with WeaponArts);

        this.at(21, 4).addItem(new GreatKnife());


        // Instantiate Weapons with Memento
        WeaponItem mementoGreatKnife = new GreatKnife();
        WeaponItem mementoShortSword = new ShortSword();
        mementoGreatKnife.addCapability(WeaponArtsEnum.MEMENTO);
        mementoShortSword.addCapability(WeaponArtsEnum.MEMENTO);
        this.at(21, 8).addItem(mementoGreatKnife);
        this.at(22, 8).addItem(mementoShortSword);

        // Consumables
        this.at(7, 8).addItem(new ShadowtreeFragment());
        this.at(30, 2).addItem(new ShadowtreeFragment());
        this.at(30, 3).addItem(new ShadowtreeFragment());
        this.at(30, 4).addItem(new ShadowtreeFragment());
        this.at(30, 5).addItem(new ShadowtreeFragment());
        this.at(6, 4).addItem(new FlaskOfHealing());
        this.at(8, 4).addItem(new FlaskOfRejuvenation());

        // NPC
        MerchantOne merchant = new MerchantOne();
        this.at(23,6).addActor(merchant);

        SuspiciousTrader trader = new SuspiciousTrader();
        this.at(6, 5).addActor(trader);

        // Gate
        ArrayList<Location> travelLocations = new ArrayList<>();
        travelLocations.add(mapManager.getGameMap("Belarut, Tower Settlement").getDefaultTravelLocation());
        travelLocations.add(mapManager.getGameMap("Belarut Sewers").getDefaultTravelLocation());
        this.addGate(DEFAULT_TRAVEL_LOCATION, travelLocations);
    }
}
