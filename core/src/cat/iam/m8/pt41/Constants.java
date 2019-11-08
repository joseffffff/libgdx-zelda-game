package cat.iam.m8.pt41;

public abstract class Constants {

	public static final float TEMPS_SPLASHSCREEN = 3;

	
	public static final float WORLD_WIDTH = 1200;
	public static final float WORLD_HEIGHT = 700;

	public static abstract class Directions {
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
		public static final int LEFT = 4;
	}
	
	// Constant rows and columns of the sprite sheet
	public static final int FRAME_COLS = 10, FRAME_ROWS = 1;
	
	public static final float TIME_FRAME = 0.08f;
	
	// PLAYER
	public static final float PLAYER_INITIAL_POSITION_X = 200;
	public static final float PLAYER_INITIAL_POSITION_Y = 200;
	public static final float PLAYER_HEIGHT = 100;
	public static final float PLAYER_WIDTH = 75;
	public static final float PLAYER_VELOCITATX = 90;
	public static final float PLAYER_VELOCITATY = 90;


	public static final float OCTOROK_VELOCITATX = 20;
	public static final float OCTOROK_VELOCITATY = 20;


	public static final float INITIAL_TIME_NEW_ENEMY = 5;


	public static final float OCTOROK_WIDTH = 70;
	public static final float OCTOROK_HEIGHT = 60;


	public static final float TIME_SHOT = 2;


	public static final float RADIUS_SHOT = 30;


	public static final float VELOCITAT_SHOT = 200;
	
}
