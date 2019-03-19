package util;

import java.util.ArrayList;

import javafx.scene.shape.Circle;

/**
 * A NodeGroup represents a building or logical grouping of end points 
 * @author Trent
 */
public class NodeGroup extends Circle {
	public static final int DIAMETER = 10;
	public ArrayList<Node> nodes;
	
	public NodeGroup(int x, int y) {
		nodes = new ArrayList<Node>();
		this.setCenterX(x);
		this.setCenterY(y);
		this.resize(DIAMETER, DIAMETER);
	}
}
