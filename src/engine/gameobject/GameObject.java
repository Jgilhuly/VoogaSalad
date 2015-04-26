package engine.gameobject;

import engine.gameobject.behaviors.Behavior;
import engine.gameobject.behaviors.EndBehaviorful;
import engine.gameobject.labels.Type;
import engine.gameobject.units.Buffable;
import engine.gameobject.units.Colliding;
import engine.gameobject.units.Firing;
import engine.prototype.Prototype;
import engine.shop.PurchasableGameObject;
import engine.shop.tag.GameObjectTag;
import gameworld.ObjectCollection;


/**
 *
 * @author Jeremy, Kaighn
 *
 */

public interface GameObject extends Firing, Colliding, Buffable, Movable, Health, PurchasableGameObject, Prototype<GameObject>, EndBehaviorful {

    // public void updateGraphics ();//cannot implement yet

    /**
     * Updates the object accordingly within the objectcollection (usually gameworld) given
     *
     * @param world
     */
    public void update (ObjectCollection world);
    
    /**
     * Performs the ondeath behavior of the object upon the world
     * @param world
     */
    public void onDeath (ObjectCollection world);
    
    public Mover getMover ();

    public Type getLabel ();

    public void setGraphic (Graphic graphic);

    public void setLabel (Type label);

    /**
     * Tags contain important GameObject info (e.g. name, description, image)
     *
     * @return
     */
    @Override
    public GameObjectTag getTag ();

    /**
     * Returns the Cartesian coordinate of the game object.
     */
    public PointSimple getPoint ();

    /**
     * Sets the object's location to point
     *
     * @param point
     */
    public void setPoint (PointSimple point);

    /**
     * Sets the GameObject's Mover
     */
    public void setMover (Mover mover);

    public void setHealth (Health clone);

    public void setTag (GameObjectTag myTag);

}
