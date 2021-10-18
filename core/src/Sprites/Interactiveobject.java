package Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.my.game.MainClass;


public abstract class Interactiveobject {
	protected World world;
	protected TiledMap map;
	protected TiledMapTile tile;
	protected Rectangle bound;
	protected Body body;
	protected Fixture fixture;
		
	
	public Interactiveobject(World world,TiledMap map,Rectangle bound)
	{
		this.world = world;
		this.map = map;
		this.bound = bound;
		
		BodyDef bdef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		
		bdef.type = BodyDef.BodyType.StaticBody;
		bdef.position.set((bound.getX() + bound.getWidth()/2)/ MainClass.PPM,(bound.getY() + bound.getHeight()/2)/ MainClass.PPM);
	
		body = world.createBody(bdef);
		
		shape.setAsBox(bound.getWidth() / 2 / MainClass.PPM, bound.height / 2 / MainClass.PPM);
		fdef.shape = shape;
		body.createFixture(fdef);
		fixture = body.createFixture(fdef);
		
		
	}
	
	public abstract void Headhit();
	

}