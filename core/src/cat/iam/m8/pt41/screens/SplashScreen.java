package cat.iam.m8.pt41.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;

import cat.iam.m8.pt41.Constants;
import cat.iam.m8.pt41.MyGdxGame;

public class SplashScreen extends ScreenAdapter {

	MyGdxGame game;
	Texture logo;
	
	float acumulatedTime;

	public SplashScreen(MyGdxGame game) {
		this.game = game;
		logo = new Texture("logo.jpg");
		acumulatedTime = 0;
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		super.resize(width, height);
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		super.render(delta);
		acumulatedTime += delta;
		
		if (acumulatedTime > Constants.TEMPS_SPLASHSCREEN) {
			game.setScreen(new GameScreen(game));
			this.dispose();
		} else {
			game.batch.begin();
			game.batch.draw(logo, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			game.batch.end();
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
		logo.dispose();
	}

}
