package main;

import java.util.ArrayList;
import java.util.Collections;

import util.DataLoader;
import util.NodeGroup;

/**
 * This class reads in the data from the script and pings all of the nodes periodically
 * @author Trent
 *
 */
public class WatchManModel {
	public ArrayList<NodeGroup> groups;
	public ArrayList<NodeGroup> downBuildings;
	
	private int numDeadNodes;
	
	public WatchManModel() {
//		groups = fakeData();
		groups = DataLoader.loadNodeGroups();
		if(groups == null) {
			System.exit(1);
		}
//		groups = addFakeNodes(groups);
		downBuildings = new ArrayList<NodeGroup>();
	}
	
	/**
	 * Iterate through all node groups and check if any have gone down or been restored
	 * @return	True if nodes have changed state. False if everything is the same
	 */
	public int getNumChanges() {
		int changes = 0;
		numDeadNodes = 0;
		for(NodeGroup ng : groups) {
			if(ng.getNumChanges() == 0) {
				continue;
			}
			changes++;
			if(ng.deadNodes.isEmpty()) {
				downBuildings.remove(ng);
			} else {
				numDeadNodes += ng.deadNodes.size();
				sortedAdd(downBuildings, ng);
				Collections.sort(downBuildings);
			}
		}
		return changes;
	}
	
	public void sortedAdd(ArrayList<NodeGroup> group, NodeGroup ng) {
		int pos = Collections.binarySearch(group, ng);
		if(pos < 0) {
			group.add(-pos-1, ng);
		}
	}

	public void calculateLocations(double fitHeight) {
		for(NodeGroup ng : groups) {
			ng.calculateLocation(fitHeight);
		}
	}
	
	public int getNumDeadNodes() {
		return numDeadNodes;
	}
}
