package app.impl;

import javafx.event.ActionEvent;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import util.Constants;

/*
 * This class contains most of the logic for the actual mechanics of the game. It creates a new instance of the 
 * Doodle, and adds it to the _root BorderPane. It contains the logic for setting up the quit button, for 
 * animating the Doodle to move up the screen, for all the physics simulation, and for responses to key input
 */

public class Doodler {

	private Doodle _doodle;
	private BorderPane _root;
	private Platform _platforms;

	/*
	 * In the constructor, I pass in a BorderPane as a parameter. I also add the
	 * Doodle to the BorderPane. I then call methods to set up the buttons, to set
	 * up the Timeline animation system. Then, I call methods to have the central
	 * pane respond to key inputs
	 */

	public Doodler(BorderPane borderPane) {

		_root = borderPane;
		Pane _doodlePane = new Pane();
		_doodlePane.setPrefHeight(Constants.PANE_SIZE);
		_doodlePane.setPrefWidth(Constants.PANE_SIZE);
		
		_platforms = new Platform(_doodlePane);
		_doodle = new Doodle(_doodlePane, _platforms);
		_root.setPrefSize(Constants.PANE_SIZE, Constants.PANE_SIZE);
		_root.setCenter(_doodlePane);
		
		this.setUpButton();
		_doodlePane.setOnKeyPressed(new MoveHandler());
		_doodlePane.requestFocus();
		_doodlePane.setFocusTraversable(true);
		this.setUpTimeline();
		

	}

	/*
	 * This method sets up the quit button. It adds the button to a VBOX on the
	 * right side of the window, and creates a new QuitHandler when the button is
	 * pressed
	 */

	public void setUpButton() {

		VBox _userInputBox = new VBox();
		Button bt1 = new Button("Quit");
		bt1.setOnAction(new QuitHandler());
		bt1.setFocusTraversable(false);
		_userInputBox.getChildren().add(bt1);
		_root.setRight(_userInputBox);

	}

	/*
	 * This method is used to create the Timeline by creating a KeyFrame, setting
	 * its Duration to the constant specified in the Constants class, and setting
	 * the Animation cycle count to indefinite
	 */

	public void setUpTimeline() {

		KeyFrame kf = new KeyFrame(Duration.seconds(Constants.DURATION), new TimeHandler());
		Timeline timeline = new Timeline(kf);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

	/*
	 * This private inner class TimeHandler contains the logic for the animation of
	 * the Doodle It sets the velocity of the doodle, sets the position of the
	 * doodle, updates the scrolling of the platforms and calls the update platforms
	 * method to delete and add new platforms
	 */

	private class TimeHandler implements EventHandler<ActionEvent> {

		public void handle(ActionEvent event) {

			_doodle.updateYVelocity();
			_doodle.updatePosition();
			_platforms.updatePlatforms();

		}

	}

	/*
	 * This private class QuitHandler contains the logic for quitting the
	 * application when the button is pressed
	 */

	private class QuitHandler implements EventHandler<ActionEvent> {

		public void handle(ActionEvent event) {

			System.exit(0);
		}
	}

	/*
	 * This private inner class contains the logic for moving the doodle. First, an
	 * If statement is used to determine whether the Doodle is to the left of the
	 * leftmost border of the window. If so, it only can move right. If the doodle
	 * is to the right of the rightmost border of the window, it can only move left.
	 * If the doodle doesn't fill any of these cases, it can move in any direction
	 */

	private class MoveHandler implements EventHandler<KeyEvent> {

		public void handle(KeyEvent e) {
			KeyCode keyPressed = e.getCode();

			// case for when the doodle is on the leftmost edge of the border

			if (_doodle.getXLocation() < Constants.FRAME_LEFT_EDGE) {
				switch (keyPressed) {

				case RIGHT:
					_doodle.setXLocation(_doodle.getXLocation() + Constants.DOODLE_MOVE);
					break;

				case LEFT:
					break;

				default:
					break;
				}

				// Case for when the doodle is on the rightmost edge of the window

			} else if (_doodle.getXLocation() > Constants.FRAME_RIGHT_EDGE) {

				switch (keyPressed) {

				case RIGHT:
					break;

				case LEFT:
					_doodle.setXLocation(_doodle.getXLocation() - Constants.DOODLE_MOVE);
					break;

				default:
					break;
				}

				// Case for when the doodle is within the interior of the window

			} else {

				switch (keyPressed) {

				case RIGHT:
					_doodle.setXLocation(_doodle.getXLocation() + Constants.DOODLE_MOVE);
					break;

				case LEFT:
					_doodle.setXLocation(_doodle.getXLocation() - Constants.DOODLE_MOVE);
					break;

				default:
					break;
				}

			}

			e.consume();
		}

	}

}
