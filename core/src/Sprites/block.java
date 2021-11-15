
package Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

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

	}
	

}