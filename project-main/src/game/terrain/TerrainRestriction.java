package game.terrain;

/**
 * This Enum class is used to restrict entities from walking on certain Terrains
 * @author Ian Lai
 * @version 1.0.0
 */

public enum TerrainRestriction {
    /**
     * An Enum value representing no restrictions on Terrain
     */
    NONE,
    /**
     * An Enum value representing a restriction on Dirt
     */
    DIRT,
    /**
     * An Enum value representing a restriction on Floor
     */
    FLOOR,
    /**
     * An Enum value representing a restriction on Puddle
     */
    PUDDLE,
    /**
     * An Enum value representing a restriction on Fire
     */
    FIRE,
    /**
     * An Enum value representing a restriction on Poison Swamp
     */
    POISON_SWAMP,
    /**
     * An Enum value representing a restriction on Wall
     */
    WALL // No Actor can enter a wall (as of now)
}
