package engine.gameobject.weapon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import engine.gameobject.GameObject;
import engine.gameobject.GameObjectSimple;
import engine.gameobject.behavior.Behavior;
import engine.gameobject.weapon.firingstrategy.FiringStrategy;


/**
 * 
 * @author Nathan Prabhu
 *
 */
public class WeaponSimple implements Weapon {
    private double myRange;
    private double myFiringRate;
    private List<Behavior> myBehaviors;
    private FiringStrategy myFiringStrategy;
    

    public WeaponSimple (double range,
                         double firingRate,
                         FiringStrategy firingStrategy,
                         Behavior ... behaviors) {
        myRange = range;
        myFiringRate = firingRate;
        myFiringStrategy = firingStrategy;
        myBehaviors = new ArrayList<>(Arrays.asList(behaviors));
    }

    @Override
    public double getRange () {
        return myRange;
    }

    @Override
    public double getFiringRate () {
        return myFiringRate;
    }
    
    public FiringStrategy getFiringStrategy () {
        return myFiringStrategy;
    }

    @Override
    public void attack (GameObject... targets) {
        Arrays.asList(targets).forEach(target -> {
            myBehaviors.forEach(behavior -> behavior.apply(target));
        });
    }
    
    @Override
    public void addBehavior (Behavior behavior) {
        // add behavior if it doesn't exist, otherwise upgrade existing one 
    }

    @Override
    public void addRange (double value) {
        myRange += value;
    }

}
