package game.characters;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.GameMap;
import game.core.FancyMessage;
import game.weapons.BareFist;

/**
 * Class representing the Player.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * @author Ian Lai
 * @version 1.0.0
 */
public class Player extends Actor {
    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Player(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        // Default Player Mana and Strength
        int manaPoints = 100;
        int strengthPoints = 5;
        // Addition of Attributes using BaseActorAttribute
        this.addAttribute(BaseActorAttributes.MANA, new BaseActorAttribute(manaPoints));
        this.addAttribute(PlayerActorAttribute.STRENGTH, new BaseActorAttribute(strengthPoints));
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(Status.TARNISHED_ALLY);
        this.addCapability(Ability.PLAYER_ATTACK_ONLY); // This addCapability add by: Junyan; This Enum which is from Ability
        this.addCapability(Status.IS_PLAYER);
        this.setIntrinsicWeapon(new BareFist());
    }

    /**
     * Gives a player it's turn
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return  the Action Player takes on this turn
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        System.out.println(this.playerState());
        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        // return/print the console menu
        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);
    }

    /**
     * Method that can be executed when the Player is unconscious due to the action of another actor
     * @param actor the perpetrator
     * @param map   where the actor fell unconscious
     * @return  a String describing what happened when the Player is unconscious
     */
    @Override
    public String unconscious(Actor actor, GameMap map){
        String result = super.unconscious(actor, map);
        // OH NO! THE TARNISHED IS DEAD
        for (String line : FancyMessage.YOU_DIED.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return result;
    }

    /**
     * A method that gets a String representing the Player's health, Mana, And Strength
     * @return  returns a String to display the Player's Health, Mana, and Strength
     */
    private String playerState() {
        return name + " (" +
            this.getAttribute(BaseActorAttributes.HEALTH) + "/" +
            this.getAttributeMaximum(BaseActorAttributes.HEALTH) +
            ")\nMana: (" + this.getAttribute(BaseActorAttributes.MANA) + "/" +
            this.getAttributeMaximum(BaseActorAttributes.MANA) +
            ")\nStrength: " + this.getAttribute(PlayerActorAttribute.STRENGTH) +
            // A3 Task 4 added this.getBalance() to display balance
            "\nGold: " + this.getBalance();
    }
}
