package Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.*;
import com.my.game.MainClass;

import Screens.PlayScreen;


public abstract class Interactiveobject {
	
	
	protected World world;
	protected TiledMap map;
	protected TiledMapTile tile;
	protected Rectangle bound;
	protected Body body;
	protected Fixture fixture;
		
	
	public Interactiveobject(PlayScreen screen, Rectangle bound) {
		this.world = screen.getWorld();
		this.map = screen.getMap();
		this.bound = bound;
		
		BodyDef bdef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		
		bdef.type = BodyDef.BodyType.StaticBody;
		bdef.position.set((bound.getX() + bound.getWidth()/2)/ MainClass.PPM,(bound.getY() + bound.getHeight()/2)/ MainClass.PPM);
	
		body = world.createBody(bdef);
		
		shape.setAsBox(bound.getWidth() / 2 / MainClass.PPM, bound.height / 2 / MainClass.PPM);
		fdef.shape = shape;
		fixture = body.createFixture(fdef);
		
	}

	public abstract void Headhit();

	
	public void setCategoryFilter(short filterBit)
	{
		Filter filter = new Filter();
		filter.categoryBits = filterBit;
		fixture.setFilterData(filter);
	}

	public TiledMapTileLayer.Cell getCell()
	{
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
		return layer.getCell((int)(body.getPosition().x * MainClass.PPM / 16), (int)(body.getPosition().y * MainClass.PPM / 16));
	}
	

}