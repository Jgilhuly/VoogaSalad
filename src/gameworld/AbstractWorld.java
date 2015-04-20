package gameworld;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javafx.scene.Node;
import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import engine.gameobject.test.EnemyLabel;
import engine.gameobject.test.ProjectileLabel;
import engine.gameobject.test.TowerLabel;
import engine.interactions.BuffImparter;
import engine.interactions.CollisionEngine;
import engine.interactions.InteractionEngine;
import engine.interactions.RangeEngine;
import engine.interactions.ShootAt;


public class AbstractWorld implements GameWorld{
    private List<GameObject> myObjects;
    private InteractionEngine myCollisionEngine;
    private InteractionEngine myRangeEngine;
    
    public AbstractWorld () {
        myObjects = new ArrayList<GameObject>();
        initiateCollisionEngine();
        initiateRangeEngine();
    }

/*
 * The private methods that follow is unofficial code:
 * sets up the interaction engines to defaults. Set interaction engine methods may be needed.
 */
    
    private void initiateCollisionEngine(){
        myCollisionEngine = new CollisionEngine();
        myCollisionEngine.setWorld(this);
        myCollisionEngine.put(new ProjectileLabel(), new EnemyLabel(), new BuffImparter());
    }
    
    private void initiateRangeEngine(){
        myRangeEngine = new RangeEngine();
        myRangeEngine.setWorld(this);
        myRangeEngine.put(new TowerLabel(), new EnemyLabel(), new ShootAt());
    }
    
    @Override
    public void addObject (GameObject toSpawn, PointSimple pixelCoords) throws StructurePlacementException {
        myObjects.add(toSpawn);
        toSpawn.setPoint(pixelCoords);// TODO change from pixel coords
        // myGrid.addObject(toSpawn);
        }

    @Override
    public void updateGameObjects () {
    	
         ArrayList<GameObject> currentObjects = new ArrayList<GameObject>(myObjects);
         for (GameObject object: currentObjects){
             object.update(this);
             for (GameObject interactObject: currentObjects){
                 if (interactObject != object){
                     myCollisionEngine.interact(object, interactObject);
                     myRangeEngine.interact(object, interactObject);
                 }
             }
         }
         removeDeadObjects();
    }
    
    private void removeDeadObjects () {
        ArrayList<GameObject> buffer = new ArrayList<GameObject>();
        myObjects.forEach(go -> {
            if (go.isDead()) {
                buffer.add(go);
            }
        });
        for (GameObject toRemove: buffer){
            myObjects.remove(toRemove);
            toRemove.onDeath(this);
        }

    }

    @Override
    public Collection<GameObject> getGameObjects () {
        return Collections.unmodifiableList(myObjects);
    }

    @Override
    public Collection<GameObject> objectsInRange (double range, PointSimple center) {
        ArrayList<GameObject> inRange = new ArrayList<>();
        for (GameObject o : myObjects) {
            if (center.withinRange(o.getPoint(), range)) {
                inRange.add(o);
            }
        }
        return Collections.unmodifiableList(inRange);
    }

    @Override
    public void addObject (GameObject toSpawn) {
        myObjects.add(toSpawn);
    }

    @Override
    public boolean isPlacable (Node n, PointSimple pixelCoords) {
        return true; // TODO plz replace with logic. Ex: towers cannot be placed on towers
    }

}

