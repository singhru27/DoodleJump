package app.impl;

import javafx.scene.layout.BorderPane;

public class PaneOrganizer {

	// Creating a new _root BorderPane

	private BorderPane _root;

	/*
	 * In the constructor I create a new instance of the _root BorderPane, and a new
	 * instance of the Doodler class
	 */
	public PaneOrganizer() {

		_root = new BorderPane();
		Doodler doodler = new Doodler(_root);

	}

	public BorderPane getRoot() {

		return _root;
	}

}
