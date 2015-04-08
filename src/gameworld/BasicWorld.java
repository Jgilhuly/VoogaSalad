package gameworld;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import engine.gameobject.GameObject;
import engine.grid.Grid;
import engine.grid.StructurePlacementException;
import engine.interactions.InteractionEngine;
import engine.pathfinding.EndOfPathException;
import engine.pathfinding.Path;


public class BasicWorld implements GameWorld {
    private ArrayList<GameObject> myObjects;
    private Grid myGrid;
    private InteractionEngine myCollisionEngine;

    public BasicWorld () {
        myObjects = new ArrayList<GameObject>();
    }

    @Override
    public void addObject (GameObject toSpawn) throws StructurePlacementException {
        myObjects.add(toSpawn);
        myGrid.addObject(toSpawn);
    }

    @Override
    public void updateGameObjects () {
        // TODO Auto-generated method stub

    }

    @Override
    public Path getPathFinder () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void moveObjects () {
        myObjects.forEach(go -> {
            try {
                go.move();
            }
            catch (EndOfPathException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

    @Override
    public void checkCollisions () {
        myObjects.forEach(go1 -> {
            myObjects.forEach(go2 -> {
                if (go1.getGraphic().getNode().getBoundsInParent()
                        .intersects(go2.getGraphic().getNode().getBoundsInParent()))
                    myCollisionEngine.interact(go1, go2);
            });
        });

    }

    @Override
    public void removeDeadObjects () {
        // TODO Auto-generated method stub
        myObjects.forEach(go -> {
            if (go.isDead()) {
                myObjects.remove(go);
                myGrid.removeObject(go);
            }

        });

    }

    @Override
    public Collection<GameObject> getGameObjects () {
        return Collections.unmodifiableList(myObjects);
    }

    @Override
    public List<GameObject> objectsInRange (double range, GameObject center) {
        ArrayList<GameObject> inRange = new ArrayList<>();
        for (GameObject o : myObjects){
            if (center.getPoint().withinRange(o.getPoint(), range)){
                inRange.add(o);
            }
        }
        return Collections.unmodifiableList(inRange);
    }
    

}
