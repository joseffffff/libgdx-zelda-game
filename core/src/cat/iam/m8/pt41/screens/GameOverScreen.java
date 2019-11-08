package cat.iam.m8.pt41.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;

import cat.iam.m8.pt41.MyGdxGame;


public class GameOverScreen extends ScreenAdapter {

	final MyGdxGame game;
	Texture gameOverLogo;
	
	public GameOverScreen(MyGdxGame game) {
		this.game = game;
		gameOverLogo = new Texture("gameover.png");
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		
		game.batch.begin();
		game.batch.draw(gameOverLogo, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		game.batch.end();
		
	}
	
}
