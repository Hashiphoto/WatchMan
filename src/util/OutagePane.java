package util;

import java.util.ArrayList;

import javafx.scene.layout.VBox;

/**
 * This pane contains the list of OutageEntries in a VBox
 * @author Trent
 */
public class OutagePane extends VBox {
	public ArrayList<OutageEntry> buildingEntries;
	public ArrayList<NodeGroup> downBuildings;
	
	public OutagePane() {
		super();
		buildingEntries = new ArrayList<OutageEntry>();
		downBuildings = new ArrayList<NodeGroup>();
	}
	
	/**
	 * Add a new entry for a building outage in the pane. If the entry already exists, this will
	 * rebuild that entry. Only call if the list of outages has changed
	 * @param building	The NodeGroup for which this entry will watch
	 * @return	The OutageEntry the NodeGroup is contained within
	 */
	public OutageEntry addOutage(NodeGroup building) {
		// Find an existing entry or create a new one
		OutageEntry outageEntry = null;
		if(!downBuildings.contains(building)) {
			downBuildings.add(building);
			outageEntry = new OutageEntry(building);
		} else {
			for(OutageEntry oe : buildingEntries) {
				if(oe.building == building) {
					outageEntry = oe;
				}
			}
		}
		this.getChildren().add(outageEntry);
		outageEntry.refresh();
		return outageEntry;
	}
}
