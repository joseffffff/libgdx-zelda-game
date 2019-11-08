package cat.iam.m8.pt41;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import cat.iam.m8.pt41.screens.SplashScreen;

public class MyGdxGame extends Game {

	public SpriteBatch batch;

	private Viewport viewport;
	private Camera camera;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		
		
		camera = new OrthographicCamera();
		viewport = new FitViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, camera);
		camera.position.x = Constants.WORLD_WIDTH / 2;
		camera.position.y = Constants.WORLD_HEIGHT / 2;
		
		this.setScreen(new SplashScreen(this));
		
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
	}

	@Override
	public void render() {
		
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		// Neteja de pantalla
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		super.render();
	}
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		super.resize(width, height);
		viewport.update(width, height);
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}
