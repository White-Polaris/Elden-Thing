package game.weapons;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * Class representing a bare fist
 * @author Adrian Kristanto
 * @author Ian Lai
 * @version 1.0.0
 */
public class BareFist extends IntrinsicWeapon {
    private String name = "Bare Fist";

    /**
     * Constructor
     */
    public BareFist() {
        super(25, "punches", 50);
    }

    /**
     * Returns the name of a BareFist
     * @return  the name of a BareFist
     */
    @Override
    public String toString(){
        return name;
    }

}
