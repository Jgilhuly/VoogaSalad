package gameworld;

import java.util.Collection;
import java.util.List;
import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import engine.grid.StructurePlacementException;
import engine.pathfinding.Path;


public interface GameWorld {
    public void updateGameObjects ();
    public void moveObjects();
    public void checkCollisions();
    public void removeDeadObjects();
    public Path getPathFinder ();
    public Collection<GameObject> getGameObjects();
    public void addObject (GameObject object) throws StructurePlacementException;
    public Collection<GameObject> objectsInRange(double range, PointSimple center);
}