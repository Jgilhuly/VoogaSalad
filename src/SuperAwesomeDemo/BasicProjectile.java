package SuperAwesomeDemo;

import engine.gameobject.GameObjectSimple;
import engine.gameobject.Graphic;
import engine.gameobject.HealthSimple;
import engine.gameobject.MoverDirection;
import engine.gameobject.PointSimple;
import engine.gameobject.RotatorNull;
import engine.gameobject.test.ProjectileLabel;
import engine.gameobject.units.directdamage.DamageBuff;
import engine.gameobject.weapon.NullWeapon;


public class BasicProjectile extends GameObjectSimple {
    public BasicProjectile (int damage, double speed, double distanceLimit) {
        super();
        setLabel(new ProjectileLabel());
        Graphic myGraphic = new Graphic(25,25,"/images/robertDuvall.jpg");
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
