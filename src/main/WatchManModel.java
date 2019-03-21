package main;

import java.util.HashSet;

import util.DataLoader;
import util.Node;
import util.NodeGroup;

/**
 * This class reads in the data from the script and pings all of the nodes periodically
 * @author Trent
 *
 */
public class WatchManModel {
	public HashSet<NodeGroup> groups;
	public HashSet<NodeGroup> downBuildings;
	
	public WatchManModel() {
//		groups = fakeData();
		groups = DataLoader.loadNodeGroups();
		groups = addFakeNodes(groups);
		downBuildings = new HashSet<NodeGroup>();
	}
	
	/**
	 * Iterate through all node groups and check if any have gone down or been restored
	 * @return	True if nodes have changed state. False if everything is the same
	 */
	public boolean scanForChanges() {
		boolean change = false;
		for(NodeGroup ng : groups) {
			if(!ng.scanForChanges()) {
				continue;
			}
			change = true;
			if(ng.deadNodes.isEmpty()) {
				downBuildings.remove(ng);
			} else {
				downBuildings.add(ng);
			}
		}
		return change;
	}
	
	private HashSet<NodeGroup> addFakeNodes(HashSet<NodeGroup> buildingList) {
		for(NodeGroup ng : buildingList) {
			int numNodes = (int)(Math.random() * 10);
			for(int i = 0; i < numNodes; i++) {
				ng.nodes.add(new Node("123.123.123.123", ng.name + i));
			}
		}
		return buildingList;
	}

	public void calculateLocations(double fitHeight) {
		for(NodeGroup ng : groups) {
			ng.calculateLocation(fitHeight);
		}
	}
}
