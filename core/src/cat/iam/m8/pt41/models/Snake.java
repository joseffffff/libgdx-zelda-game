package cat.iam.m8.pt41.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Snake extends BaseCharacter {
	
	Animation<TextureRegion> animationUp;
	Animation<TextureRegion> animationRight;
	Animation<TextureRegion> animationDown;
	Animation<TextureRegion> animationLeft;
	
	private int actualDirection;

	public Snake(Rectangle rectangle, int direction) {
		super(rectangle, direction);
		prepareAnimations();
	}

	private void prepareAnimations() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(SpriteBatch openedBatch) {
		// TODO Auto-generated method stub

	}

}
