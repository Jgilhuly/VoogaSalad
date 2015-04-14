package engine.gameobject.units;

import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;
import engine.gameobject.BasicMover;
import engine.gameobject.GameObjectSimple;
import engine.gameobject.weapon.BasicWeapon;
import engine.gameobject.weapon.Weapon;
import engine.pathfinding.EndOfPathException;
import gameworld.ObjectCollection;


public class BuffableUnit extends GameObjectSimple implements Buffable, Serializable{
    private List<Buff> buffList;
    private Weapon myWeapon;

    public BuffableUnit(){
        super();
        buffList = new ArrayList<>();
        myWeapon = new BasicWeapon();
    }
    
    public double getHealth(){
        return myHealth.getHealth();
    }
    
    @Override
    public void update (ObjectCollection world) {
        advanceBuffs();
        try{
            move();
        }
        catch (EndOfPathException e){
            //TODO: ENCODE END OF PATH BEHAVIOR
        }
        myWeapon.fire(world, myPoint);        
    }
    
    public void addBuff (Buff toAdd) {
        Buff equalBuff = findEqualBuff(toAdd);
        if (equalBuff == null) {
            applyBuff(toAdd);
        }
        else if (toAdd.isStrongerBuff(equalBuff)) {
            removeBuff(equalBuff);
            applyBuff(toAdd);
        }
    }
    
    /********
     * advanceBuffs would need to be on the list of things updated every frame/unit of time.
     ********/
    private void advanceBuffs () {
        ArrayList<Buff> removeBuffer = new ArrayList<Buff>();
        for (Buff buff : buffList) {
            if (buff.timeLeft() <= 0) {
                removeBuffer.add(buff);
            }
            buff.advanceTime(1, this);
        }
        for(Buff toRemove : removeBuffer){
            removeBuff(toRemove);
        }
    }

    private void applyBuff(Buff b){
        b.apply(this);
        buffList.add(b);
    }
    
    private void removeBuff(Buff b){
        buffList.remove(b);
        b.unapply(this);
    }
    
    private Buff findEqualBuff (Buff toAdd) {
        for (Buff b : buffList) {
            if (toAdd.getClass().equals(b.getClass())) {
                return b;
            }
        }
        return null;
    }

    @Override
    public void setWeapon (Weapon weapon) {
        myWeapon = weapon;
        
    }

    @Override
    public Weapon getWeapon () {
        return myWeapon;
    }

    @Override
    public void onDeath (ObjectCollection world) {
        // TODO Auto-generated method stub
        
    }

}
