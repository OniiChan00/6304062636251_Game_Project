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
import com.badlogic.gdx.utils.Array;
import com.my.game.MainClass;


import Screens.PlayScreen;
import Sprites.Coin;
import Sprites.GameDany;
import Sprites.block;
import Sprites.En1;


public class WorldCreater {
    private Array<En1> en1;
	public WorldCreater(PlayScreen screen) {
	    World world = screen.getWorld();
	    TiledMap map = screen.getMap();
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
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / GameDany.PPM, (rect.getY() + rect.getHeight() / 2) / GameDany.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / GameDany.PPM, rect.getHeight() / 2 / GameDany.PPM);
            fdef.shape = shape;
            fdef.filter.categoryBits = GameDany.OBJECT_BIT;
            body.createFixture(fdef);
        }
		
		
		//brick
		for(MapObject object: map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class))
		{
			Rectangle rect = ((RectangleMapObject) object).getRectangle();

			new block(screen,rect);
		}


		//coin
		for(MapObject object: map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class))
		{
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			new Coin(screen,rect);
		}

		en1 = new Array<En1>();
        for(MapObject object: map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            en1.add(new En1(screen, rect.getX() /GameDany.PPM, rect.getY()/GameDany.PPM));
        }

	}

	public Array<En1> getEn1()
    {
        return en1;
    }

}