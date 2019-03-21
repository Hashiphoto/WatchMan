package util;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * This displays a single building outage and all downed nodes
 * @author Trent
 */
public class OutageEntry extends VBox {
	public NodeGroup building;
	public Edge edge;
	
	private static final Font TITLE_FONT = new Font("Agency FB", 25.0);
	private static final Font NODE_FONT = new Font("Arial", 13.0);
	private static final int INDENT = 10;
	private static final int MARGIN = 10;
	private Text title;
    private DoubleProperty xLocation = new SimpleDoubleProperty();
    private DoubleProperty yLocation = new SimpleDoubleProperty();
	
	public OutageEntry(NodeGroup nodeGroup) {
		super();
		building = nodeGroup;
		title = new Text(building.name);
		title.setFont(TITLE_FONT);
		this.getChildren().add(title);
	}
	
	/**
	 * Iterates through the list of dead nodes and updates the display accordingly			
	 */
	public void refresh() {
		this.getChildren().clear();
		this.getChildren().add(title);
		for(Node n : building.deadNodes) {
			Text text = new Text(n.hostName);
			text.setFont(NODE_FONT);
			VBox.setMargin(text, new Insets(0, 0, 0, INDENT));
			this.getChildren().add(text);
		}
	}
	
	public void delete() {
		((Pane)edge.getParent()).getChildren().remove(edge);
		((OutagePane)this.getParent()).getChildren().remove(this);
	}
 
	public void updateLocation() {
		Bounds bounds = title.localToScene(title.getBoundsInLocal()); 
		xLocation.set(bounds.getMinX() - MARGIN);
    	yLocation.set((bounds.getMinY() + bounds.getMaxY()) / 2);
	}
	
	public double getXLocation() {
		return xLocation.get();
	}
	
	public double getYLocation() {
		return yLocation.get();
	}
	
    public DoubleProperty xLocationProperty() {
    	return xLocation;
    }
 
    public DoubleProperty yLocationProperty() {
    	return yLocation;
    }
}
