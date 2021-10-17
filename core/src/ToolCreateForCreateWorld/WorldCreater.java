package ToolCreateForCreateWorld;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

import Sprites.Coin;
import Sprites.block;
import Sprites.pipe;

public class WorldCreater 
{
	public WorldCreater(World world,TiledMap map)
	{
		
		
		//ground
		for(MapObject object: map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class))
		{
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			new block(world, map, rect);
			
		}
		
		//pipe
		for(MapObject object: map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class))
		{
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			new pipe(world, map, rect);
		}
		
		//brick
		for(MapObject object: map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class))
		{
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			new block(world,map,rect);
		}
		
		//coin
		for(MapObject object: map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class))
		{
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			new Coin(world,map,rect);
		}
	}
}