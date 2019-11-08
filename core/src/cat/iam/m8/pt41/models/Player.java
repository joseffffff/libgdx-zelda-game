	package cat.iam.m8.pt41.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import cat.iam.m8.pt41.Constants;
import cat.iam.m8.pt41.Constants.Directions;

public class Player extends BaseCharacter {

	Animation<TextureRegion> animationUp;
	Animation<TextureRegion> animationRight;
	Animation<TextureRegion> animationDown;
	Animation<TextureRegion> animationLeft;

	Animation<TextureRegion> swortAnimationUp;
	Rectangle swort;

	boolean attack;
	int framesSwort;
	float stateTimeSwort; // fer un update amb el temps delta  
	
	
	private Texture swortUp;
	private Texture swortLeft;
	private Texture swortDown;
	private Texture swortRight;

	public Player(Rectangle rectangle, int direction) {
		super(rectangle, direction);
		swort = new Rectangle();
		prepareAnimations();
		attack = false; 
		framesSwort = 0; 
	}

	public void prepareAnimations() {

		prepareAnimationUp();
		prepareAnimationRight();
		prepareAnimationDown();
		prepareAnimationLeft();

		prepareSwortAnimation();

		stateTime = 0f;
		stateTimeSwort = 0f;
	}

	@Override
	public void draw(SpriteBatch openedBatch) {
		
		if (getDirection() == Constants.Directions.DOWN) {
			openedBatch.draw(getAnimation().getKeyFrame(stateTime, true), rectangle.x, rectangle.y, rectangle.width,
					rectangle.height);
		}
		
		if (attack) {
			swort = getSwort();
			Texture swortFrame = getSwortFrame();
			
			openedBatch.draw(swortFrame, swort.x, swort.y, swort.width, swort.height );
			attack = false;
		}
		
		if (getDirection() != Constants.Directions.DOWN) {
			openedBatch.draw(getAnimation().getKeyFrame(stateTime, true), rectangle.x, rectangle.y, rectangle.width,
					rectangle.height);
		}
		
	}
	
	private Texture getSwortFrame() {
		
		switch (super.getDirection()) {
			case Constants.Directions.UP:
				return this.swortUp;
	
			case Constants.Directions.RIGHT:
				return this.swortRight;
	
			case Constants.Directions.DOWN:
				return this.swortDown;
	
			case Constants.Directions.LEFT:
				return this.swortLeft;
	
			default:
				return null;
		}
	}

	private Rectangle getSwort() {
		
		switch (super.getDirection()) {
			case Constants.Directions.UP:
				
				return new Rectangle(rectangle.x + (rectangle.height/2)-20, rectangle.y + rectangle.height -20, 20, 70);
	
			case Constants.Directions.RIGHT:
				return new Rectangle(rectangle.x + (rectangle.height/2), rectangle.y + rectangle.height -50, 70, 20);
	
			case Constants.Directions.DOWN:
				return new Rectangle(rectangle.x + (rectangle.height/2)-20, rectangle.y - rectangle.height + 50, 20, 70);
	
			case Constants.Directions.LEFT:
				return new Rectangle(rectangle.x - (rectangle.height/2), rectangle.y + rectangle.height -50, 70, 20);
	
			default:
				return null;
		}
	}
	
	public boolean overlapsSwort(BaseCharacter enemy) {
		return enemy.rectangle.overlaps(swort);
	}

	private Animation<TextureRegion> getAnimation() {

		switch (super.getDirection()) {
			case Constants.Directions.UP:	
				return animationUp;
	
			case Constants.Directions.RIGHT:
				return animationRight;
	
			case Constants.Directions.DOWN:
				return animationDown;
	
			case Constants.Directions.LEFT:
				return animationLeft;
	
			default:
				return null;
		}
	}

	private void prepareAnimationUp() {

		TextureRegion[] walkFrames = getWalkFrames(Gdx.files.internal("sprites/link/moveuptest.png"),
				Constants.FRAME_COLS, Constants.FRAME_ROWS);

		animationUp = new Animation<TextureRegion>(Constants.TIME_FRAME, walkFrames);
	}

	private void prepareAnimationRight() {

		TextureRegion[] walkFrames = getWalkFrames(Gdx.files.internal("sprites/link/movelefttest.png"),
				Constants.FRAME_COLS, Constants.FRAME_ROWS);

		for (TextureRegion textureRegion : walkFrames) {
			textureRegion.flip(true, false);
		}

		animationRight = new Animation<TextureRegion>(Constants.TIME_FRAME, walkFrames);
	}

	private void prepareAnimationDown() {

		TextureRegion[] walkFrames = getWalkFrames(Gdx.files.internal("sprites/link/movedowntest.png"),
				Constants.FRAME_COLS, Constants.FRAME_ROWS);

		animationDown = new Animation<TextureRegion>(Constants.TIME_FRAME, walkFrames);
	}

	private void prepareAnimationLeft() {

		TextureRegion[] walkFrames = getWalkFrames(Gdx.files.internal("sprites/link/movelefttest.png"),
				Constants.FRAME_COLS, Constants.FRAME_ROWS);

		animationLeft = new Animation<TextureRegion>(Constants.TIME_FRAME, walkFrames);
	}

	private void prepareSwortAnimation() {
		
		this.swortUp = new Texture("swort/swortUp.png");
		this.swortLeft = new Texture("swort/swortLeft.png");
		this.swortDown = new Texture("swort/swortDown.png");
		this.swortRight = new Texture("swort/swortRight.png");
		
	
	}

	private TextureRegion[] getWalkFrames(FileHandle internal, int cols, int rows) {

		Texture spriteSheet = new Texture(internal);

		TextureRegion[][] tmp = TextureRegion.split(spriteSheet, spriteSheet.getWidth() / cols,
				spriteSheet.getHeight() / rows);

		TextureRegion[] walkFrames = new TextureRegion[cols * rows];

		int index = 0;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				walkFrames[index++] = tmp[i][j];
			}
		}

		return walkFrames;
	}

	public void atack(SpriteBatch openedBatch, float delta) {
		attack = true;
	}

}
