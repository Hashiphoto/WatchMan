package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class DataLoader {
	// TODO: Fix this to be a local path
	private static final String DATA_FOLDER = "src\\data\\";
	private static final String BUILDING_LIST = "buildingListTemp.txt";
	
	public static HashSet<NodeGroup> loadNodeGroups() {
		File file = new File(DATA_FOLDER + BUILDING_LIST);
		if(!file.exists()) {
			System.err.println("Data Loader: Could not open file " + DATA_FOLDER + BUILDING_LIST);
			return null;
		}
		HashSet<NodeGroup> nodeGroup = new HashSet<NodeGroup>();
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		scanner.useDelimiter("[,\n]");
		while(scanner.hasNext()) {
			String name = scanner.next().trim();
			String xString = scanner.next().trim();
			String yString = scanner.next().trim();
			double x = Double.parseDouble(xString);
			double y = Double.parseDouble(yString);
			nodeGroup.add(new NodeGroup(name, x, y));
		}
		scanner.close();
		return nodeGroup;
	}
}
