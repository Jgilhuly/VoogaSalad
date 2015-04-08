package engine.gameobject.weapon.upgradable.behavior;

import engine.gameobject.GameObject;
import engine.gameobject.weapon.upgradable.Upgradable;

/**
 * Actions associated with a weapon. They will be applied every time a weapon is used on the 
 * intended target of a GameObject's attack. 
 * @author Nathan Prabhu and Tom Puglisi
 *
 */
public interface Behavior extends Upgradable {
    
    public void apply(GameObject target);
    
}
