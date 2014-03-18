package world.objects.ingredients;

import game.GMath;
import game.Main;
import game.parts.GameWorld;
import game.tools.GMouse;
import game.tools.GTimer;
import options.Controlls;

import org.lwjgl.opengl.Display;

import world.WorldObject;
import world.objects.GIngredient;
import world.objects.ingredients.bases.Activateable;
import world.objects.projectiles.BananaProjectile;
import world.objects.projectiles.CheeseProjectile;
import world.objects.projectiles.OnionRingProjectile;

public class Banana extends GIngredient implements Activateable{

	GTimer castTimer = new GTimer(0.3f);
	GTimer recoverTimer = new GTimer(2f);
	public Banana(float x, float y) {
		super(x, y, 100,100,"banana", 0.45f, 0.55f,49,30,3);
	}
	
	@Override
	public void useFirstAbility(WorldObject user, GameWorld world) {

		while(castTimer.hasExceeded() && use(1)){
			float xDir = GMouse.getX() - Display.getWidth()/2;
			float yDir = GMouse.getY() - Display.getHeight()/2;
			float speed = GMath.getLength(xDir, yDir)*5f;
			world.spawn(new BananaProjectile(getX(),getY(),user.getFootZPos()+30,xDir,yDir, speed, user, this, false));
			castTimer.reset();
		}
		
	}

	@Override
	public void useSecondAbility(WorldObject user, GameWorld world) {
		while(castTimer.hasExceeded() && use(1)){
			float xDir = GMouse.getX() - Display.getWidth()/2;
			float yDir = GMouse.getY() - Display.getHeight()/2;
			float speed = GMath.getLength(xDir, yDir)*5f;
			world.spawn(new BananaProjectile(getX(),getY(),user.getFootZPos()+30,xDir,yDir, speed, user, this, true));
			castTimer.reset();
		}

	}
	
	@Override
	public void update(GameWorld world){
		//if(recoverTimer.hasExceeded()){
			//recover(1);
			//recoverTimer.reset();
			//}
	}

}
