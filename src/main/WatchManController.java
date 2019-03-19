package main;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class WatchManController {
	
	@FXML
	private GridPane gridPaneMain;
	
	@FXML
	private ImageView spuMap;
	
	@FXML
	private ColumnConstraints gridPaneLeft;
	
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
	}
}
