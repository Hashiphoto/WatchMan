package main;

import java.util.ArrayList;

import util.DataLoader;
import util.Building;

/**
 * This class reads in the data from the script and pings all of the nodes periodically
 * @author Trent
 *
 */
public class WatchManModel {
	public ArrayList<Building> downBuildings = null;
	
	public WatchManModel() {
		updateList();
	}
	
	public void updateList() {
		ArrayList<Building> temp = DataLoader.loadNodeGroups();
		if(temp != null) {
			downBuildings = temp;
		}
	}

	public void calculateLocations(double fitHeight) {
		for(Building ng : downBuildings) {
			ng.calculateLocation(fitHeight);
		}
	}
}
