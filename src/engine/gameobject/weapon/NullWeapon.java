package engine.gameobject.weapon;

import engine.gameobject.PointSimple;
import engine.gameobject.units.Buff;
import engine.gameobject.weapon.firingstrategy.Projectile;
import gameworld.ObjectCollection;

public class NullWeapon implements Weapon{
    
    
    @Override
    public void fire (ObjectCollection world, PointSimple location) {
        //Do nothing
    }

    @Override
    public void addBuff (Buff newBuff) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public double getValue () {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setProjectile (Projectile projectile) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public double getRange () {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getFiringRate () {
        // TODO Auto-generated method stub
        return 0;
    }
}
