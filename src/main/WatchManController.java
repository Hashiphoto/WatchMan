package main;

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
import util.NodeGroup;
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
	@FXML private Pane viewPane;
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
		
		addVisibleNodes();

		// Dynamically relocate the nodes on window resizing
		spuMap.fitHeightProperty().addListener(e -> {
			model.calculateLocations(spuMap.getFitHeight());
			outagePane.updateLocations();
		});
		
		// Collapse the node list if it is too tall
		outagePane.heightProperty().addListener(e -> {
    		if(outagePane.getHeight() + downNodeLabel.getHeight() > spuMap.getFitHeight()) {
    			overflowPane.getChildren().clear();
    			overflowPane.getChildren().addAll(outagePane.getOverflowedNodes());
    		}
		});
	}
	
	private void addVisibleNodes() {
		if(model == null) {
			return;
		}
		viewPane.getChildren().addAll(model.groups);
	}
	
	private void addEdge(OutageEntry entry) {
		Edge edge = new Edge(entry);
		edgePane.getChildren().add(edge);
		entry.edge = edge;
	}
	
	/**
	 * This method defines what happens whenever the View calls an update
	 * @return
	 */
	public EventHandler<ActionEvent> update()
	{
		return new EventHandler<ActionEvent>()
		{
		    @Override
		    public void handle(ActionEvent event) 
		    {
		    	// If there are changes, rebuild the list of down nodes
		    	int numChanges = model.getNumChanges();
		    	if(numChanges > 0) {
		    		for(NodeGroup ng : model.downBuildings) {
		    			OutageEntry entry = outagePane.updateGroup(ng);
		    			if(entry.edge == null) {
			    			addEdge(entry);
		    			}
		    		}
		    		outagePane.purgeOnlineGroups();
		    	}
		    }
		};
	}
}
