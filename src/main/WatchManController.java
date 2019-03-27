package main;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import util.Edge;
import util.Building;
import util.OutageEntry;
import util.OutagePane;

/**
 * Controller class for the GUI
 * @author Trent
 */
public class WatchManController {
	private WatchManModel model = new WatchManModel();
	private OutagePane outagePane = new OutagePane();
	
	@FXML private GridPane gridPaneMain;
	@FXML private ImageView spuMap;
	@FXML private ColumnConstraints gridPaneLeft;
	@FXML private Pane downNodePane;
	@FXML private Pane buildingPane;
	@FXML private Pane edgePane;
	@FXML private Label downNodeLabel;
	@FXML private VBox overflowPane;
	
	/**
	 * Called on program start
	 */
	public void initialize() {
		outagePane = new OutagePane();
		downNodePane.getChildren().add(outagePane);
		spuMap.toBack();
		
		// Dynamically resize the left half of the grid pane and map to fit the window
		// The right half will automatically take up the remaining width
		spuMap.fitHeightProperty().bind(gridPaneMain.heightProperty());
		spuMap.fitWidthProperty().bind(gridPaneMain.heightProperty());
		gridPaneLeft.prefWidthProperty().bind(gridPaneMain.heightProperty());
		gridPaneLeft.maxWidthProperty().bind(gridPaneMain.heightProperty());
		gridPaneLeft.minWidthProperty().bind(gridPaneMain.heightProperty());

		buildingPane.getChildren().addAll(model.downBuildings);

		// Dynamically relocate the nodes on window resizing
		spuMap.fitHeightProperty().addListener(e -> {
			model.calculateLocations(spuMap.getFitHeight());
			outagePane.updateLocations();
		});
		
		// Collapse the node list if it is too tall
		outagePane.heightProperty().addListener(e -> {
    		if(outagePane.getHeight() + downNodeLabel.getHeight() > spuMap.getFitHeight()) {
//    			overflowPane.getChildren().clear();
//    			outagePane.getOverfslowedNodes();
//    			overflowPane.getChildren().addAll(outagePane.getOverflowedNodes());
    		}
		});
	}
	
	private void clearPanes() {
		edgePane.getChildren().clear();
		outagePane.getChildren().clear();
		buildingPane.getChildren().clear();
	}
	
	/**
	 * This method defines what happens whenever the View calls an update
	 * @return	The event that happens on when the timer is triggered
	 */
	public EventHandler<ActionEvent> update() {
		return new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		    	model.updateList();
		    	ArrayList<Building> downBuildings = model.downBuildings;
		    	if(downBuildings == null) {
		    		return;
		    	}
		    	clearPanes();
	    		for(Building ng : downBuildings) {
	    			ng.refreshColor();
	    			OutageEntry entry = outagePane.addBuilding(ng);
	    			if(entry == null) {
	    				continue;
	    			}
	    			Edge edge = new Edge(entry);
	    			edgePane.getChildren().add(edge);
	    		}
	    		buildingPane.getChildren().addAll(model.downBuildings);
	    		model.calculateLocations(spuMap.getFitHeight());
		    }
		};
	}
}
