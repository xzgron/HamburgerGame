package controllers;

import game.GMath;
import game.GPhysics;
import game.GTimer;
import game.Game;
import game.Main;
import game.parts.GameWorld;
import worldObjects.food.GFood;

public class HostileController extends GController {

	public HostileController(GFood gf) {
		super(gf);
	}

	float a = 0;
	float a2 = 0;
	float time = 0.5f;

	GTimer timer = new GTimer(time);
	
	public void handle() {
		GFood r = getReciever();
		
		

		

		if (!r.isInAir()) {
			timer.update();
		}
		

		if (timer.hasExceeded()) {
			if (!r.isInAir()) {
				float ra = (float) (Math.random()*100 +200);
				r.setZSpeed(ra);
				r.setWalkingSpeed(ra/4);
				a2 = -a2;
				a = GMath.getAngle(r,GameWorld.getPlayer()) + a2;
				getReciever().setSpeedByAngle(r.getWalkingSpeed(), a);
			}		
		}
		else
			r.stop();
		

 


		if (r.isWalking()) {
			if (r.getZ() == 0) {
				a2 = -a2;
				a = GMath.getAngle(r.getX(), r.getY(), GameWorld.getPlayer().getX(),
						GameWorld.getPlayer().getY()) + a2;
			}
			getReciever().moveByAngle(r.getWalkingSpeed() * Main.getDelta(), a);
		}
		
		
		
		
	}

}
