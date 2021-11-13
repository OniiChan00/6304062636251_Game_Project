
package Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

import Scenes.Hud;

public class Win_block extends Interactiveobject {

    public Win_block(World world, TiledMap map, Rectangle bound) {
        super(world, map, bound);
        fixture.setUserData(this);
        setCategoryFilter(Dany.Brick_bit);
    }

    @Override
    public void Headhit() {

    }
}


