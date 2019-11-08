package cat.iam.m8.pt41.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

import cat.iam.m8.pt41.Constants;

public class Octorok extends BaseCharacter {
	
	Animation<TextureRegion> animationUp;
	Animation<TextureRegion> animationRight;
	Animation<TextureRegion> animationDown;
	Animation<TextureRegion> animationLeft;
	
	List<Shot> shots;
	
	float timeShot;
	
	boolean kill;

	public Octorok(Rectangle rectangle, int direction) {
		super(rectangle, direction);
		prepareAnimations();
		timeShot = 0;
		
		shots = new ArrayList<Shot>();
		kill = false;
	}
	
	public void prepareAnimations() {
		
		TextureRegion[] allFrames = getWalkFrames(Gdx.files.internal("octorokBlue.png"), 4, 4);

		prepareAnimationUp(Arrays.copyOfRange(allFrames, 12, 16));
		prepareAnimationRight(Arrays.copyOfRange(allFrames, 8, 12)); 
		prepareAnimationDown(Arrays.copyOfRange(allFrames, 0, 4));
		prepareAnimationLeft(Arrays.copyOfRange(allFrames, 4, 8));
		
		stateTime = 0f;
	}

	private void prepareAnimationLeft(TextureRegion[] textureRegions) {
		animationLeft = new Animation<TextureRegion>(Constants.TIME_FRAME, textureRegions);		
	}

	private void prepareAnimationRight(TextureRegion[] textureRegions) {
		animationRight = new Animation<TextureRegion>(Constants.TIME_FRAME, textureRegions);		
	}

	private void prepareAnimationDown(TextureRegion[] textureRegions) {
		animationDown = new Animation<TextureRegion>(Constants.TIME_FRAME, textureRegions);		
	}

	private void prepareAnimationUp(TextureRegion[] textureRegions) {
		animationUp = new Animation<TextureRegion>(Constants.TIME_FRAME, textureRegions);		
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

	@Override
	public void draw(SpriteBatch openedBatch) {
		openedBatch.draw(getAnimation().getKeyFrame(stateTime, true), rectangle.x, rectangle.y, rectangle.width,
				rectangle.height);
		
		for (Shot shot : shots) {
			shot.draw(openedBatch);
		}
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
	
	public void move(float delta, int direction, boolean run, Player player) {
		// TODO Auto-generated method stub
		super.move(delta, direction, run, Constants.OCTOROK_VELOCITATX);
		
		float diferenceX = 0;
		boolean playerMoreX = false;
		float diferenceY = 0;
		boolean playerMoreY = false;
		
		// Veure cap a on han de caminar
		if (player.rectangle.x > rectangle.x){
			rectangle.x += Constants.OCTOROK_VELOCITATY * delta;
			diferenceX = player.rectangle.x - rectangle.x;
			playerMoreX = true;
		} else {
			rectangle.x -= Constants.OCTOROK_VELOCITATY * delta;
			diferenceX = rectangle.x - player.rectangle.x;
			playerMoreX = false;
		}
		
		if (player.rectangle.y > rectangle.y){
			rectangle.y += Constants.OCTOROK_VELOCITATY * delta;
			diferenceY = player.rectangle.y - rectangle.y;
			playerMoreY = true;
		} else {
			rectangle.y -= Constants.OCTOROK_VELOCITATY * delta;
			diferenceY = rectangle.y - player.rectangle.y;
			playerMoreY = false;
		}
		
		// Veure cap a on han de mirar
		if (diferenceX > diferenceY) {
			if (playerMoreX) {
				setDirection(Constants.Directions.RIGHT);
			} else {
				setDirection(Constants.Directions.LEFT);
			}
		} else {
			if (playerMoreY) {
				setDirection(Constants.Directions.UP);
			} else {
				setDirection(Constants.Directions.DOWN);
			}
		}
		
		for (Shot shot : shots) {
			shot.move(delta);
			if (shot.overlaps(player.rectangle)) {
				kill = true;
			}
		}
	}

	private void attack(int direction) {
		shots.add(
				new Shot(
							new Circle(this.rectangle.x, this.rectangle.y, Constants.RADIUS_SHOT),
							new Texture("shot.png"), 
							direction));		
	}

	public boolean overlaps(Player player) {
		// TODO Auto-generated method stub
		return rectangle.overlaps(player.rectangle);
	}
	
	public boolean isKiller() {
		return kill;
	}

	public void update(float delta, Player player) {
		timeShot += delta;
		
		// Veure si han de disparar i disparar
		if (timeShot > Constants.TIME_SHOT) {
			
			if ((player.rectangle.x+10 > this.rectangle.x && player.rectangle.x-10 < this.rectangle.x)
					|| (player.rectangle.y+10 > this.rectangle.y && player.rectangle.y-10 < this.rectangle.y)) {
				attack(this.getDirection());
				timeShot = 0;				
			}
			
		}
		
	}
	
	public void checkHitShots(Player p) {
		
		
		for (Shot shot : shots) {
			if (shot.overlaps(p.swort)) {
				shot.invertDirection();
			}
		}
	}
}

