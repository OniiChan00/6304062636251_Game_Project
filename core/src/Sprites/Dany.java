package Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.my.game.MainClass;

import Screens.PlayScreen;


public class Dany extends Sprite{
	public enum State {FALLING, JUMPING, STANDING, RUNNING, DEAD}
	public static State currentState;
	public static State previousSate;
	private Animation<TextureRegion> DinaRun;
	private Animation<TextureRegion> DinaJump;
	private float stateTimer;
	private boolean runningRight;
	private boolean DanyIsDead = false;


	
	public World world;
	public Body b2body;
	private TextureRegion DinaStand;
	
	public Dany(PlayScreen screen) {
		super(screen.getAtlas().findRegion("dina"));
		this.world = screen.getWorld();

		currentState = State.STANDING;
		previousSate = State.STANDING;
		stateTimer = 0;
		runningRight = true;
		
		Array<TextureRegion> frames = new Array<TextureRegion>();
		
		for(int i = 2; i < 4; i++)
		{
			frames.add(new TextureRegion(getTexture(),115 + (i * 16),43,16,16));
		}
		DinaRun = new Animation<>(0.1f,frames);
		
		
		for(int i = 4; i < 5; i++)
		{
			frames.add(new TextureRegion(getTexture(),115 + (i * 16),43,16,16));
		}
		DinaJump = new Animation<>(0.1f, frames);
		frames.clear();
		
		
		
		defineDany();
		DinaStand = new TextureRegion(getTexture(),115 ,43,16,16);
		setBounds(0, 10, 16/MainClass.PPM, 16/MainClass.PPM);
		setRegion(DinaStand);
		
		
	}


	
	public void update(float dt) {
		setPosition(b2body.getPosition().x - getWidth()/2 , b2body.getPosition().y - getHeight()/2 );
		setRegion(getFrame(dt));
	}
	
	public TextureRegion getFrame(float dt) {
		currentState = getState();
		
		TextureRegion region;
		switch(currentState)
		{
		case JUMPING:
			region = DinaJump.getKeyFrame(stateTimer);
			break;
		case RUNNING:
			region = DinaRun.getKeyFrame(stateTimer, true);
			break;
		case FALLING:
		case STANDING:
		default:
			region = DinaStand;
			break;
		}
		if((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX())
		{
			region.flip(true, false);
			runningRight = false;
		}
		
		else if((b2body.getLinearVelocity().x > 0 || runningRight )&& region.isFlipX())
		{
			region.flip(true, false);
			runningRight = true;
		}
		//System.out.println(currentState);
		stateTimer = currentState == previousSate ? stateTimer + dt: 0;
		previousSate = currentState;
		
		return region;
	}
	
	public State getState() {
		if(b2body.getLinearVelocity().y > 0.01 || (b2body.getLinearVelocity().y < 0 && previousSate == State.JUMPING))
		{
			return State.JUMPING;
		}
		else if(b2body.getLinearVelocity().y<0)
		{
			return State.FALLING;
		}
		else if(b2body.getLinearVelocity().x != 0)
		{
			return State.RUNNING;
		}
		else
		{
			return State.STANDING;
		}
	}
	
	public float get_y()
	{
		return b2body.getPosition().y;
	}

	public void defineDany() {
		BodyDef bdef = new BodyDef();
		bdef.position.set(48 / MainClass.PPM ,48 / MainClass.PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		
		
		
		FixtureDef fdef = new FixtureDef();	
		CircleShape shape = new CircleShape();
		shape.setRadius(6 / MainClass.PPM);
		fdef.filter.categoryBits = GameDany.Dany_bit;
		fdef.filter.maskBits = GameDany.GROUND_BIT|
				GameDany.Brick_bit |
				GameDany.coin_bit |
				GameDany.ENEMY_BIT|
				GameDany.OBJECT_BIT;
				
		fdef.shape = shape;
		b2body.createFixture(fdef).setUserData(this);
		
		EdgeShape head = new EdgeShape();
		head.set(new Vector2(-2/MainClass.PPM,6/MainClass.PPM),new Vector2(2/MainClass.PPM,6/MainClass.PPM));
		fdef.shape = head;
		fdef.isSensor = true;
		
		b2body.createFixture(fdef).setUserData("head");	
	}

	public void die() {
		DanyIsDead = true;
		Filter filter = new Filter();
		filter.maskBits = GameDany.NOTHING_BIT;
	}

	public void hit(Enemy enemy)
	{
		die();
	}

	public boolean isDead(){
		return DanyIsDead;
	}

	public float getStateTimer() {
		return stateTimer;
	}
}