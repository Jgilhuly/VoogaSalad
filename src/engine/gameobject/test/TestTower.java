package engine.gameobject.test;

import engine.gameobject.Graphic;
import engine.gameobject.HealthSimple;
import engine.gameobject.MoverNull;
import engine.gameobject.PointSimple;
import engine.gameobject.units.BuffableUnit;

public class TestTower extends BuffableUnit{
    
    public TestTower (int type, int xcor, int ycor) {
        super();
        myImagePath = "robertDuvall.jpg";
        myLabel = "";
        myHealth = new HealthSimple(3);
        myPoint = new PointSimple(xcor, ycor);
        myMover = new MoverNull();
        myGraphic = new Graphic(60, 60, myImagePath);
        myGraphic.setPoint(myPoint);
        super.setWeapon(new TestWeapon(type));
    }
    
    
}
