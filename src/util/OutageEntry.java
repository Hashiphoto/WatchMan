package util;

import javafx.scene.layout.VBox;

/**
 * This displays a single building outage and all downed nodes
 * @author Trent
 */
public class OutageEntry extends VBox {
	public NodeGroup building;
	
	public OutageEntry(NodeGroup nodeGroup) {
		super();
		building = nodeGroup;
	}
	
	/**
	 * Iterates through the list of dead nodes and updates the 
	 * display accordingly
	 */
	public void refresh() {
		// TODO
	}
}
