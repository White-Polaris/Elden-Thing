package game.core;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.World;
import game.characters.Player;
import game.location.*;



/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * @author Ian Lai
 * @version 1.0.0
 */
public class Application {

    public static void main(String[] args) {

        World world = new World(new Display());
        MapManager mapManager = new MapManager();

        EnhancedGameMap gravesitePlainsGameMap = mapManager.getGameMap("Gravesite Plains");

        // Add GameMaps to World
        for (GameMap map : mapManager.getGameMaps()){
            world.addGameMap(map);
        }

        // BEHOLD, ELDEN THING!
        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        // Initialise Entities (Weapons, Enemies, Consumables) of Maps
        mapManager.initialiseGameMaps();
        Player player = new Player("Tarnished", '@', 150);
        world.addPlayer(player, gravesitePlainsGameMap.at(7, 4));
        world.run();

    }
}
