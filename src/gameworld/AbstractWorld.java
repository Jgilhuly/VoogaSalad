package gameworld;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javafx.scene.Node;
import engine.gameobject.GameObject;
import engine.gameobject.GameObjectSimpleTest;
import engine.gameobject.PointSimple;
import engine.gameobject.test.TestTower;
import engine.gameobject.units.Buffable;
import engine.gameobject.weapon.firingstrategy.Buffer;
import engine.grid.Grid;
import engine.grid.GridFree;
import engine.interactions.InteractionEngine;
import engine.pathfinding.EndOfPathException;
import engine.pathfinding.Path;


public class AbstractWorld implements GameWorld{
    private List<GameObject> myObjects;
//    private InteractionEngine myCollisionEngine;

    public AbstractWorld () {
        myObjects = new ArrayList<GameObject>();
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
         for (GameObject o: currentObjects){
             o.update(this);
         }
         //TODO: Tell each object to fire.
         checkCollisions();
         removeDeadObjects();
    }

    private void checkCollisions () {
        //TODO: Use collision engine to do all colisions
    }
    

    private void removeDeadObjects () {
        // TODO Auto-generated method stub
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

