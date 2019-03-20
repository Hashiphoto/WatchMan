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
//	public HashSet<NodeGroup> downBuildings;
	
	public WatchManModel() {
		System.out.println("Model constructor");
		groups = fakeData();
//		downBuildings = new HashSet<NodeGroup>();
	}
	
	/**
	 * Iterate through all node groups and check if any have gone down or been restored
	 * @return	True if nodes have changed state. False if everything is the same
	 */
	public boolean scanForChanges() {
		boolean change = false;
		for(NodeGroup ng : groups) {
			if(ng.scanForChanges()) {
//				downBuildings.add(ng);
				change = true;
			} else {
//				downBuildings.remove(ng);
			}
		}
		return change;
	}
	
	private ArrayList<NodeGroup> fakeData() {
		ArrayList<NodeGroup> group = new ArrayList<NodeGroup>();
		NodeGroup omh = new NodeGroup("OMH", 10, 10);
		NodeGroup bar = new NodeGroup("BAR", 20, 10);
		NodeGroup foo = new NodeGroup("FOO", 30, 10);
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
}
