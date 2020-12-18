package util;

/**
 * This is your Constants class. It defines some constants you will need in
 * DoodleJump, using the default values from the demo--you shouldn't need to
 * change any of these values unless you want to experiment. Feel free to add
 * more constants to this class!
 *
 * A NOTE ON THE GRAVITY CONSTANT: Because our y-position is in pixels rather
 * than meters, we'll need our gravity to be in units of pixels/sec^2 rather
 * than the usual 9.8m/sec^2. There's not an exact conversion from pixels to
 * meters since different monitors have varying numbers of pixels per inch, but
 * assuming a fairly standard 72 pixels per inch, that means that one meter is
 * roughly 2800 pixels. However, a gravity of 2800 pixels/sec2 might feel a bit
 * fast. We suggest you use a gravity of about 1000 pixels/sec2. Feel free to
 * change this value, but make sure your game is playable with the value you
 * choose.
 */

/*
 * In this class, I've added additional constants for the Doodle's initial x and
 * y location, as well as the incrementation constant for moving the Doodle left
 * and right when the arrow keys are pressed. I also have added constants
 * representing the left and right edge of the Window, and constants to set the
 * initial location of the first Platform. The constants class also contains a
 * constant used to set the initial velocity of the doodle to 0, and a constant
 * used to initialize the first platform
 */

public class Constants {

	public static final int GRAVITY = 1000; // acceleration constant (UNITS: pixels/s^2)
	public static final int REBOUND_VELOCITY = -900; // initial jump velocity (UNITS: pixels/s)
	public static final double DURATION = .008; // KeyFrame duration (UNITS: s)

	public static final int PLATFORM_WIDTH = 40; // (UNITS: pixels)
	public static final int PLATFORM_HEIGHT = 10; // (UNITS: pixels)
	public static final int PANE_SIZE = 600;
	public static final int DOODLE_WIDTH = 20; // (UNITS: pixels)
	public static final int DOODLE_HEIGHT = 40; // (UNITS: pixels)
	public static final int DOODLE_INITIAL_X_LOCATION = 300;
	public static final int DOODLE_INITIAL_Y_LOCATION = 550;
	public static final int DOODLE_MOVE = 20;
	public static final double FRAME_LEFT_EDGE = 20;
	public static final double FRAME_RIGHT_EDGE = 570;
	public static final double FRAME_TOP_EDGE = 0;
	public static final double FRAME_BOTTOM_EDGE = 600;
	public static final double FRAME_MIDPOINT = 300;
	public static final double DOODLE_INITIAL_VELOCITY = 0;
	public static final double INITIAL_PLATFORM_Y_OFFSET = 40;
	public static final double INITIAL_PLATFORM_X_OFFSET = -10;
	public static final double MINIMUM_PLATFORM_X_CONSTRAINT = -300;
	public static final double MAXIMUM_PLATFORM_X_CONSTRAINT = 300;
	public static final double MINIMUM_PLATFORM_Y_CONSTRAINT = 100;
	public static final double MAXIMUM_PLATFORM_Y_CONSTRAINT = 200;

}
