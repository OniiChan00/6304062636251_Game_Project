package ToolCreateForCreateWorld;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import Sprites.Dany;
import Sprites.En1;
import Sprites.Enemy;
import Sprites.GameDany;
import Sprites.Interactiveobject;

public class WorldContactLisnener implements ContactListener{

	@Override
	public void beginContact(Contact contact) {
		Fixture fixA = contact.getFixtureA();
		Fixture fixB = contact.getFixtureB();

		int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

		if (fixA.getUserData() == "head" || fixB.getUserData() == "head") {
			Fixture head = fixA.getUserData() == "head" ? fixA : fixB;
			Fixture object = head == fixA ? fixB : fixA;

			if (object.getUserData() != null && Interactiveobject.class.isAssignableFrom(object.getUserData().getClass())) {
				((Interactiveobject) object.getUserData()).Headhit();
			}

		}
		switch (cDef)
		{
			case GameDany.ENEMY_BIT | GameDany.OBJECT_BIT:
				if(fixA.getFilterData().categoryBits == GameDany.ENEMY_BIT)
					((Enemy)fixA.getUserData()).reverse_Velo(true, false);
				else
					((Enemy)fixB.getUserData()).reverse_Velo(true, false);
				Gdx.app.log("Test","Reverse");
				break;

			case GameDany.ENEMY_BIT | GameDany.Dany_bit:

				((Dany) fixB.getUserData()).hit((Enemy)fixA.getUserData());
				break;

			case GameDany.ENEMY_BIT | GameDany.ENEMY_BIT:
				((Enemy)fixA.getUserData()).reverse_Velo(true, false);
				((Enemy)fixB.getUserData()).reverse_Velo(true, false);
				break;




		}
	}

	@Override
	public void endContact(Contact contact) {
		
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}

}
