package controllers;

import static org.lwjgl.input.Keyboard.*;
import static org.lwjgl.input.Mouse.*;
import static game.GMath.*;
import worldObjects.food.GFood;
import game.*;

public class DefaultController extends GController {

	public DefaultController(GFood gf) {
		super(gf);
	}
	boolean jumping = false;
	public void handle() {

		GFood r = getReciever();

		
		float keyAngle = getKeyAngle();
		if (keyAngle != -1) {
			r.setSpeedByAngle(getReciever().getWalkingSpeed(),
					keyAngle);
		} else
			r.stop();
		
		if(!r.isInAir())
			jumping = false;
		
		
		if (!r.isInAir() && r.isWalking())
			r.setZSpeed(120);
		
		
		if (isKeyDown(KEY_SPACE) && !jumping) {
			jumping = true;
			r.setZSpeed(300);
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

	private float getKeyAngle() {
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

		if (x == 0 && y == 0)
			return -1;
		else
			return getAngle(0, 0, x, y);
	}

}