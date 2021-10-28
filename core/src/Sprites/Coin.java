package Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

import Scenes.Hud;

public class Coin extends Interactiveobject {
	
	private static TiledMapTileSet tile_map;
	private final int TAKEN_COIN = 28;
	private boolean already_collect = false;
	
	
	public Coin(World world,TiledMap map,Rectangle bound)
	{
		super(world,map,bound);
		tile_map =map.getTileSets().getTileSet("tileset_gutter");
		fixture.setUserData(this);
		setCategoryFilter(Dany.coin_bit);
	}

	@Override
	public void Headhit() {
		if(already_collect == false)
		{
			getCell().setTile(tile_map.getTile(TAKEN_COIN));	
			Hud hud = new Hud();
			hud.addScore(100);
			already_collect = true;
		}
		else{}
	}

}