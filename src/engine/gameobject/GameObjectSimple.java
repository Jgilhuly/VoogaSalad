package engine.gameobject;

import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import engine.gameobject.Health;
import engine.gameobject.Mover;
import engine.gameobject.PointSimple;
import engine.gameobject.weapon.Weapon;
import engine.pathfinding.EndOfPathException;


/**
 * 
 * @author Jeremy
 *
 */
public class GameObjectSimple implements GameObject {
    private Node myNode;
    private String myImagePath;
    private String myLabel;
    private PointSimple myPoint;
    private Health myHealth;
    private Mover myMover;
    private Weapon myWeapon;
    private Graphic myGraphic;

    @Override
    public boolean isDead () {
        return myHealth.isDead();
    }

    @Override
    public void changeHealth (double amount) {
        myHealth.changeHealth(amount);
    }

    // temporary
    public GameObject clone () {
        try {
            return (GameObject) super.clone();
        }
        catch (CloneNotSupportedException e) {
            System.out.println(this.getLabel() + " can't be cloned");
            return null;
        }
    }

    @Override
    public String getLabel () {
        return myLabel;
    }

    @Override
    public PointSimple getPoint () {
        return new PointSimple(myPoint);
    }

   
    public void initializeNode () {
        Image image = new Image(myImagePath);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        myNode = imageView;
    }

    @Override
    public void move () throws EndOfPathException {
        // TODO Auto-generated method stub
        PointSimple point = myMover.move(myPoint);
        myPoint = new PointSimple(new Point2D(point.getX(), point.getY()));
    }

    @Override
    public void setSpeed (double speed) {
        // TODO Auto-generated method stub

    }

  
    @Override
    public Graphic getGraphic () {
        // TODO Auto-generated method stub
        return myGraphic;
    }
    
    String getImagePath() {
        return myImagePath;
    }
    
    void setImagePath(String imgpath) {
        myImagePath = imgpath;
    }
    
    void setLabel(String label) {
        myLabel = label;
    }
    
    void setPoint(PointSimple point) {
        myPoint = point;
    }

    Health getHealth() {
        return myHealth;
    }
    
    void setHealth(Health health) {
        myHealth = health;
    }
    
    Mover getMover() {
        return myMover;
    }
    
    void setMover(Mover mover) {
        myMover = mover;
    }
    
    void setGraphic(Graphic graphic) {
        myGraphic = graphic;
    }

    @Override
    public Weapon getWeapon () {
        return myWeapon;
    }
    @Override
    public void setWeapon(Weapon weapon)  {
        myWeapon = weapon;
    }
}
