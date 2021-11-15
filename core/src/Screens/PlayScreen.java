package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.my.game.MainClass;

import Scenes.GameOver;
import Scenes.Hud;
import Scenes.Next_Level;
import Sprites.Dany;
import Sprites.Dany.State;
import Sprites.En1;
import Sprites.Enemy;
import Sprites.GameDany;
import ToolCreateForCreateWorld.WorldContactLisnener;
import ToolCreateForCreateWorld.WorldCreater;

public class PlayScreen implements Screen{

	private MainClass game;
	private TextureAtlas atlas;
	private OrthographicCamera gamecam;
	private Viewport gamePort;
	private Hud hud;
	private Dany player;
	//private  En1 en1;


	private boolean next_level = false;
	//tiled map
	private TmxMapLoader maploader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private World world;
	private WorldCreater create;
	private Box2DDebugRenderer b2dr;
 
	
	public PlayScreen(MainClass game)
	{
		atlas = new TextureAtlas("Dina_Enemies.pack");
		this.game = game;
		gamecam = new OrthographicCamera();
		
		//create ViewPort
		gamePort = new FitViewport(MainClass.V_WIDTH / MainClass.PPM,MainClass.V_HEIGHT / MainClass.PPM,gamecam );
		
		//hud
		hud = new Hud(game.batch,GameDany.Level);
		
		//load map
		maploader = new TmxMapLoader();
		map = maploader.load("level"+GameDany.Level+".tmx");
		renderer = new OrthogonalTiledMapRenderer(map, 1 / MainClass.PPM);
		
		//set gamecam to center correctly
		gamecam.position.set((gamePort.getWorldWidth()/2), (gamePort.getWorldHeight()/2),0);
		
		
		world = new World(new Vector2(0, -10 ), true);
		
		//dbug line of our box2d World
		b2dr = new Box2DDebugRenderer();
		
		create = new WorldCreater(this);
		
		player = new Dany(this);


		world.setContactListener(new WorldContactLisnener());

		//test Enenmy
		//en1 = new En1(this,.32f,.32f);
	}
	
	public TextureAtlas getAtlas()
	{
		return atlas;
	}

	@Override
	public void show() 
	{

		
	}

	
	public void handleInput(float dt) {
		 if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && Dany.previousSate != State.JUMPING &&Dany.previousSate != State.FALLING)
			player.b2body.applyLinearImpulse(new Vector2(0,4f), player.b2body.getWorldCenter(), true);
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2)
			player.b2body.applyLinearImpulse(new Vector2(0.1f,0), player.b2body.getWorldCenter(), true);
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2)
			player.b2body.applyLinearImpulse(new Vector2(-0.1f,0), player.b2body.getWorldCenter(), true);
	}
	
	public void update(float dt)
	{
		handleInput(dt);
		
		world.step(1/60f,6,2);
		gamecam.position.x = player.b2body.getPosition().x;
		
		player.update(dt);
		for(Enemy enemy: create.getEn1())
		{
			enemy.update(dt);
		}
		//en1.update(dt);

			hud.update(dt);

		gamecam.update();
		renderer.setView(gamecam);

		if(hud.get_worldtime() < 0 || player.get_y() < 0 || player.isDead()) {
			player.currentState = State.DEAD;
		}


	}
	
	
	@Override
	public void render(float delta) {
		update(delta);


		//clear Screen
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
		// render game
		renderer.render();
		
		//render Box2DDug
		//b2dr.render(world, gamecam.combined);

		
		
		game.batch.setProjectionMatrix(gamecam.combined);
		game.batch.begin();

		player.draw(game.batch);
		//en1.draw(game.batch);
		for(Enemy enemy: create.getEn1()) {
			enemy.draw(game.batch);
		}

		game.batch.end();
		
		//set batch to now draw Hud cam see
		game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();

		if(gameOver()){
			game.setScreen(new GameOver(game));
			dispose();
		}
		if(hud.get_score() == 5){
			game.setScreen(new Next_Level(game));
		}
	}
	public boolean gameOver() {
		if(player.currentState == State.DEAD) {
			return true;
		}
		else{
			return false;
		}

	}

	@Override
	public void resize(int width, int height) {
		gamePort.update(width, height);
		
	}

	public TiledMap getMap()
	{
		return map;
	}

	public World getWorld()
	{
		return world;
	}

	@Override
	public void pause() 
	{
	
		
	}

	@Override
	public void resume() 
	{
		
		
	}

	@Override
	public void hide() {
	
		
	}

	@Override
	public void dispose() {
		map.dispose();
		renderer.dispose();
		world.dispose();
		b2dr.dispose();
		hud.dispose();
		
	}


}