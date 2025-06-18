package game.core;

import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * A Class that stores the State of an Actor, including all it's attributes such as Health, Mana, Strength, etc.
 * @author Ian Lai
 * @version 1.0.0
 */

public class State {

    private int health;
    private int mana;
    private int strength;
    private Location location;
    private ArrayList<StatusEffect> statusEffects;

    /**
     * Default Constructor
     * @param health        The health of the actor
     * @param mana          The mana of the actor
     * @param strength      The strength of the actor
     * @param location      The location of the actor
     * @param statusEffects A list of statusEffects an actor has
     */
    public State(int health, int mana, int strength, Location location, ArrayList<StatusEffect> statusEffects) {
        this.health = health;
        this.mana = mana;
        this.strength = strength;
        this.location = location;
        this.statusEffects = statusEffects;
    }

    /**
     * A method that returns the stored health value
     * @return  An integer representing Health
     */
    public int getHealth() {
        return health;
    }

    /**
     * A method that returns the stored mana value
     * @return  An integer representing Mana
     */
    public int getMana() {
        return mana;
    }

    /**
     * A method that returns the stored strength value
     * @return  An integer representing strength
     */
    public int getStrength() {
        return strength;
    }

    /**
     * A method that returns the stored Location
     * @return  A Location representing where the Actor is
     */
    public Location getLocation() {
        return location;
    }

    /**
     * A method that returns an ArrayList of StatusEffect objects
     * @return  A List of StatusEffect objects
     */
    public ArrayList<StatusEffect> getStatusEffects(){
        return statusEffects;
    }

    /**
     * A method that returns the Ground from the Location saved
     * @return A saved Ground object
     */
    public Ground getGround(){
        return location.getGround();
    }

    /**
     * A method that returns Items from a saved Location
     * @return  A List of Item objects
     */
    public List<Item> getItems(){
        return location.getItems();
    }
}
