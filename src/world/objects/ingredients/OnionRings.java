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
import world.objects.projectiles.CheeseProjectile;
import world.objects.projectiles.OnionRingProjectile;

public class OnionRings extends GIngredient implements Activateable{

	GTimer castTimer = new GTimer(0.8f);
	GTimer recoverTimer = new GTimer(2f);
	public OnionRings(float x, float y) {
		super(x, y, 100,50,"onion", 0.40f, 0.60f,49,12,6);
	}

	@Override
	public void useFirstAbility(WorldObject user,GameWorld world) {

		while(castTimer.hasExceeded() && use(1)){
			float xDir = GMouse.getX() - Display.getWidth()/2;
			float yDir = GMouse.getY() - Display.getHeight()/2;
			float speed = GMath.getLength(xDir, yDir)*6f;
			world.spawn(new OnionRingProjectile(getX(),getY(),getFootZPos(),xDir,yDir, speed));
			castTimer.reset();
		}
		
	}

	@Override
	public void useSecondAbility(WorldObject user, GameWorld world) {


	}
	
	@Override
	public void update(GameWorld world){
		if(recoverTimer.hasExceeded()){
			recover(1);
			recoverTimer.reset();
			}
	}

}
