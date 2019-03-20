package main;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import util.NodeGroup;
import util.OutagePane;

/**
 * Controller class for the GUI
 * @author Trent
 */
public class WatchManController {
	private WatchManModel model = new WatchManModel();
	private OutagePane outagePane = new OutagePane();
	
	@FXML
	private GridPane gridPaneMain;
	
	@FXML
	private ImageView spuMap;
	
	@FXML
	private ColumnConstraints gridPaneLeft;
	
	@FXML
	private Pane downNodePane;
	
	/**
	 * Called on program start
	 */
	public void initialize() {
		// Dynamically resize the left half of the grid pane and map to fit the window
		// The right half will automatically take up the remaining width
		spuMap.fitHeightProperty().bind(gridPaneMain.heightProperty());
		spuMap.fitWidthProperty().bind(gridPaneMain.heightProperty());
		gridPaneLeft.prefWidthProperty().bind(gridPaneMain.heightProperty());
		gridPaneLeft.maxWidthProperty().bind(gridPaneMain.heightProperty());
		gridPaneLeft.minWidthProperty().bind(gridPaneMain.heightProperty());
		
		// Run this continuously
		model.scanForChanges();
		
		// Initialize the OutagePane
		outagePane = new OutagePane();
		downNodePane.getChildren().add(outagePane);
		System.out.println("There are " + model.groups.size() + " groups");
		for(NodeGroup ng : model.groups) {
			outagePane.addOutage(ng);
			System.out.println("Added " + ng.name);
		}
	}
	
	
}
