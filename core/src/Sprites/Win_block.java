
package Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import Scenes.Hud;
import Screens.PlayScreen;

public class Win_block extends Interactiveobject {

    public Win_block(PlayScreen screen, Rectangle bound) {
        super(screen, bound);
        fixture.setUserData(this);
        setCategoryFilter(Dany.Brick_bit);
    }

    @Override
    public void Headhit() {
        Gdx.app.log("Win_block","Collision");

        Hud hud = new Hud();
        hud.add_level();

    }
}


