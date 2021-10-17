package Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

public class pipe extends Interactiveobject{

	public pipe(World world, TiledMap map, Rectangle bound) 
	{
		super(world, map, bound);
	}

}