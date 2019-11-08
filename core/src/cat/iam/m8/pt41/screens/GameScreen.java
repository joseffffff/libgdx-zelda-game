package cat.iam.m8.pt41.screens;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import cat.iam.m8.pt41.Constants;
import cat.iam.m8.pt41.MyGdxGame;
import cat.iam.m8.pt41.models.Octorok;
import cat.iam.m8.pt41.models.Player;

public class GameScreen extends ScreenAdapter {

	MyGdxGame game;

	Player player;

	private Texture backgroundTexture;
	private Sprite backgroundSprite;
	
	List<Octorok> octoroks;
	
	float time;
	float timeNewEnemy;

	public GameScreen(MyGdxGame game) {
		this.game = game;

		player = new Player(
						new Rectangle(Constants.PLAYER_INITIAL_POSITION_X,
										Constants.PLAYER_INITIAL_POSITION_Y, 
										Constants.PLAYER_WIDTH, 
										Constants.PLAYER_HEIGHT),
						Constants.Directions.DOWN);

		backgroundTexture = new Texture("map.jpg");
		backgroundSprite = new Sprite(backgroundTexture);
		
		octoroks = new ArrayList<Octorok>();
		octoroks.add(new Octorok(new Rectangle(Constants.WORLD_HEIGHT, 
												Constants.WORLD_WIDTH/2,
												Constants.OCTOROK_WIDTH, 
												Constants.OCTOROK_HEIGHT), Constants.Directions.DOWN));
		
		time = 0f;
		timeNewEnemy = Constants.INITIAL_TIME_NEW_ENEMY;
	}

	private void input(float delta) {
		
		boolean run = Gdx.input.isKeyPressed(Keys.ALT_LEFT);
		
		if (Gdx.input.isKeyPressed(Keys.A)) {
			player.move(delta, Constants.Directions.LEFT, run, Constants.PLAYER_VELOCITATX);
		}

		if (Gdx.input.isKeyPressed(Keys.S)) {
			player.move(delta, Constants.Directions.DOWN, run, Constants.PLAYER_VELOCITATY);
		}

		if (Gdx.input.isKeyPressed(Keys.D)) {
			player.move(delta, Constants.Directions.RIGHT, run, Constants.PLAYER_VELOCITATX);
		}

		if (Gdx.input.isKeyPressed(Keys.W)) {
			player.move(delta, Constants.Directions.UP, run, Constants.PLAYER_VELOCITATY);
		}
		
		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			player.atack(game.batch, delta);
		}
	}
	
	private void moveEnemies(float delta) {
		for (Octorok octorok : octoroks) {
			octorok.move(delta, octorok.getDirection(), false, player);
			octorok.update(delta, player);
		}
	}
	
	private void drawEnemies(SpriteBatch batch) {
		for (Octorok octorok : octoroks) {
			octorok.draw(batch);
		}
	}
	
	private void checkDie() {
		// TODO Auto-generated method stub
		for (Octorok octorok : octoroks) {
			if (octorok.overlaps(player) || octorok.isKiller()) {
				game.setScreen(new GameOverScreen(game));
			}
		}
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	public void renderBackground() {
		backgroundSprite.draw(game.batch);
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		input(delta);
		moveEnemies(delta);
		checkDie();
		enemydie();
		
		game.batch.begin();
		renderBackground();
		player.draw(game.batch);
		drawEnemies(game.batch);
		newEnemy(delta);
		game.batch.end();
	}

	private void enemydie() {
		
		for (Iterator<Octorok> iterator = octoroks.iterator(); iterator.hasNext();) {
			Octorok enemy = (Octorok) iterator.next();
			
			if (player.overlapsSwort(enemy)) {
				iterator.remove();
			}
		}
	}
	
	public void hitShots() {
		for (Iterator<Octorok> iterator = octoroks.iterator(); iterator.hasNext();) {
			Octorok enemy = (Octorok) iterator.next();
			enemy.checkHitShots(player);			
		}
	}

	private void newEnemy(float delta) {
		
		Random rand = new Random();
		
		time += delta;
		
		if (time >= 0) {
			
			if (time > timeNewEnemy) {
				
				int from = rand.nextInt(4);
				
				Rectangle rectangleNewOctorok = new Rectangle();
				rectangleNewOctorok.height = Constants.OCTOROK_HEIGHT;
				rectangleNewOctorok.width = Constants.OCTOROK_WIDTH;
				
				int direction = 0;
				
				switch (from) {
					
					case 0: // aparece des de arriba
						rectangleNewOctorok.y = Constants.WORLD_HEIGHT;
						rectangleNewOctorok.x = (float) rand.nextInt((int)Constants.WORLD_WIDTH);
						direction = Constants.Directions.DOWN;
						break;
						
					case 1: // aparece des de la derecha
						rectangleNewOctorok.y = (float) rand.nextInt((int)Constants.WORLD_HEIGHT);
						rectangleNewOctorok.x = Constants.WORLD_WIDTH;
						direction = Constants.Directions.LEFT;
						break;
						
					case 2: // aprece des de abajo
						rectangleNewOctorok.y = 0;
						rectangleNewOctorok.x = (float) rand.nextInt((int)Constants.WORLD_WIDTH);
						direction = Constants.Directions.UP;
						break;
						
					case 3: // aparece des de la izquierda
						rectangleNewOctorok.y = (float) rand.nextInt((int)Constants.WORLD_HEIGHT);
						rectangleNewOctorok.x = 0;
						direction = Constants.Directions.RIGHT;
						break;
						
					default:
						break;
				}
				
				octoroks.add(new Octorok(rectangleNewOctorok, direction));
				
				
				time = 0;
			}
			
		}
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}

}
