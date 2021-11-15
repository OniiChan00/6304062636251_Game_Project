package Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.my.game.MainClass;

import Screens.PlayScreen;

public class En1 extends Enemy{
    private float stateTime;
    private Animation walkAnimation;
    private Array<TextureRegion> frames;

    public En1(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        frames = new Array<TextureRegion>();
        for(int i = 0; i < 2; i++)
        {
            frames.add(new TextureRegion(screen.getAtlas().findRegion("teddy"), i *16,0,16,16));
            walkAnimation = new Animation(0.4f,frames);
            stateTime = 0;
            setBounds(getX(),getY(),16/ MainClass.PPM,16/ MainClass.PPM);
        }
    }

    public void update(float dt)
    {
        stateTime += dt;
        setPosition(b2body.getPosition().x - getWidth()/2,b2body.getPosition().y - getHeight()/2);
        setRegion((TextureRegion) walkAnimation.getKeyFrame(stateTime,true));
        b2body.setLinearVelocity(velocity);

    }

    @Override
    protected void defineEnemy() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(), getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / MainClass.PPM);
        fdef.filter.categoryBits = GameDany.ENEMY_BIT;
        fdef.filter.maskBits = GameDany.GROUND_BIT |
                GameDany.Brick_bit |
                GameDany.coin_bit |
                GameDany.ENEMY_BIT|
                GameDany.Dany_bit|
                GameDany.OBJECT_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
    }


}
