package engine.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import View.Displayable;
import engine.fieldsetting.Settable;
import engine.shop.wallet.ConcreteWallet;
import engine.shop.wallet.Wallet;


/**
 * This class represents a current player of the game, storing player specific information
 * such as lives left/health, score, and currency that can be used in the store.
 * 
 * @author Sierra Smith, Cosette Goldstein
 *
 */
@Settable
public class Player extends Observable {

    private String myName;
    private PlayerUnit myHealth;
    private PlayerUnit myScore;
    private Wallet myWallet;

    // note: wallet needs to be initialzed with the right player unit before passed in here?
    // alternative: could pass in a string or something to indicate which player unit to assign the
    // wallet

    public Player () {
        myName = "";
        myHealth = new PlayerUnit();
        myScore = new PlayerUnit();
        myWallet = new ConcreteWallet();
    }

    public Player (String name, PlayerUnit health, PlayerUnit score, Wallet wallet) {
        myHealth = health;
        myScore = score;
        myName = name;
        myWallet = wallet;
    }

    @Settable
    public void setName (String name) {
        myName = name;
    }

    /**
     * Adds the specifies amount to the player's score unit.
     * @param toAdd
     */
    public void changeScore (int toAdd) {
        myScore.changeValue(toAdd);
        setChanged();
        notifyObservers(myScore);
    }

    /**
     * Adds the specified amount to the player's health unit.
     * @param change
     */
    public void changeHealth (int change) {
        myHealth.changeValue(change);
        setChanged();
        notifyObservers(myHealth);
    }

    public double getScore () {
        return myScore.getValue();
    }

    public double getHealth () {
        return myHealth.getValue();
    }

    public Wallet getWallet () {
        return myWallet;
    }

    public String getName () {
        return myName;
    }

    public List<Displayable> getDisplayables () {
        List<Displayable> toReturn = new ArrayList<>();
        toReturn.add(myHealth);
        toReturn.add(myScore);
        return toReturn;
    }

    @Settable
    public void setHealth (PlayerUnit health) {
        myHealth = health;
    }

    @Settable
    public void setScore (PlayerUnit score) {
        myScore = score;
    }

    @Settable
    public void setWallet (Wallet wallet) {
        myWallet = wallet;
    }

}
