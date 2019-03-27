package main;

import java.util.ArrayList;

import fxmlComponents.Building;
import util.DataLoader;

/**
 * This class reads in the data from the script and pings all of the nodes periodically
 * @author Trent
 *
 */
public class WatchManModel {
	public ArrayList<Building> downBuildings = null;
	private DataLoader dataLoader = new DataLoader();
	
	public WatchManModel() {
		updateList();
	}
	
	public void updateList() {
		ArrayList<Building> temp = dataLoader.loadNodeGroups();
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
