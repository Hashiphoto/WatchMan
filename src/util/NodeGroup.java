package util;

import java.util.ArrayList;
import java.util.HashSet;

import javafx.scene.shape.Circle;

/**
 * A NodeGroup represents a building or logical grouping of end points 
 * @author Trent
 */
public class NodeGroup extends Circle {
	public static final int DIAMETER = 10;
	public String name;
	public ArrayList<Node> nodes;
	public HashSet<Node> deadNodes;
	
	public NodeGroup(String name, int x, int y) {
		this.name = name;
		nodes = new ArrayList<Node>();
		deadNodes = new HashSet<Node>();
		this.setCenterX(x);
		this.setCenterY(y);
		this.resize(DIAMETER, DIAMETER);
	}
	
	/**
	 * Iterate through all nodes and check if any have gone down or been restored
	 * @return	True if nodes have changed state. False if everything is the same
	 */
	public boolean scanForChanges() {
		boolean change = false;
		System.out.println(name + " is scanning");
		for(Node n : nodes) {
			if(!n.online) {
				System.out.println("Added node to dead");
				boolean isNew = deadNodes.add(n);
				if(isNew) {
					change = true;
				}
				continue;
			}
			if(deadNodes.contains(n)) {
				deadNodes.remove(n);
				change = true;
			}
		}
		return change;
	}
}
