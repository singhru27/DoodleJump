package app.impl;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import util.Constants;

/*
 * In this class, I create all the platforms that are in the Application. First, I create an Arraylist
 * to hold all the platforms. I then set up the initial platform right under the doodle, and define methods
 * to add rectangles up through the window and to remove platforms below the viewing screen. 
 */

public class Platform {

	private ArrayList<Rectangle> _platforms;
	private Pane _doodlePane;
	private Rectangle _rect;

	/*
	 * The Constructor of the Platform is modified to take in a Pane as a paramter.
	 * Within the constructor, I create the new arrayList, set the initial size and
	 * location for the first rectangle, and call a method to set up the initial
	 * rectangle.
	 */

	public Platform(Pane pane) {

		_doodlePane = pane;
		_platforms = new ArrayList<Rectangle>();
		_rect = new Rectangle();
		this.setUpRectangle();
		this.addInitialRectangle();

	}

	/*
	 * This method sets the initial coordinates and size of the first rectangle in
	 * the game that is right below the doodle
	 */

	public void setUpRectangle() {
		_rect.setX(Constants.DOODLE_INITIAL_X_LOCATION + Constants.INITIAL_PLATFORM_X_OFFSET);
		_rect.setY(Constants.DOODLE_INITIAL_Y_LOCATION + Constants.INITIAL_PLATFORM_Y_OFFSET);
		_rect.setWidth(Constants.PLATFORM_WIDTH);
		_rect.setHeight(Constants.PLATFORM_HEIGHT);
		_rect.setFill(Color.BLACK);
	}

	/*
	 * This method adds the initial rectangle to the Arraylist, and adds the
	 * Arraylist to the Pane
	 */

	public void addInitialRectangle() {

		_platforms.add(_rect);
		_doodlePane.getChildren().addAll(_platforms);
	}

	/*
	 * This method is used to return the _platforms ArrayList
	 */

	public ArrayList<Rectangle> getArrayList() {

		return _platforms;
	}

	/*
	 * This method is used to randomly generate platforms above the top most
	 * platform while the topmost platform is "Y Constraint" below the top of the
	 * window. This ensures that no platforms are created above the viewing window
	 * of the pane. It creates the new platform and adds it to the ArrayList when
	 * the condition holds true. The method also utilizes a For loop, and cycles
	 * through the entire ArrayList when called, deleting any rectangles that are
	 * below the bottom of the pane Whenever a platform is below the bottom of the
	 * window, it is deleted
	 */

	public void updatePlatforms() {

		// Creating a new platform, and setting it using a semi-random constraint
		// Reassigning the newly created platform to be the topmost platform

		while (_rect.getY() > Constants.FRAME_TOP_EDGE + Constants.MAXIMUM_PLATFORM_Y_CONSTRAINT) {

			Rectangle secondRect = new Rectangle();

			/*
			 * This method sets the y coordinate of the new rectangle in reference to the
			 * coordinate of the old rectangle.
			 */

			secondRect.setY(_rect.getY() - this.findNewY());

			/*
			 * This method sets the x coordinate of each new platform in reference to the x
			 * coordinate location of the topmost rectangle. It sets the x coordinate
			 * between the specified minimum and maximum distances away from the topmost
			 * previous platform
			 */

			secondRect.setX(this.findNewX());

			secondRect.setWidth(Constants.PLATFORM_WIDTH);
			secondRect.setHeight(Constants.PLATFORM_HEIGHT);
			secondRect.setFill(Color.BLACK);

			_platforms.add(secondRect);
			_doodlePane.getChildren().add(_platforms.get(_platforms.size() - 1));
			_rect = secondRect;

		}

		/*
		 * This loop tests each element in the array to see if platforms are still
		 * within the viewing window. If it isn't, it removes the element from the array
		 * and removes the element from the pane
		 */

		for (int i = 0; i < _platforms.size(); i++) {

			if (_platforms.get(i).getY() > Constants.FRAME_BOTTOM_EDGE) {
				_doodlePane.getChildren().remove(_platforms.get(i));
				_platforms.remove(i);
				i = 0;
				continue;
			} else {

			}

		}

	}

	/*
	 * This method returns the random constrained variable which represents the
	 * translated Y coordinate of the next platform in reference to the topmost
	 * platform
	 */

	public double findNewY() {
		return (Math.random() * (Constants.MAXIMUM_PLATFORM_Y_CONSTRAINT - Constants.MINIMUM_PLATFORM_Y_CONSTRAINT + 1)
				+ Constants.MINIMUM_PLATFORM_Y_CONSTRAINT);
	}

	/*
	 * This method returns the random constrained variable which represents the
	 * translated X coordinate of the next platform in reference to the topmost
	 * platform. If the last platform is on the right half of the pane, it returns
	 * the minimum value of either the randomly generated X translation or the
	 * distance between the platform and the the right side of the pane. If the last
	 * platform is on the left half of the pane, it returns the maximum value of
	 * either the randomly generated X translation or the distance between the
	 * platform and the left side of the pane
	 */

	public double findNewX() {
		if (_rect.getX() > Constants.FRAME_MIDPOINT) {

			return Math.min((Constants.FRAME_RIGHT_EDGE - Constants.PLATFORM_WIDTH) - _rect.getX(),
					Math.random()
							* (Constants.MAXIMUM_PLATFORM_X_CONSTRAINT - Constants.MINIMUM_PLATFORM_X_CONSTRAINT + 1)
							+ Constants.MINIMUM_PLATFORM_X_CONSTRAINT);

		} else {
			return Math.max(_rect.getX() - Constants.FRAME_LEFT_EDGE,
					Math.random()
							* (Constants.MAXIMUM_PLATFORM_X_CONSTRAINT - Constants.MINIMUM_PLATFORM_X_CONSTRAINT + 1)
							+ Constants.MINIMUM_PLATFORM_X_CONSTRAINT);
		}
	}
}
