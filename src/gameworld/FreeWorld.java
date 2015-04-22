package gameworld;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
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
import voogasalad.util.pathsearch.graph.GridCell;
import voogasalad.util.pathsearch.pathalgorithms.NoPathExistsException;
import engine.interactions.InteractionEngine;
import engine.pathfinding.EndOfPathException;
import engine.pathfinding.Path;
import engine.pathfinding.PathFree;

/**
 * GameWorld where structures belong to a grid.
 * @author Kaighn
 *
 */
public class FreeWorld extends AbstractWorld {
//	private List<GameObject>[][] myGrid;
	private GameObject[][] myGrid;
	private CoordinateTransformer myTransform;
	
	
//	@SuppressWarnings("unchecked")
	public FreeWorld(int numRows, int numCols){
//		myGrid = (List<GameObject>[][]) new LinkedList<?>[numRows][numCols];
		myGrid = new GameObject[numRows][numCols];
		myTransform = new CoordinateTransformer(numRows, numCols, 500, 500); //TODO fix window 1000 1000 measurements
		myPath = new PathFree(myTransform, myGrid);
	}

    @Override
    public void addObject (GameObject toSpawn, PointSimple pixelCoords) throws StructurePlacementException { 
		if(!isPlaceable(toSpawn.getGraphic().getNode(),pixelCoords)){
			throw new StructurePlacementException();
		}
		GridCell c = myTransform.transformWorldToGrid(pixelCoords);
		myGrid[c.getRow()][c.getCol()] = toSpawn;
        super.addObject(toSpawn);
        toSpawn.setPoint(myTransform.tranformGridToWorld(c));
        try {
			myPath.updatePath();
		} catch (NoPathExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public boolean isPlaceable (Node n, PointSimple pixelCoords) {
    	GridCell c = myTransform.transformWorldToGrid(pixelCoords);
    	if(c.getRow() < 0 || c.getRow() >= myGrid.length || c.getCol() < 0 || c.getCol() >= myGrid[0].length){ return false;}
    	return myGrid[c.getRow()][c.getCol()] == null;
    }

}