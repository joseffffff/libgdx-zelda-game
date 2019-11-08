package cat.iam.m8.pt41.models;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import cat.iam.m8.pt41.Constants;

public abstract class BaseCharacter {
	
	// A variable for tracking elapsed time for the animation
	float stateTime;
	
	Rectangle rectangle;
	
	private int direction; 
	
	public BaseCharacter(Rectangle rectangle, int direction) {
		this.rectangle = rectangle;
		this.direction = direction;
	}
	
	public int getDirection() {
		return direction;
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public void move(float delta, int direction, boolean run, float velocity) {

		stateTime += delta;

		this.direction = direction;
		
		if (run) {
			velocity *= 1.5;
		}

		switch (direction) {

			case Constants.Directions.UP:
				rectangle.y += velocity * delta;
				break;
	
			case Constants.Directions.RIGHT:
				rectangle.x += velocity * delta;
				break;
	
			case Constants.Directions.DOWN:
				rectangle.y -= velocity * delta;
				break;
	
			case Constants.Directions.LEFT:
				rectangle.x -= velocity * delta;
				break;
		}
		
		if (rectangle.x < 0) {
			rectangle.x = 2;
		} 
		
		if (rectangle.x > (Constants.WORLD_WIDTH-rectangle.width)) {
			rectangle.x = Constants.WORLD_WIDTH - rectangle.width - 20;
		}
		
		if (rectangle.y < 0) {
			rectangle.y = 2;
		}
		
		if (rectangle.y > (Constants.WORLD_HEIGHT - rectangle.height)) {
			rectangle.y = Constants.WORLD_HEIGHT - rectangle.height - 20 ;
		}
	}
	
	public abstract void draw(SpriteBatch openedBatch);
}
