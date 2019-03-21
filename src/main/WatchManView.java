package main;
	
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This application is designed to show all network connections on the SPU network and
 * display which ones are currently down in a panel to the right
 * @author Trent
 */
public class WatchManView extends Application {
	private static final double REFRESH_TIME = 5;
	private WatchManController controller;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("the_man_layout.fxml"));
			Parent root = fxmlLoader.load();
			controller = (WatchManController) fxmlLoader.getController();
			Scene scene = new Scene(root,1280,720);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			// Refresh the view on a cycle
			Timeline refreshConnection = new Timeline(new KeyFrame(Duration.seconds(REFRESH_TIME), controller.update()));
			refreshConnection.setCycleCount(Timeline.INDEFINITE);
			refreshConnection.play();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public WatchManController getController() {
		return controller;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
