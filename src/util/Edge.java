package util;

import javafx.scene.shape.Line;

public class Edge extends Line {
	private OutageEntry oe;

	public Edge(OutageEntry oe) {
		super();
		this.oe = oe;
		this.setStrokeWidth(4);
		this.startXProperty().bind(oe.building.centerXProperty());
		this.startYProperty().bind(oe.building.centerYProperty());
		this.endXProperty().bind(oe.xLocationProperty());
		this.endYProperty().bind(oe.yLocationProperty());
	}
}