package Sprites;

import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;

import Scenes.Hud;
import Screens.PlayScreen;

public class Coin extends Interactiveobject {
	
	private static TiledMapTileSet tile_map;
	private final int TAKEN_COIN = 28;
	private boolean already_collect = false;
	
	
	public Coin(PlayScreen screen, Rectangle bound) {
		super(screen,bound);
		tile_map =map.getTileSets().getTileSet("tileset_gutter");
		fixture.setUserData(this);
		setCategoryFilter(GameDany.coin_bit);
	}



	@Override
	public void Headhit() {
		if(already_collect == false)
		{
			getCell().setTile(tile_map.getTile(TAKEN_COIN));	
			Hud hud = new Hud();
			hud.addScore(1);
			already_collect = true;
		}
		else{}
	}

}