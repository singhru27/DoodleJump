package app.impl;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.Constants;

/*
 * In the constructor, I create a top level Pane and pass the root from the
 * PaneOrganizer class into the new Scene. I then title the stage "Doodle Jump"
 * and show the stage
 */

public class App extends Application {

	@Override
	public void start(Stage stage) {

		PaneOrganizer paneorganizer = new PaneOrganizer();
		Scene scene = new Scene(paneorganizer.getRoot(), Constants.PANE_SIZE, Constants.PANE_SIZE);
		stage.setScene(scene);
		stage.setTitle("Doodle Jump");
		stage.show();
		// Instantiate top-level object, set up the scene, and show the stage here.
	}

	public static void main(String[] argv) {
		// launch is a static method inherited from Application.
		launch(argv);
	}
}
