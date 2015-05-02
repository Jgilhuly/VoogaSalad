package SuperAwesomeDemo;

import engine.gameobject.GameObjectSimple;
import engine.gameobject.PointSimple;
import engine.gameobject.graphics.Graphic;
import engine.gameobject.graphics.RotatorNull;
import engine.gameobject.healths.HealthSimple;
import engine.gameobject.movers.MoverDirection;
import engine.gameobject.units.directdamage.DamageBuff;
import engine.gameobject.weapon.NullWeapon;


public class BasicFriendlyProjectile extends GameObjectSimple {
    
    public BasicFriendlyProjectile(){
        this(1, 1, 100);
    }
    public BasicFriendlyProjectile (int damage, double speed, double distanceLimit) {
        super();
        setType(new FriendlyProjectileType());
        Graphic myGraphic = new Graphic(7,7,"/images/Black_dot.png");
        myGraphic.setRotator(new RotatorNull());
        setGraphic(myGraphic);
        setPoint(new PointSimple(0, 10000));
        setHealth(new HealthSimple(1));
        setWeapon(new NullWeapon());
        MoverDirection myMover = new MoverDirection(new PointSimple(0, 0), speed, distanceLimit);
        getCollider().addCollisionBehavior(new DamageBuff(damage));
        setMover(myMover);
    }
}
