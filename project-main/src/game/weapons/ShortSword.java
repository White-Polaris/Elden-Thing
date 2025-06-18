package game.weapons;

/**
 * A Class representing a ShortSword Weapon
 * @author Ian Lai
 * @version 1.0.0
 */

public class ShortSword extends WeaponItem {

    private static final String NAME = "Short Sword";
    private static final int DAMAGE = 100;
    private static final int HIT_RATE = 70;
    private static final String VERB = "slashes";
    private static final char DISPLAY_CHAR = '!';
    private static final int STRENGTH_REQUIRED = 10;

    /**
     * Constructor
     */
    public ShortSword(){
        super(NAME, DISPLAY_CHAR, DAMAGE, VERB, HIT_RATE, STRENGTH_REQUIRED);
    }
}
