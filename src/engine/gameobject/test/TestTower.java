package engine.gameobject.test;

import engine.gameobject.GameObjectSimple;
import engine.gameobject.Graphic;
import engine.gameobject.HealthSimple;
import engine.gameobject.MoverUser;
import engine.gameobject.PointSimple;

public class TestTower extends GameObjectSimple{
    
    private int type;
    
    public TestTower (int type, int xcor, int ycor) {
        super();
        this.type = type;
        Graphic graphic = new Graphic(40, 40, "Bloons_TackShooter.png");
        setGraphic(graphic);
        setPoint(new PointSimple(xcor, ycor));
        setHealth(new HealthSimple(3));
        MoverUser moveruser = new MoverUser();
        moveruser.setGraphic(graphic);
        setMover(moveruser);
        setWeapon(new TestWeapon(type));
        getTag().setName("TestTower");
        getTag().setDescription("Just a test tower; nothing special here...");
        getTag().setShopGraphic(new Graphic(40, 40, "Bloons_TackShooterIcon.png"));
        setLabel(new TowerLabel());
    }
    
    
    
}