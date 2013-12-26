package controllers;

import game.GMath;
import game.GPhysics;
import game.input.GTimer;
import game.Game;
import game.Main;
import game.parts.GameWorld;
import world.objects.food.GFood;

public class HostileController extends GController {

	float a = 0;
	float a2 = 0;
	float time = 0.5f;

	GTimer timer = new GTimer(time);
	
	public void handle(GFood food) {
		
		if (timer.hasExceeded()) {
			if (!food.isInAir()) {
				float ra = (float) (Math.random()*100 +200);
				food.setZSpeed(ra);
				food.setWalkingSpeed(ra/4);
				a2 = -a2;
				a = GMath.getAngle(food,GameWorld.getPlayer()) + a2;
				food.setSpeedByAngle(food.getWalkingSpeed(), a);
			}		
		}
		else
			food.stop();
		

 


		if (food.isWalking()) {
			if (food.getZ() == 0) {
				a2 = -a2;
				a = GMath.getAngle(food.getX(), food.getY(), GameWorld.getPlayer().getX(),
						GameWorld.getPlayer().getY()) + a2;
			}
			food.moveByAngle(food.getWalkingSpeed() * Main.getDelta(), a);
		}
		
		
		
		
	}

}
