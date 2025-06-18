package game.location;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * This class allows for Items in the World to have multiple choices of Actions. This class extends the Item class.
 * @author Ian Lai
 * @version 1.0.0
 */

public abstract class WorldItem extends Item {

    /**
     * A List of Actions that can be completed with this Item
     */
    protected List<Action> worldActions = new ArrayList<>();
    /**
     * A Location object storing the Location of where the WorldItem lies
     */
    protected Location itemLocation;

    /**
     * Constructor
     * @param name  A String representing the name of the Item
     * @param displayChar   A char representing the icon of the Item
     * @param portable  A boolean representing if this item can be picked up and moved
     */
    public WorldItem(String name, char displayChar, boolean portable, Location itemLocation) {
        super(name, displayChar, portable);
        this.itemLocation = itemLocation;
    }

    /**
     * A method to add an allowable Action to a WorldItem
     * @param newAction  The Action that can be done with a WorldItem
     */
    protected void addAction(Action newAction){
        this.worldActions.add(newAction);
    }

    /**
     * A method to return the Location in which this WorldItem lies
     * @return  A Location object of where the WorldItem lies
     */
    public Location getItemLocation(){
        return this.itemLocation;
    }

    /**
     * Overrides the allowableActions method of the Item class with it's own list of allowableActions
     * @param location the location of the ground on which the item lies
     * @return  A list of allowableActions
     */
    @Override
    public ActionList allowableActions(Location location) {
        ActionList actions = super.allowableActions(location);
        for (Action action : worldActions) {
            actions.add(action);
        }
        return actions;
    }
}
