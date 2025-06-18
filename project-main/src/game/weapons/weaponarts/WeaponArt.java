package game.weapons.weaponarts;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;

/**
 * WeaponArt parent class used to hold properties of WeaponArts
 */
public abstract class WeaponArt extends Action {

    private int manaCost;
    private Weapon weapon;
    private Actor target;
    private String direction;

    /**
     * Parent constructor used by child classes of WeaponArt
     * @param manaCost mana cost of the WeaponArt
     * @param weapon the weapon that contains this WeaponArt
     */
    public WeaponArt(int manaCost, Actor target, String direction, Weapon weapon ){
        this.manaCost = manaCost;
        this.target = target;
        this.direction = direction;
        this.weapon = weapon;
    }

    public int getManaCost(){
        return this.manaCost;
    }

    public Actor getTarget(){
        return this.target;
    }

    public String getDirection(){
        return this.direction;
    }

    public Weapon getWeapon() {
        return this.weapon;
    }

    @Override
    public abstract String execute(Actor attacker, GameMap map);

    @Override
    public abstract String menuDescription(Actor actor);

}
