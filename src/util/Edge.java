package util;


import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Edge extends Line {
	private static final double STROKE_WIDTH = 2.5;

	public Edge(OutageEntry oe) {
		super(oe.building.getCenterX(), oe.building.getCenterY(), oe.getXLocation(), oe.getYLocation());
		Color color = Color.hsb(Math.random() * 255, 1, 0.8);
		this.setStroke(color);
		this.setStrokeWidth(STROKE_WIDTH);
		this.startXProperty().bind(oe.building.centerXProperty());
		this.startYProperty().bind(oe.building.centerYProperty());
		this.endXProperty().bind(oe.xLocationProperty());
		this.endYProperty().bind(oe.yLocationProperty());
		this.setVisible(false);
	}
}