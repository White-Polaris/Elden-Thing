package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.action.AttackAction;
import game.characters.Ability;
import game.characters.PlayerActorAttribute;
import game.core.StateManager;
import game.characters.Status;
import game.weapons.weaponarts.Lifesteal;
import game.weapons.weaponarts.Memento;
import game.weapons.weaponarts.Quickstep;
import game.weapons.weaponarts.WeaponArtsEnum;

import java.util.Random;

/**
 * Class representing items that can be used as a weapon.
 * @author Ian Lai
 * @version 1.0.0
 */
public abstract class WeaponItem extends Item implements Weapon {
    private static final float DEFAULT_DAMAGE_MULTIPLIER = 1.0f;
    private int damage;
    private int hitRate;
    private String verb;
    private float damageMultiplier;
    private int strengthRequired;
    private StateManager stateManager = new StateManager();

    /**
     * Constructor
     * @param name  The Name of the Weapon
     * @param displayChar   The Character of the Weapon to be displayed in the Menu
     * @param damage    The Damage dealt by this Weapon
     * @param verb      The act of using the Weapon
     * @param hitRate   The chance of hitting a targetwith this Weapon
     * @param strengthRequired  An integer representing the strengthPoints required to use this weapon
     */
    public WeaponItem(String name, char displayChar, int damage, String verb, int hitRate, int strengthRequired) {
        super(name, displayChar, true);
        this.damage = damage;
        this.verb = verb;
        this.hitRate = hitRate;
        this.damageMultiplier = DEFAULT_DAMAGE_MULTIPLIER;
        this.strengthRequired = strengthRequired;
    }

    /**
     * Constructor
     * @param name  The Name of the Weapon
     * @param displayChar   The Character of the Weapon to be displayed in the Menu
     * @param damage    The Damage dealt by this Weapon
     * @param verb      The act of using the Weapon
     * @param hitRate   The chance of hitting a targetwith this Weapon
     */
    public WeaponItem(String name, char displayChar, int damage, String verb, int hitRate) {
        super(name, displayChar, true);
        this.damage = damage;
        this.verb = verb;
        this.hitRate = hitRate;
        this.damageMultiplier = DEFAULT_DAMAGE_MULTIPLIER;
    }

    /**
     * A method that allows an Actor to pick up an instance of WeaponItem, given it is strong enough
     * @param actor The Actor picking up the Weapon
     * @return  a PickUpAction if the actor is able to pick up a Weapon
     */
    @Override
    public PickUpAction getPickUpAction(Actor actor){
        // Fixed the problem by: Ian
        if (actor.hasAttribute(PlayerActorAttribute.STRENGTH)) {
            if (actor.getAttribute(PlayerActorAttribute.STRENGTH) >= this.strengthRequired) {
                return new PickUpAction(this);
            }
        }
        return null;

    }

    /**
     * Method to get a String representing the act of an attack with a WeaponItem
     * @param attacker the actor who performed the attack
     * @param target   the actor who is the target of the attack
     * @param map      the map on which the attack was executed
     * @return  a String representing the act of an attack with a WeaponItem
     */
    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        Random rand = new Random();
        if (!(rand.nextInt(100) < this.hitRate)) {
            return attacker + " misses " + target + ".";
        }

        target.hurt(Math.round(damage * damageMultiplier));

        return String.format("%s %s %s for %d damage", attacker, verb, target, damage);
    }

    /**
     * A List of Actions
     * @param otherActor the other actor
     * @param location the location of the other actor
     * @return  a List of Allowable Actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location){
        ActionList actions = new ActionList();
        String direction = location.toString();

        // This conditional sentence Ability.PLAYER_ATTACK_ONLY add by: Junyan
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) | otherActor.hasCapability(Ability.PLAYER_ATTACK_ONLY)) {
            // This is used to detect if the weapon possess a WeaponArt by: Guo Xun
            if (this.hasCapability(WeaponArtsEnum.LIFESTEAL)) {
                actions.add(new Lifesteal(otherActor, direction, this));
            } else if (this.hasCapability(WeaponArtsEnum.QUICKSTEP)) {
                actions.add(new Quickstep(otherActor, direction, this));
            } else if (this.hasCapability(WeaponArtsEnum.MEMENTO)){
                actions.add(new Memento(otherActor, direction, this, stateManager));
            } else {
                actions.add(new AttackAction(otherActor, direction, this));
            }

        }
        return actions;
    }

}
