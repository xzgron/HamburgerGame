package world.objects.ingredients;

import game.GMath;
import game.Main;
import game.input.GMouse;
import game.input.GTimer;

import org.lwjgl.opengl.Display;

import controllers.Controlls;
import world.WorldObject;
import world.objects.GIngredient;
import world.objects.ingredients.bases.Activateable;
import world.objects.projectiles.CheeseProjectile;
import world.objects.projectiles.OnionRingProjectile;

public class OnionRings extends GIngredient implements Activateable{

	GTimer castTimer = new GTimer(1f);
	GTimer recoverTimer = new GTimer(2f);
	public OnionRings(float x, float y) {
		super(x, y, 100,100,"onionRings", 0.45f, 0.55f,49,12,6);
	}

	public void useFirstAbility(WorldObject user) {

		while(castTimer.hasExceeded() && use(1)){
			float xDir = GMouse.getX() - Display.getWidth()/2;
			float yDir = GMouse.getY() - Display.getHeight()/2;
			float speed = GMath.getLength(xDir, yDir)*6f;
			Main.game.world.spawn(new OnionRingProjectile(getX(),getY(),getHeadZPos(),xDir,yDir, speed));
			castTimer.reset();
		}
		
	}


	public void useSecondAbility(WorldObject user) {


	}

	public void update(){
		if(recoverTimer.hasExceeded()){
			recover(1);
			recoverTimer.reset();
			}
	}

}
