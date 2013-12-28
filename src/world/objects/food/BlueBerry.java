package world.objects.food;

import world.HealthBar;
import world.WorldObject;
import controllers.HostileController;
import game.*;
import game.parts.GameWorld;

public class BlueBerry extends GFood {

	HealthBar healthBar = new HealthBar(0, -30, 50, 7, 0, 1, 0, 1, this);

	public BlueBerry(float xPos, float yPos) {
		super(xPos, yPos, 40, 0.13f, 0.73f, 10, 50);
		healthBar.setFollow(true);
		createShadow();
		setTexture("Blueberrys/B" + ((int) (Math.random() * 4)));
		setController(new HostileController());

		setRadius(19);

		setWalkingSpeed(80);
		setJumpingSpeed(120);
		
		setDeathTexture("shadow");
		setDeathSound("blueBerryDeath");

	}

	public void render() {
		super.render();
		healthBar.render();
	}

	public void collidedWith(WorldObject go) {
		jump();
		setSpeedByVector(200, getX() - go.getX(), getY() - go.getY());
		
		
		if (go == GameWorld.getPlayer()){
			float dmg = GMath.getDistance(0,0,getXSpeed()+go.getXSpeed(),getYSpeed()+ go.getYSpeed())/200;
			if(dmg > 0)
				((GFood) go).damage((int) dmg);
			
			addSpeed(go.getXSpeed(), go.getYSpeed(), 0);
		}
	}

}
