package util;

import javafx.scene.shape.Line;

public class Edge extends Line {

	public Edge(OutageEntry oe) {
		super(oe.building.getCenterX(), oe.building.getCenterY(), oe.getXLocation(), oe.getYLocation());
		this.setStrokeWidth(4);
		this.startXProperty().bind(oe.building.centerXProperty());
		this.startYProperty().bind(oe.building.centerYProperty());
		this.endXProperty().bind(oe.xLocationProperty());
		this.endYProperty().bind(oe.yLocationProperty());
	}
}