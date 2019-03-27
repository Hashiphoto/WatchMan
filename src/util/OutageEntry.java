package util;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * This displays a single building outage and all downed nodes
 * @author Trent
 */
public class OutageEntry extends VBox {
	public Building building;
	
	private static final Font TITLE_FONT = new Font("Arial Bold", 15.0);
	private static final Font NODE_FONT = new Font("Arial", 13.0);
	private static final int INDENT = 10;
	private static final int MARGIN = 10;
    private static final double DELAY_TIME = 0.1;
	private Text title;
    private DoubleProperty xLocation = new SimpleDoubleProperty();
    private DoubleProperty yLocation = new SimpleDoubleProperty();
    private Timeline delayRefresh;
	
	public OutageEntry(Building nodeGroup) {
		super();
		building = nodeGroup;
		title = new Text(building.name);
		title.setFont(TITLE_FONT);
		this.getChildren().add(title);
		delayRefresh = new Timeline(new KeyFrame(Duration.seconds(DELAY_TIME), new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		    	setCoordinates();
		    }
		}));
		delayRefresh.setCycleCount(1);
		this.layoutYProperty().addListener(e -> {
			updateLocation();
		});
		this.needsLayoutProperty().addListener(e -> {
			updateLocation();
		});
		refresh();
	}
	
	/**
	 * Iterates through the list of dead nodes and updates the display accordingly			
	 */
	public void refresh() {
		for(Node n : building.deadNodes) {
			Text text = new Text(n.hostName);
			text.setFont(NODE_FONT);
			VBox.setMargin(text, new Insets(0, 0, 0, INDENT));
			this.getChildren().add(text);
		}
	}
	
	/**
	 * Use this when too many nodes are displayed. It will show only the title
	 * @param hide	True to hide the individual nodes
	 * @return 	The list of removed labels
	 */
	public ObservableList<javafx.scene.Node> clearNodes() {
//		int numNodes = this.getChildren().size();
//		title.setText(building.name + ": " + numNodes);
		title.setText(building.name);
		ObservableList<javafx.scene.Node> children = this.getChildren();
		this.getChildren().clear();
		this.getChildren().add(title);
		
		return children;	
	}
 
	public void updateLocation() {
		delayRefresh.play();
	}
	
	public void setCoordinates() {
		Bounds bounds = title.localToScene(title.getBoundsInLocal()); 
		xLocation.set(bounds.getMinX() - MARGIN);
    	yLocation.set((bounds.getMinY() + bounds.getMaxY()) / 2);
	}
	
	public double getXLocation() {
		return xLocation.get();
	}
	
	public double getYLocation() {
		return yLocation.get();
	}
	
    public DoubleProperty xLocationProperty() {
    	return xLocation;
    }
 
    public DoubleProperty yLocationProperty() {
    	return yLocation;
    }
}
