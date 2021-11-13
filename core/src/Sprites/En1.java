package Sprites;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import Screens.PlayScreen;

public class En1 extends Enemy{

    public En1(PlayScreen screen, float x, float y) {
        super(screen, x, y);
    }

    @Override
    protected void defineEnemy() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(48 / MainClass.PPM ,48 / MainClass.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);



        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6 / MainClass.PPM);
        fdef.filter.categoryBits = Dany.ENEMY_BIT;
        fdef.filter.maskBits = Dany.GROUND_BIT| Dany.Brick_bit | Dany.coin_bit | Dany.ENEMY_BIT| Dany.OBJECT_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef);

    }
}
