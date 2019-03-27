package fxmlComponents;

import java.util.HashSet;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * A NodeGroup represents a building or logical grouping of end points 
 * @author Trent
 */
public class Building extends Circle implements Comparable<Building>{
	public String name;
	public HashSet<Node> deadNodes;
	
	private static final int DIAMETER = 4;
	private static final Paint disabledColor = Color.CRIMSON;
	private static final Paint activeColor = Color.LIGHTGREEN;
	private double xPercent;
	private double yPercent;
	
	public Building(String name, double xPercent, double yPercent) {
		super(xPercent, yPercent, DIAMETER, activeColor);
		this.xPercent = xPercent;
		this.yPercent = yPercent;
		this.name = name;
		deadNodes = new HashSet<Node>();
	}
	
	public void refreshColor() {
		this.setFill(deadNodes.isEmpty() ? activeColor : disabledColor);
	}

	public void calculateLocation(double fitHeight) {
		this.setCenterX(fitHeight * xPercent);
		this.setCenterY(fitHeight * yPercent);
	}

	@Override
	public int compareTo(Building other) {
		double dist = this.yPercent - other.yPercent;
		if(dist > 0)
			return 1;
		if(dist < 0)
			return -1;
		return 0;
	}
}
