package world.objects.food;

import world.HealthBar;
import world.WorldObject;
import controllers.HostileController;
import game.*;
import game.parts.GameWorld;

public class BlueBerry extends GFood {

	HealthBar healthBar = new HealthBar(0, -30, 50, 7,  this);

	public BlueBerry(float xPos, float yPos, float size) {
		super(xPos, yPos, size, size, 0.13f, 0.73f, (int)(GMath.getSphereVolume(size/2)/700+1), (int)(GMath.getSphereVolume(size/2)/700+1));
		//System.out.println((GMath.getSphereVolume(size/2)/700));
		//System.out.println(size + " " + getWeight() + " " + getMaxHealth());
		setRadius(size/2.1f);
		
		healthBar.setFollow(true);
		healthBar.setBackgroundColor(0.8f,0,0,1);
		healthBar.setColor(0, 0.8f,0,1);
		
		createShadow();
		setTexture("Blueberrys/B" + ((int) (Math.random() * 4)));
		setController(new HostileController());

		

		setWalkingSpeed(GMath.random(60, 100));
		setJumpingForce(getWeight()*120);
		
		setDeathTexture("shadow");
		setDeathSound("blueberry/death");

	}

	public void render() {
		super.render();
		if(!isDead())
			healthBar.render();
	}

	
	
	//////////ACTION////////////////////
	public void collidedWith(WorldObject go) {
		if (go == GameWorld.getPlayer()){
			float dmg = GMath.getLength(getXSpeed()-go.getXSpeed(),getYSpeed()-go.getYSpeed())*getWeight()/1500;
			if(dmg > 0)
				((GFood) go).damage((int) dmg);
			
			addSpeed(go.getXSpeed(), go.getYSpeed(), 0);
		}
		
		jump();
		setSpeedByVector(200, getX() - go.getX(), getY() - go.getY());
	}

}
