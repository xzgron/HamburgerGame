package input;

import static org.lwjgl.input.Keyboard.*;
import static org.lwjgl.input.Mouse.*;
import static game.GPhysics.*;


import game.*;
import gameObjects.GCharacter;

public class DefaultController extends Controller {

	public DefaultController() {
		addReciever(GWorld.player);
	}
	
	private boolean jumping = false;
	private float ySpeed = 0;
	private float jumpMovement = 0;
	
	protected void handleInput() {
		for (GCharacter r : getRecievers()) {
			float keyAngle = getKeyAngle();
			if(keyAngle != -1){
				r.setWalking(true);
				r.moveByAngle(r.getSpeed()*Game.deltaTime,keyAngle);
				int rotationState = (int)((keyAngle+45/2)/360*8);
				r.setRotationState(rotationState);
			}
			else
				r.setWalking(false);
			
			
			
			if(isKeyDown(KEY_SPACE) && !jumping){
				jumping = true;
				ySpeed = 15;
			}
			if(jumping){
				r.move(0,ySpeed); 
				jumpMovement += ySpeed;
				ySpeed -= 150  * Game.deltaTime; 
				
				if(jumpMovement <= 0)
					jumping = false;

			}
			
			
			if(isKeyDown(KEY_LSHIFT))
				r.setSpeed(250);
			else
				r.setSpeed(150);
			
			
			/*
			if (isKeyDown(KEY_W) && !isKeyDown(KEY_D) && !isKeyDown(KEY_S) && !isKeyDown(KEY_A)) {
				r.moveByAngle(r.getSpeed()*Game.deltaTime,0);
			}
			else if (isKeyDown(KEY_W) && isKeyDown(KEY_D) && !isKeyDown(KEY_S) && !isKeyDown(KEY_A)) {
				r.moveByAngle(r.getSpeed()*Game.deltaTime,45);
			}
			else if (!isKeyDown(KEY_W) && isKeyDown(KEY_D) && !isKeyDown(KEY_S) && !isKeyDown(KEY_A)) {
				r.moveByAngle(r.getSpeed()*Game.deltaTime,90);
			}
			else if (!isKeyDown(KEY_W) && isKeyDown(KEY_D) && isKeyDown(KEY_S) && !isKeyDown(KEY_A)) {
				r.moveByAngle(r.getSpeed()*Game.deltaTime,135);
			}
			else if (!isKeyDown(KEY_W) && !isKeyDown(KEY_D) && isKeyDown(KEY_S) && !isKeyDown(KEY_A)) {
				r.moveByAngle(r.getSpeed()*Game.deltaTime,180);
			}
			else if (!isKeyDown(KEY_W) && !isKeyDown(KEY_D) && isKeyDown(KEY_S) && isKeyDown(KEY_A)) {
				r.moveByAngle(r.getSpeed()*Game.deltaTime,225);
			}
			else if (!isKeyDown(KEY_W) && !isKeyDown(KEY_D) && !isKeyDown(KEY_S) && isKeyDown(KEY_A)) {
				r.moveByAngle(r.getSpeed()*Game.deltaTime,270);
			}
			else if (isKeyDown(KEY_W) && !isKeyDown(KEY_D) && !isKeyDown(KEY_S) && isKeyDown(KEY_A)) {
				r.moveByAngle(r.getSpeed()*Game.deltaTime,315);
			}
*/
			//rotation
			/*
			{
				float a = getAngle(r.getX(),r.getY(),Mouse.getX(),Mouse.getY());
				int rotationState = (int)((a+45/2)/360*8);
				r.setRotationState(rotationState);
			}
			*/
		}		
	}
	
	private float getKeyAngle(){
		float x = 0;
		float y = 0;
		if(isKeyDown(KEY_W))
			y += 1;
		if(isKeyDown(KEY_D))
			x += 1;
		if(isKeyDown(KEY_S))
			y -= 1;
		if(isKeyDown(KEY_A))
			x -= 1;


		
		if(x == 0 && y == 0)
			return -1;
		else
			return getAngle(0,0,x,y);
	}

}
