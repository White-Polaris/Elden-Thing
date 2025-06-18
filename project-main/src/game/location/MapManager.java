package game.location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapManager {

    /**
     * A HashMap storing a key-value pair of gameMap objects, where the name of the map is its key
     */
    private final Map<String, EnhancedGameMap> gameMaps = new HashMap<>();

    /**
     * Constructor that initializes all maps.
     */
    public MapManager() {
        gameMaps.put("Gravesite Plains", new GravesitePlains(this));
        gameMaps.put("Belarut, Tower Settlement", new BelarutTowerSettlement(this));
        gameMaps.put("Belarut Sewers", new BelarutSewers(this));
        gameMaps.put("Stagefront", new Stagefront(this));
    }

    /**
     * A static method to return a particular GameMap
     * @param mapName A String representing the name of the GameMap
     * @return  The desired GameMap object
     */
    public EnhancedGameMap getGameMap(String mapName) {
        EnhancedGameMap map = gameMaps.get(mapName);
        if (map == null) {
            System.out.println("This GameMap does not exist");
        }
        return map;
    }

    /**
     * A static method to return all existing GameMaps as a List
     * @return  An ArrayList of EnhancedGameMaps representing all the existing maps in the HashMap
     */
    public ArrayList<EnhancedGameMap> getGameMaps() {
        return new ArrayList<>(gameMaps.values());
    }

    /**
     * A static method that initialises the entities in each EnhancedGameMap stored in the HashMap
     */
    public void initialiseGameMaps() {
        for (EnhancedGameMap map : gameMaps.values()) {
            map.initialiseEntities();
        }
    }
}
