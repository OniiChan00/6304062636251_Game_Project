package Sprites;

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
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
	
		for(MapObject object: map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class))
		{
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set((bound.getX() + bound.getWidth()/2)/ MainClass.PPM,(bound.getY() + bound.getHeight()/2)/ MainClass.PPM);
		
			body = world.createBody(bdef);
			
			shape.setAsBox(bound.getWidth() / 2 / MainClass.PPM, bound.height / 2 / MainClass.PPM);
			fdef.shape = shape;
			body.createFixture(fdef);
		}
	}

}