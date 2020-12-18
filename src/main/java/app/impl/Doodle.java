package app.impl;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import util.Constants;

/*
 * This class is responsible for creating the actual doodle, setting up its initial location, adding it
 * to a Pane, and defining methods to return the velocity and location of the doodle, as well as to actually
 * set the velocity and location of the doodle
 */

public class Doodle {

	private Rectangle _doodle;
	private Pane _doodlePane;
	private double _velocity;
	private Platform _platforms;

	/*
	 * In the Constructor, I create a new rectangle to represent the doodle and pass
	 * in a Pane. I first set the height and width of the doodle. I then call
	 * methods to set the initial velocity of the doodle, to set the location of the
	 * doodle, and to add the doodle to a Pane
	 */

	public Doodle(Pane pane, Platform platform) {

		_doodlePane = pane;
		_platforms = platform;
		_doodle = new Rectangle();
		_velocity = Constants.DOODLE_INITIAL_VELOCITY;
		_doodle.setHeight(Constants.DOODLE_HEIGHT);
		_doodle.setWidth(Constants.DOODLE_WIDTH);
		_doodle.setFill(Color.ORANGE);
		this.setXLocation(Constants.DOODLE_INITIAL_X_LOCATION);
		this.setYLocation(Constants.DOODLE_INITIAL_Y_LOCATION);
		this.setUpPane();
	}

	/*
	 * This method is used to set the X coordinate location of the doodle
	 */

	public void setXLocation(double x) {
		_doodle.setX(x);
	}

	/*
	 * This method is used to set the Y coordinate location of the doodle
	 */

	public void setYLocation(double y) {
		_doodle.setY(y);
	}

	/*
	 * This method returns the x coordinate location of the doodle
	 */

	public double getXLocation() {

		return _doodle.getX();
	}

	/*
	 * This method returns the y coordinate location of the doodle
	 */

	public double getYLocation() {

		return _doodle.getY();
	}

	/*
	 * This method returns the value of the velocity variable
	 */

	public double getVelocity() {

		return _velocity;
	}

	/*
	 * This method sets the value of the velocity variable
	 */

	public void setVelocity(double x) {

		_velocity = x;
	}

	/*
	 * This method is used to add the doodle to the Pane that was passed in in the
	 * Doodle constructor
	 */

	public void setUpPane() {
		_doodlePane.getChildren().add(_doodle);
	}

	/*
	 * This method returns the Rectangle _doodle when called
	 */

	public Rectangle getDoodle() {
		return _doodle;
	}

	/*
	 * This helper method is used to update the Y velocity of the doodle. A For loop
	 * is used to test a condition for each value of the array. If the _doodle
	 * intersects any value of the array while its velocity is positive (downwards),
	 * the velocity is reset to the rebound velocity. If the doodle doesn't fit this
	 * condition, it returns a null value. If the For loop reaches the end of the
	 * ArrayList without the intersection test resulting in a positive boolean
	 * value, the velocity is set to the previous period velocity + Duration *
	 * Gravity
	 */

	public void updateYVelocity() {

		for (int i = 0; i < _platforms.getArrayList().size(); i++) {

			if (this.getVelocity() > 0 && this.getDoodle().intersects(_platforms.getArrayList().get(i).getX(),
					_platforms.getArrayList().get(i).getY(), Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGHT)) {

				this.setVelocity(Constants.REBOUND_VELOCITY);

			} else if (i == _platforms.getArrayList().size() - 1) {

				this.setVelocity(this.getVelocity() + Constants.DURATION * Constants.GRAVITY);
				break;

			} else {

			}
		}

	}
	/*
	 * This helper method is used to update the relative positioning of the doodle
	 * and the platforms. If the doodle is above the midpoint, the location of the
	 * doodle is reset to the midpoint of the graph and the platforms are cycled
	 * down by the current Doodle's Y location. If the doodle is below the midpoint
	 * but above the bottom of the window it's position is incremented according to
	 * it's current velocity. If the doodle is below the bottom of the pane a label
	 * pops onto the screen and a method is called to remove key functionality
	 */

	public void updatePosition() {

		// Scenario for when the Doodle falls below the Window
		if (this.getYLocation() > Constants.FRAME_BOTTOM_EDGE) {

			Label lbl = new Label("You Failed");
			_doodlePane.getChildren().add(lbl);
			_doodlePane.setOnKeyPressed(null);

			// Scenario for when the Doodle jumps above the midpoint

		} else if (this.getYLocation() < Constants.FRAME_MIDPOINT) {

			double midpointDifference = (Constants.FRAME_MIDPOINT - this.getYLocation());

			this.setYLocation(Constants.FRAME_MIDPOINT);

			for (int i = 0; i < _platforms.getArrayList().size(); i++) {

				_platforms.getArrayList().get(i).setY(_platforms.getArrayList().get(i).getY() + midpointDifference);

			}

			// Scenario for when the Doodle is above the bottom but below the midpoint

		} else {

			this.setYLocation(this.getYLocation() + this.getVelocity() * Constants.DURATION);

		}
	}

}
