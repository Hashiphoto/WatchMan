package util;

import java.util.HashSet;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 * This pane contains the list of OutageEntries in a VBox
 * @author Trent
 */
public class OutagePane extends VBox {
	public HashSet<OutageEntry> buildingEntries;
	public HashSet<NodeGroup> downBuildings;
	
	public OutagePane() {
		super();
		buildingEntries = new HashSet<OutageEntry>();
		downBuildings = new HashSet<NodeGroup>();
	}
	
	/**
	 * Add a new entry for a building outage in the pane. If the entry already exists, this will
	 * rebuild that entry. Only call if the list of outages has changed
	 * @param building	The NodeGroup for which this entry will watch
	 * @return	The OutageEntry the NodeGroup is contained within
	 */
	public OutageEntry updateGroup(NodeGroup building) {
		OutageEntry outageEntry = null;
		// Create a new entry for the building
		if(!downBuildings.contains(building)) {
			downBuildings.add(building);
			outageEntry = new OutageEntry(building);
			this.getChildren().add(outageEntry);
			buildingEntries.add(outageEntry);
		} 
		// Find the existing entry
		else {
			for(OutageEntry oe : buildingEntries) {
				if(oe.building == building) {
					outageEntry = oe;
					break;
				}
			}
		}
		outageEntry.refresh();
		return outageEntry;
	}

	public void updateLocations() {
		for(OutageEntry oe : buildingEntries) {
			oe.updateLocation();
		}
	}

	public void purgeOnlineGroups() {
		for(Node node : this.getChildren()) {
			OutageEntry oe = (OutageEntry)node;
			if(oe.building.deadNodes.size() == 0) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						downBuildings.remove(oe.building);
						buildingEntries.remove(oe);
						oe.delete();
					}
				});
			}
		}
	}

	public void collapseGroups() {
		for(OutageEntry oe : buildingEntries) {
			oe.clearNodes();
		}
	}
}
