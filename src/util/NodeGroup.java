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
	public ArrayList<Node> nodes;
	public HashSet<Node> deadNodes;
	
	public NodeGroup(int x, int y) {
		nodes = new ArrayList<Node>();
		deadNodes = new HashSet<Node>();
		this.setCenterX(x);
		this.setCenterY(y);
		this.resize(DIAMETER, DIAMETER);
	}
	
	public void Scan() {
		for(Node n : nodes) {
			if(!n.online) {
				deadNodes.add(n);
				continue;
			}
			if(deadNodes.contains(n)) {
				deadNodes.remove(n);
			}
		}
	}
}
