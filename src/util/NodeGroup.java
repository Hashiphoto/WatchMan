package util;

import java.util.HashSet;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * A NodeGroup represents a building or logical grouping of end points 
 * @author Trent
 */
public class NodeGroup extends Circle {
	public String name;
	public HashSet<Node> nodes;
	public HashSet<Node> deadNodes;
	
	private static final int DIAMETER = 4;
	private static final Paint disabledColor = Color.CRIMSON;
	private static final Paint activeColor = Color.LIGHTGREEN;
	private double xPercent;
	private double yPercent;
	
	public NodeGroup(String name, double xPercent, double yPercent) {
		super(xPercent, yPercent, DIAMETER, activeColor);
		this.xPercent = xPercent;
		this.yPercent = yPercent;
		this.name = name;
		nodes = new HashSet<Node>();
		deadNodes = new HashSet<Node>();
		this.toFront();
	}
	
	/**
	 * Iterate through all nodes and check if any have gone down or been restored
	 * @return	True if nodes have changed state. False if everything is the same
	 */
	public boolean scanForChanges() {
		boolean change = false;
		for(Node n : nodes) {
			n.checkConnection();
			if(!n.online) {
				boolean isNew = deadNodes.add(n);
				if(isNew) {
					change = true;
				}
			} else if(deadNodes.contains(n)) {
				deadNodes.remove(n);
				change = true;
			}
		}
		this.setFill(deadNodes.isEmpty() ? activeColor : disabledColor);
		return change;
	}

	public void calculateLocation(double fitHeight) {
		this.setCenterX(fitHeight * xPercent);
		this.setCenterY(fitHeight * yPercent);
	}
}
