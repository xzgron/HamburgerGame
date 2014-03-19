package world.objects.ingredients;

import options.Controlls;

import org.lwjgl.opengl.Display;




import game.GMath;
import game.Main;
import game.parts.GameWorld;
import game.tools.GMouse;
import game.tools.GTimer;
import world.WorldObject;
import world.objects.GIngredient;
import world.objects.ingredients.bases.Activateable;
import world.objects.projectiles.CheeseProjectile;

public class Cheese extends GIngredient implements Activateable{

	GTimer castTimer = new GTimer(0.09f);
	GTimer recoverTimer = new GTimer(0.1f);
	public Cheese(float x, float y) {
		super(x, y, 115,70,"cheese", 0.45f, 0.55f, 57, 30, 100);
	}
	@Override
	public void useFirstAbility(WorldObject user, GameWorld world) {
		if(GMouse.isButtonPressed(Controlls.FIRST_ABILITY_BUTTON))
			castTimer.reset();
		while(castTimer.hasExceeded() && use(1)){
			float xDir = GMouse.getX() - Display.getWidth()/2;
			float yDir = GMouse.getY() - Display.getHeight()/2;
			float speed = GMath.getLength(xDir, yDir)*2.3f;
			world.spawn(new CheeseProjectile(getX(),getY(),getHeadZPos(),xDir,yDir, speed));
			castTimer.resetFromEnd();
		}
		
	}

	@Override
	public void useSecondAbility(WorldObject user,GameWorld world) {
		if(GMouse.isButtonPressed(Controlls.SECOND_ABILITY_BUTTON))
		for(int i = 0; i < 40 && use(1); i++){
			float xDir = GMath.random(-1, 1);
			float yDir = GMath.random(-1, 1);
			world.spawn(new CheeseProjectile(getX(),getY(),getHeadZPos(),xDir,yDir, GMath.random(200, 500)));
			castTimer.resetFromEnd();
		}
		
	}
	
	@Override
	public void update(GameWorld world){
		if(recoverTimer.hasExceeded()){
			recover(1);
			recoverTimer.reset();
			}
	}

}
