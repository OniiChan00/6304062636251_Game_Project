package ToolCreateForCreateWorld;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.my.game.MainClass;

import Sprites.Coin;
import Sprites.Win_block;
import Sprites.block;


public class WorldCreater 
{
	public WorldCreater(World world,TiledMap map)
	{
		BodyDef bdef = new BodyDef();
		PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;
		
		//ground
		for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / MainClass.PPM, (rect.getY() + rect.getHeight() / 2) / MainClass.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / MainClass.PPM, rect.getHeight() / 2 / MainClass.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
		
		//pipe
		for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / MainClass.PPM, (rect.getY() + rect.getHeight() / 2) / MainClass.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / MainClass.PPM, rect.getHeight() / 2 / MainClass.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
		
		
		//brick
		for(MapObject object: map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class))
		{
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			new block(world,map,rect);
		}

		//win_block
        for(MapObject object : map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)){

            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Win_block(world,map ,rect);

        }


		//coin
		for(MapObject object: map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class))
		{
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			new Coin(world,map,rect);
		}
	}
}