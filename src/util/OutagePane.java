package util;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 * This pane contains the list of OutageEntries in a VBox
 * @author Trent
 */
public class OutagePane extends VBox {	
	/**
	 * Add a new entry for a building outage in the pane. If the entry already exists, this will
	 * rebuild that entry. Only call if the list of outages has changed
	 * @param building	The NodeGroup for which this entry will watch
	 * @return	The OutageEntry the NodeGroup is contained within
	 */
	public OutageEntry addBuilding(Building building) {
		if(building.deadNodes.isEmpty()) {
			return null;
		}
		OutageEntry outageEntry = null;
		outageEntry = new OutageEntry(building);
		this.getChildren().add(outageEntry);
		return outageEntry;
	}

	public void updateLocations() {
		for(Node node : this.getChildren()) {
			OutageEntry oe = (OutageEntry) node;
			oe.updateLocation();
		}
	}

	/**
	 * This checks if the containing pane has been overflowed and collects the labels
	 * from every building
	 * @return	The list of all dead nodes
	 */
//	public ObservableList<Node> getOverflowedNodes() {
//		ObservableList<Node> nodes = null;
//		for(OutageEntry oe : buildingEntries) {
//			if(nodes == null) {
//				nodes = oe.clearNodes();
//				continue;
//			}
//			nodes.addAll(oe.clearNodes());
//		}
//		return nodes;
//	}
}
