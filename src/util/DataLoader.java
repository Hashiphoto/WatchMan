package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import fxmlComponents.Building;
import fxmlComponents.Node;

public class DataLoader {
	private PropertyReader prop = new PropertyReader();
	
	public ArrayList<Building> loadNodeGroups() {
		// Open list of buildings
		Scanner scanner = openFile(prop.getString("buildingPath"));
		if(scanner == null) {
			return null;
		}
		ArrayList<Building> nodeGroup = new ArrayList<Building>();
		scanner.useDelimiter("[,\n]");
		while(scanner.hasNext()) {
			String name = scanner.next().trim();
			String xString = scanner.next().trim();
			String yString = scanner.next().trim();
			double x = Double.parseDouble(xString);
			double y = Double.parseDouble(yString);
			nodeGroup.add(new Building(name, x, y));
		}
		scanner.close();
		Collections.sort(nodeGroup);
		
		// Add all nodes to the buildings
		scanner = openFile(prop.getString("nodeListPath"));
		if(scanner == null) {
			return null;
		}
		// Skip the header
		scanner.nextLine();
		scanner.useDelimiter("[,\n]");
		Building group = null;
		while(scanner.hasNext()) {
			String ip = scanner.next().trim();
			String state = scanner.next().trim();
			String hostName = scanner.next().trim();
			String location = scanner.next().trim();
			String online = scanner.next().trim();
			if(group == null || group.name != location) {
				group = findGroup(location, nodeGroup);
				if(group == null) {
					System.err.println("Could not find group for " + location);
					continue;
				}
			}
			if(online.toUpperCase().equals("FALSE")) {
				group.deadNodes.add(new Node(ip, hostName, state));
			}
		}
		scanner.close();
		return nodeGroup;
	}
	
	private Building findGroup(String building, ArrayList<Building> buildingList) {
		for(Building ng : buildingList) {
			if(ng.name.equals(building)) {
				return ng;
			}
		}
		return null;
	}
	
	private Scanner openFile(String fileName) {
		File file = new File(fileName);
		if(!file.exists()) {
			System.err.println("Data Loader: Could not open file " + fileName);
			return null;
		}
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.err.println("Data loader: Could not create scanner for " + fileName);
			return null;
		}
		return scanner;
	}
}
