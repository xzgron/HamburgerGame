package controllers;

import game.GMath;
import game.GPhysics;
import game.input.GTimer;
import game.Game;
import game.Main;
import game.parts.GameWorld;
import world.objects.GFood;

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
			float dx = Main.game.world.getPlayer().getX() - food.getX();
			float dy = Main.game.world.getPlayer().getGroundYPos() - food.getGroundYPos();
			
			float ovalDistance = GMath.getLength(dx,dy*2);
			
			dx = dx/ovalDistance * Main.game.world.getPlayer().getRadius();
			dy = dy/ovalDistance * Main.game.world.getPlayer().getRadius();
			float r = GMath.getLength(dx,dy);
			
			if (GMath.getDistance(Main.game.world.getPlayer(), food) < r + 100 /*&& !GPhysics.objectsOverlapp(Main.game.world.getPlayer(), food)*/) {
				if (!food.isInAir()) {
					food.setSpeedByVector(250, Main.game.world.getPlayer().getX() - food.getX(), Main.game.world.getPlayer().getY() - food.getY());
					food.setZSpeed(170);
				}
			} else {
				food.tryGroundWalk(Main.game.world.getPlayer().getX() - food.getX(), Main.game.world.getPlayer().getY() - food.getY());
				food.tryJump();
			}
		}
	}
}
