package Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.my.game.MainClass;

public class Dany extends Sprite{
	public World world;
	public Body b2body;
	
	public Dany(World world)
	{
		this.world = world;
		defineDany();
	}
	public void defineDany()
	{
		BodyDef bdef = new BodyDef();
		bdef.position.set(48 / MainClass.PPM ,48 / MainClass.PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		
		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(5 / MainClass.PPM);
		
		fdef.shape = shape;
		b2body.createFixture(fdef);
		
	}
}