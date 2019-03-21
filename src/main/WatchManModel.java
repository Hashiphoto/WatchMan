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
	
	private HashSet<NodeGroup> fakeData() {
		HashSet<NodeGroup> group = new HashSet<NodeGroup>();
		NodeGroup omh = new NodeGroup("OMH", 0.1, 0.1);
		NodeGroup bar = new NodeGroup("BAR", 0.2, 0.2);
		NodeGroup foo = new NodeGroup("FOO", 0.3, 0.4);
		omh.nodes.add(new Node("192.168.2.3", "OMH123-00"));
		omh.nodes.add(new Node("192.168.2.5", "OMH123-01"));
		omh.nodes.add(new Node("192.168.2.8", "OMH123-02"));
		bar.nodes.add(new Node("123.456.1.1", "BAR01-01"));
		bar.nodes.add(new Node("123.456.1.2", "BAR02-02"));
		foo.nodes.add(new Node("999.999.1.0", "FOO300-00"));
		group.add(omh);
		group.add(bar);
		group.add(foo);
		return group;
	}

	public void calculateLocations(double fitHeight) {
		for(NodeGroup ng : groups) {
			ng.calculateLocation(fitHeight);
		}
	}
}
