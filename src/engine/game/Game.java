package engine.game;

import java.util.List;
import engine.shop.ShopModel;
import View.ButtonWrapper;


/**
 * A class can implement the game interface when it can be updated each frame, has a shop and a
 * LevelBoard and a Player, and the ability to support/contain ButtonWrappers.
 * 
 * @author Sierra
 *
 */
public interface Game {

    /**
     * Updates everything in the game by one frame.
     */
    public void update ();

    /**
     * Returns the shop for the game.
     * 
     * @return
     */
    public ShopModel getShop ();

    /**
     * Returns the level board for the game.
     * 
     * @return
     */
    public LevelBoard getLevelBoard ();

    /**
     * Adds the given ButtonWrapper to the game's collection of ButtonWrappers.
     * 
     * @param button
     */
    public void addButton (ButtonWrapper button);

    /**
     * Returns a list of the game's ButtonWrappers.
     * 
     * @return
     */
    public List<ButtonWrapper> getButtons ();

    /**
     * Returns the game's Player object.
     * 
     * @return
     */
    public Player getPlayer ();

}
