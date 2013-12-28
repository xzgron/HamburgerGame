package controllers;

import game.GMath;
import game.GPhysics;
import game.input.GTimer;
import game.Game;
import game.Main;
import game.parts.GameWorld;
import world.objects.food.GFood;

public class HostileController extends GController {

	GTimer waitTimer;
	public HostileController(){
		waitTimer = new GTimer(0.3f);
	}
	
	public void handle(GFood food) {
		
		if (food.justLanded()) {
			waitTimer.reset();
		}

		if(!food.isInAir())
			food.stop();
		
		if (waitTimer.hasExceeded()) {
			float dx = GameWorld.getPlayer().getX() - food.getX();
			float dy = GameWorld.getPlayer().getGroundYPos() - food.getGroundYPos();
			
			float ovalDistance = GMath.getLength(dx,dy*2);
			
			dx = dx/ovalDistance * GameWorld.getPlayer().getRadius();
			dy = dy/ovalDistance * GameWorld.getPlayer().getRadius();
			float r = GMath.getLength(dx,dy);
			
			if (GMath.getDistance(GameWorld.getPlayer(), food) < r +100 && !GPhysics.objectsOverlapp(GameWorld.getPlayer(), food)) {
				if (!food.isInAir()) {
					food.setSpeedByVector(250, GameWorld.getPlayer().getX() - food.getX(), GameWorld.getPlayer().getY() - food.getY());
					food.setZSpeed(170);
				}
			} else {
				food.tryGroundWalk(GameWorld.getPlayer().getX() - food.getX(), GameWorld.getPlayer().getY() - food.getY());
				food.tryJump();
			}
		}
	}
}
