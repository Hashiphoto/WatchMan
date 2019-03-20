package main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
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
	
	@FXML
	private GridPane gridPaneMain;
	
	@FXML
	private ImageView spuMap;
	
	@FXML
	private ColumnConstraints gridPaneLeft;
	
	@FXML
	private Pane downNodePane;
	
	@FXML
	private Pane viewPane;
	
	@FXML
	private Pane edgePane;
	
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
			model.calculateNodeLocation(spuMap.getFitHeight());
		});
	}
	
	private void addVisibleNodes() {
		if(model == null) {
			return;
		}
		viewPane.getChildren().addAll(model.groups);
	}
	
	private void drawEdge(NodeGroup ng, OutageEntry entry) {
		Line line = new Line(ng.getCenterX(), ng.getCenterY(), entry.getX(), entry.getY());
		edgePane.getChildren().add(line);
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
		    	if(model.scanForChanges()) {
		    		for(NodeGroup ng : model.downBuildings) {
		    			OutageEntry entry = outagePane.addOutage(ng);
		    			drawEdge(ng, entry);
		    		}
		    	}
		    }
		};
	}
}
