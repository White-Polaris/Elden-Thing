package game.weapons;

/**
 * A Class representing a GreatKnife Weapon
 * @author Ian Lai
 * @version 1.0.0
 */

public class GreatKnife extends WeaponItem {

    private static final String NAME = "Great Knife";
    private static final int DAMAGE = 75;
    private static final int HIT_RATE = 60;
    private static final String VERB = "jabs";
    private static final char DISPLAY_CHAR = '†';
    private static final int STRENGTH_REQUIRED = 5;

    /**
     * Constructor
     */
    public GreatKnife(){
        super(NAME, DISPLAY_CHAR, DAMAGE, VERB, HIT_RATE, STRENGTH_REQUIRED);
    }
}
