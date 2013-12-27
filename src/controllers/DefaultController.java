package controllers;

import static org.lwjgl.input.Keyboard.*;
import static org.lwjgl.input.Mouse.*;
import static game.GMath.*;
import world.objects.food.GFood;
import game.*;

public class DefaultController extends GController {

	boolean jumping = false;
	
	public void handle(GFood food) {
		float x = 0;
		float y = 0;
		if (isKeyDown(KEY_W) || isKeyDown(KEY_UP))
			y -= 1;
		if (isKeyDown(KEY_D) || isKeyDown(KEY_RIGHT))
			x += 1;
		if (isKeyDown(KEY_S) || isKeyDown(KEY_DOWN))
			y += 1;
		if (isKeyDown(KEY_A) || isKeyDown(KEY_LEFT))
			x -= 1;
		
		if(x == 0 && y == 0)
			food.stop();
		else
			food.walk(x,y);
		
		if(!food.isInAir())
			jumping = false;
		
		
		if (!food.isInAir() && food.isWalking())
			food.setZSpeed(120);
		
		
		if (isKeyDown(KEY_SPACE) && !jumping) {
			jumping = true;
			food.jump();
		}

		/*
		 * 
		 * if(isKeyDown(KEY_LSHIFT)) getReciever().setSpeed(250); else
		 * getReciever().setSpeed(150);
		 * 
		 * 
		 * /* if (isKeyDown(KEY_W) && !isKeyDown(KEY_D) && !isKeyDown(KEY_S) &&
		 * !isKeyDown(KEY_A)) {
		 * getReciever().moveByAngle(getReciever().getSpeed()*Game.deltaTime,0);
		 * } else if (isKeyDown(KEY_W) && isKeyDown(KEY_D) && !isKeyDown(KEY_S)
		 * && !isKeyDown(KEY_A)) {
		 * getReciever().moveByAngle(getReciever().getSpeed
		 * ()*Game.deltaTime,45); } else if (!isKeyDown(KEY_W) &&
		 * isKeyDown(KEY_D) && !isKeyDown(KEY_S) && !isKeyDown(KEY_A)) {
		 * getReciever
		 * ().moveByAngle(getReciever().getSpeed()*Game.deltaTime,90); } else if
		 * (!isKeyDown(KEY_W) && isKeyDown(KEY_D) && isKeyDown(KEY_S) &&
		 * !isKeyDown(KEY_A)) {
		 * getReciever().moveByAngle(getReciever().getSpeed(
		 * )*Game.deltaTime,135); } else if (!isKeyDown(KEY_W) &&
		 * !isKeyDown(KEY_D) && isKeyDown(KEY_S) && !isKeyDown(KEY_A)) {
		 * getReciever
		 * ().moveByAngle(getReciever().getSpeed()*Game.deltaTime,180); } else
		 * if (!isKeyDown(KEY_W) && !isKeyDown(KEY_D) && isKeyDown(KEY_S) &&
		 * isKeyDown(KEY_A)) {
		 * getReciever().moveByAngle(getReciever().getSpeed()
		 * *Game.deltaTime,225); } else if (!isKeyDown(KEY_W) &&
		 * !isKeyDown(KEY_D) && !isKeyDown(KEY_S) && isKeyDown(KEY_A)) {
		 * getReciever
		 * ().moveByAngle(getReciever().getSpeed()*Game.deltaTime,270); } else
		 * if (isKeyDown(KEY_W) && !isKeyDown(KEY_D) && !isKeyDown(KEY_S) &&
		 * isKeyDown(KEY_A)) {
		 * getReciever().moveByAngle(getReciever().getSpeed()
		 * *Game.deltaTime,315); }
		 */
		// rotation
		/*
		 * { float a =
		 * getAngle(getReciever().getX(),getReciever().getY(),Mouse.getX
		 * (),Mouse.getY()); int rotationState = (int)((a+45/2)/360*8);
		 * getReciever().setRotationState(rotationState); }
		 */
	}

}
