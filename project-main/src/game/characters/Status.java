package game.characters;

/**
 * Use this enum class to represent a status.
 * Example #1: if the player is sleeping, you can attack a Status.SLEEP to the player class
 * Created by:
 * @author Riordan D. Alfredo
 */
public enum Status {
    /**
     * An Enum value representing if this Actor is Hostile to Enemies
     */
    HOSTILE_TO_ENEMY,
    /**
     * An Enum value representing if this Actor is Tracking an Enemy
     */
    TRACKING_ENEMY,
    /**
     * An Enum value representing if this Actor is allies with a FurnaceGolem
     */
    FURNACE_GOLEM_ALLY,
    /**
     * An Enum value representing if this Actor is allies with the Player (Tarnished)
     */
    TARNISHED_ALLY,
    /**
     * An Enum value representing if this Actor is immune to Burn Damage
     */
    BURN_IMMUNITY,
    /**
     * An Enum value representing is this Actor is immune to poison; add by: Junyan
     */
    POISON_IMMUNE,
    /**
     * An Enum value representing if this Actor is allies with the Scarab
     */
    SCARAB_ALLY,
    /**
     * An Enum value representing if this Actor is allies with a Spirit
     */
    SPIRIT_ALLY,
    /**
     * An Enum value representing if this Actor is allies with a ManFly
     */
    MAN_FLY_ALLY,
    /**
     * An Enum value representing if this Actor is allies with a DancingLion
     */
    DANCING_LION_ALLY,
    /**
     * An Enum value representing if this Actor is a player
     */
    IS_PLAYER
}
