package main;

import java.util.ArrayList;
import java.util.HashSet;

import util.Node;
import util.NodeGroup;

/**
 * This class reads in the data from the script and pings all of the nodes periodically
 * @author Trent
 *
 */
public class WatchManModel {
	public ArrayList<NodeGroup> groups;
	public HashSet<NodeGroup> downBuildings;
	
	public WatchManModel() {
		groups = fakeData();
		downBuildings = new HashSet<NodeGroup>();
	}
	
	/**
	 * Iterate through all node groups and check if any have gone down or been restored
	 * @return	True if nodes have changed state. False if everything is the same
	 */
	public boolean scanForChanges() {
		boolean change = false;
		for(NodeGroup ng : groups) {
			if(ng.scanForChanges()) {
				downBuildings.add(ng);
				change = true;
			} else if(downBuildings.contains(ng)) {
				downBuildings.remove(ng);
				change = true;
			}
		}
		return change;
	}
	
	private ArrayList<NodeGroup> fakeData() {
		ArrayList<NodeGroup> group = new ArrayList<NodeGroup>();
		NodeGroup omh = new NodeGroup("OMH", 0.1, 0.1);
		NodeGroup bar = new NodeGroup("BAR", 0.2, 0.2);
		NodeGroup foo = new NodeGroup("FOO", 0.3, 0.4);
		omh.nodes.add(new Node("192.168.2.3", "OMH123"));
		omh.nodes.add(new Node("192.168.2.5", "OMH123-01"));
		omh.nodes.add(new Node("192.168.2.8", "OMH123-02"));
		bar.nodes.add(new Node("123.456.1.1", "BAR01"));
		bar.nodes.add(new Node("123.456.1.2", "BAR02"));
		foo.nodes.add(new Node("999.999.1.0", "ASDF"));
		group.add(omh);
		group.add(bar);
		group.add(foo);
		return group;
	}

	public void calculateNodeLocation(double fitHeight) {
		for(NodeGroup ng : groups) {
			ng.calculateLocation(fitHeight);
		}
	}
}
