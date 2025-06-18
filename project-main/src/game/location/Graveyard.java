package game.location;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.characters.ManFly;
import game.characters.Spirit;

import java.util.Random;

/**
 * A class representing a Graveyard, which can spawn hostile entities like Spirits and Man-Flies
 * based on its location in the game world.
 * Created by:
 * @author Yuhang Fei
 */
public class Graveyard extends Item {

    /**
     * A static instance of {@code Random} used for determining spawn probabilities.
     */
    private static final Random rand = new Random();

    /**
     * Constructor for the Graveyard.
     * Sets the name and display character, and defines it as a non-portable item.
     */
    public Graveyard() {
        super("Graveyard", 'n', false);
    }

    /**
     * The periodic update method for the Graveyard, called on each game tick.
     * This method checks the current map name and determines whether to spawn a Spirit or Man-Fly
     * based on the location:
     * <ul>
     *     <li>If the map is "Belarut, Tower Settlement", there is a 20% chance to spawn a Spirit.</li>
     *     <li>If the map is "Belarut Sewers", there is a 15% chance to spawn a Man-Fly.</li>
     * </ul>
     * The method will not spawn an actor if the location is already occupied.
     *
     * @param location The location of the Graveyard in the game world.
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        // Determine which actor to spawn based on the map
        String mapName = location.map().toString();

        if (mapName.equals("Belarut, Tower Settlement")) {
            // 20% chance to spawn a Spirit in the tower
            if (!location.containsAnActor() && rand.nextInt(100) < 20) {
                location.addActor(new Spirit());
            }
        } else if (mapName.equals("Belarut Sewers")) {
            // 15% chance to spawn a ManFly in the sewers
            if (!location.containsAnActor() && rand.nextInt(100) < 15) {
                location.addActor(new ManFly());
            }
        }
    }
}

