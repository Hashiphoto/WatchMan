package util;

import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class OutageEntry extends VBox {
	public Label heading;
	public ArrayList<Label> nodes;
	
	public OutageEntry(String title) {
		heading = new Label(title);
		nodes = new ArrayList<Label>();
	}
	
	public void add() {
		
	}
}
