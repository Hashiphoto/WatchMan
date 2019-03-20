package util;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * This displays a single building outage and all downed nodes
 * @author Trent
 */
public class OutageEntry extends VBox {
	public NodeGroup building;
	
	private Label title;
	
	public OutageEntry(NodeGroup nodeGroup) {
		super();
		building = nodeGroup;
		title = new Label(building.name);
		title.setFont(new Font("Agency FB", 25.0));
		this.getChildren().add(title);
	}
	
	/**
	 * Iterates through the list of dead nodes and updates the 
	 * display accordingly
	 */
	public void refresh() {
		for(Node n : building.deadNodes) {
			this.getChildren().add(new Label(n.hostName));
		}
	}
}
