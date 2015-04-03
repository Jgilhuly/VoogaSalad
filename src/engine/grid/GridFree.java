package engine.grid;

import java.util.LinkedList;
import java.util.List;
import engine.gameobject.GameObject;
import engine.gameobject.weapon.Weapon;
import engine.interactions.ConcreteInteractionEngine;
import engine.interactions.InteractionEngine;

public class GridFree extends GridAbstract {
	private List<GameObject>[][] myGrid;
	private CoordinateTransformer myCoordTrans;
	
	@SuppressWarnings("unchecked")
	public GridFree(int numRows, int numCols){
		myGrid = (List<GameObject>[][]) new LinkedList<?>[numRows][numCols];
	}

	@Override
	public void removeObject(GameObject o){
		super.removeObject(o);
		for(List<GameObject>[] row : myGrid){
			for(List<GameObject> tileList : row){
				if(tileList.contains(o)){
					tileList.remove(o);
				}
			}
		}
	}

	@Override
	public void addObject(GameObject o){
		super.addObject(o);
		GridCell c = myCoordTrans.transformWorldToGrid(o.getPoint());
		myGrid[c.getRow()][c.getCol()].add(o);
	}


	@Override
	public void detectRange(){
		super.detectRange();
	}
}
