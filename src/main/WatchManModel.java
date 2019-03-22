package main;

import java.util.ArrayList;
import java.util.Collections;

import util.DataLoader;
import util.Node;
import util.NodeGroup;

/**
 * This class reads in the data from the script and pings all of the nodes periodically
 * @author Trent
 *
 */
public class WatchManModel {
	public ArrayList<NodeGroup> groups;
	public ArrayList<NodeGroup> downBuildings;
	
	public WatchManModel() {
//		groups = fakeData();
		groups = DataLoader.loadNodeGroups();
		groups = addFakeNodes(groups);
		downBuildings = new ArrayList<NodeGroup>();
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
				sortedAdd(downBuildings, ng);
				Collections.sort(downBuildings);
			}
		}
		return change;
	}
	
	public void sortedAdd(ArrayList<NodeGroup> group, NodeGroup ng) {
		int pos = Collections.binarySearch(group, ng);
		if(pos < 0) {
			group.add(-pos-1, ng);
		}
	}
	
	private ArrayList<NodeGroup> addFakeNodes(ArrayList<NodeGroup> buildingList) {
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
