package engine.pathgraphsearch;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GraphNode {
	private List<Edge> myEdges;
	private GraphNodeID ID;
	private static GraphNodeID IDCounter;
	private boolean visited;
	
	public GraphNode(){
		ID = IDCounter.getClone();
		IDCounter.increment();
		visited = false;
	}
	
	public void addEdge(Edge e){
		myEdges.add(e);
	}
	
	public GraphNodeID getID(){
		return ID;
	}
	
	public void setVisited(boolean b){
		visited = b;
	}
	
	public boolean visited(){
		return visited;
	}
	
	public List<Edge> getEdges(){
		return Collections.unmodifiableList(myEdges);
	}
	
	public List<GraphNode> getNeighbors(){
		return Collections.unmodifiableList(myEdges.stream().map(e -> e.getNeighbor()).collect(Collectors.toList()));
	}
}
