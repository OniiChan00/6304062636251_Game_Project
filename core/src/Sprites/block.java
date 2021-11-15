
package Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

import Scenes.Hud;
import Screens.PlayScreen;

public class block extends Interactiveobject{

	public block(PlayScreen screen, Rectangle bound)
	{
		super(screen, bound);
		fixture.setUserData(this);
		setCategoryFilter(GameDany.Brick_bit);
	}

	@Override
	public void Headhit() {
		Gdx.app.log("block","Collision");
		setCategoryFilter(GameDany.destroyed_bit);
		getCell().setTile(null);
		
		Hud hud = new Hud();
		hud.addScore(50);
		
	}
	

}