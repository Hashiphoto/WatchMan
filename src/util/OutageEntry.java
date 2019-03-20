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
	
	private static final Font TITLE_FONT = new Font("Agency FB", 25.0);
	private static final Font NODE_FONT = new Font("Arial", 13.0);
	private Label title;
	
	public OutageEntry(NodeGroup nodeGroup) {
		super();
		building = nodeGroup;
		title = new Label(building.name);
		title.setFont(TITLE_FONT);
		this.getChildren().add(title);
	}
	
	/**
	 * Iterates through the list of dead nodes and updates the 
	 * display accordingly
	 */
	public void refresh() {
		for(Node n : building.deadNodes) {
			Label label = new Label(n.hostName);
			label.setFont(NODE_FONT);
			this.getChildren().add(label);
		}
	}

	public double getX() {
		return title.localToScene(title.getBoundsInLocal()).getMinX();
	}
	
	public double getY() {
		return this.localToScene(this.getBoundsInLocal()).getMinY() + title.getLayoutY() + title.getTranslateY() + this.getLayoutY() + this.getTranslateY();
		//		return title.localToScene(title.getBoundsInLocal()).getMinY();
//		return title.localToParent(title.getBoundsInParent()).getMinY();
//		return title.localToScene(title.getBoundsInLocal()).getMinY();
	}
}
