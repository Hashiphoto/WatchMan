package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class DataLoader {
	// TODO: Fix this to be a local path
	private static final String DATA_FOLDER = "src\\data\\";
	private static final String BUILDING_LIST = "buildingList.txt";
	private static final String NODE_LIST = "checked_DHCP_data.csv";
	
	public static ArrayList<Building> loadNodeGroups() {
		// Open list of buildings
		Scanner scanner = openFile(DATA_FOLDER + BUILDING_LIST);
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
		scanner = openFile(DATA_FOLDER + NODE_LIST);
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
			String online = scanner.next().trim().toUpperCase();
			if(group == null || group.name != location) {
				group = findGroup(location, nodeGroup);
				if(group == null) {
					System.err.println("Could not find group for " + location);
					continue;
				}
			}
			if(online.equals("FALSE")) {
				group.deadNodes.add(new Node(ip, hostName, state));
			}
		}
		return nodeGroup;
	}
	
	private static Building findGroup(String building, ArrayList<Building> buildingList) {
		for(Building ng : buildingList) {
			if(ng.name.equals(building)) {
				return ng;
			}
		}
		return null;
	}
	
	private static Scanner openFile(String fileName) {
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
