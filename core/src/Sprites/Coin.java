package Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.my.game.MainClass;

public class Coin extends Interactiveobject {
	public Coin(World world,TiledMap map,Rectangle bound)
	{
		super(world,map,bound);
		fixture.setUserData(this);
	}

	@Override
	public void Headhit() {
		Gdx.app.log("Coin","Collision");
		
	}

}