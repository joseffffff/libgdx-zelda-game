package cat.iam.m8.pt41.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

import cat.iam.m8.pt41.Constants;

public class Shot {

	private Circle circle;
	private Texture texture;
	private int direction;

	public Shot(Circle circle, Texture texture, int direction) {
		this.circle = circle;
		this.texture = texture;
		this.direction = direction;
	}

	public void move(float delta) {
		// TODO Auto-generated method stub
		switch (direction) {
		
			case Constants.Directions.UP:
				circle.y += Constants.VELOCITAT_SHOT * delta;
				break;
				
			case Constants.Directions.RIGHT:
				circle.x += Constants.VELOCITAT_SHOT * delta;
				break;
				
			case Constants.Directions.DOWN:
				circle.y -= Constants.VELOCITAT_SHOT * delta;
				break;
				
			case Constants.Directions.LEFT:
				circle.x -= Constants.VELOCITAT_SHOT * delta;
				break;

			default:
				break;
		}
	}

	public void draw(SpriteBatch openedBatch) {
		// TODO Auto-generated method stub
		openedBatch.draw(texture, circle.x, circle.y, circle.radius, circle.radius);
	}

	public boolean overlaps(Rectangle rPlayer) {
		// TODO Auto-generated method stub
		return (rPlayer.x < circle.x && (rPlayer.x + rPlayer.width) > circle.x)
				&& (rPlayer.y < circle.y && (rPlayer.y + rPlayer.height) > circle.y);
	}
	
	public void invertDirection() {
		
		switch (direction) {
		
			case Constants.Directions.UP:
				direction = Constants.Directions.DOWN;
				break;
				
			case Constants.Directions.RIGHT:
				direction = Constants.Directions.LEFT;
				break;
				
			case Constants.Directions.DOWN:
				direction = Constants.Directions.UP;
				break;
				
			case Constants.Directions.LEFT:
				direction = Constants.Directions.RIGHT;
				break;
	
			default:
				break;
		}
	}

}
