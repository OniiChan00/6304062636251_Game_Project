package Sprites;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Filter;

import Screens.PlayScreen;

public class GameDany extends Game {
    public static final int V_WIDTH = 400;
    public static final int V_HEIGHT = 208;
    public static final float PPM = 100;

    public static int Level = 1;
    public static final short NOTHING_BIT = 0;
    public static final short GROUND_BIT = 1;
    public static final short Dany_bit = 2;
    public static final short Brick_bit = 4;
    public static final short coin_bit = 8;
    public static final short destroyed_bit = 16;
    public static final short OBJECT_BIT = 32;
    public static final short ENEMY_BIT = 64;

    public SpriteBatch batch;

    @Override
    public void create() {

        batch = new SpriteBatch();

    }
    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
    }

    @Override
    public void render () {
        super.render();
    }
}
