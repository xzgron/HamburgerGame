package world.objects.food.hostile;

import world.Player;
import world.WorldObject;
import world.objects.GFood;
import world.objects.HostileFood;
import controllers.HostileController;
import game.*;
import game.HUDMaterial.HealthBar;
import game.parts.GameWorld;

public class BlueBerry extends HostileFood {

	HealthBar healthBar = new HealthBar(0, -30, 50, 7,  this);

	public BlueBerry(float xPos, float yPos, float size) {
		super(xPos, yPos, size, size,"Blueberrys/B" + ((int) (Math.random() * 4)), 0.13f, 0.73f, (int)(GMath.getSphereVolume(size/2)/700+1), (int)(GMath.getSphereVolume(size/2)/700+1));
		setRadius(size/2.1f);
		
		healthBar.setFollow(true);
		healthBar.setBackgroundColor(0.8f,0,0,1);
		healthBar.setColor(0, 0.8f,0,1);
		
		createShadow();
		setController(new HostileController());

		

		setWalkingSpeed(GMath.random(60, 100));
		setJumpingForce((float)Math.sqrt(getWeight())*120);
		
		setDeathTexture("shadow");
		setDeathSound("blueberry/death" + (int)GMath.random(1,4));

	}


	public void render() {
		super.render();
		if(!isDead())
			healthBar.render();
	}

	
	
	//////////ACTION////////////////////
	public void collidedWith(WorldObject go) {
		if (go instanceof Player){
			float dx = getXSpeed()-go.getXSpeed();
			float dy = getYSpeed()-go.getYSpeed();
			float dmg = (float) (GMath.getLength(dx,dy)*Math.sqrt(getWeight())/500); // genom 1000 om roten ur
			if((dx > 0) == (getXSpeed() > 0) && (dy > 0) == (getYSpeed() > 0)){ //ršr sig mot targeten
				((GFood) go).damage((int) dmg,this);
				jump();
			}
			addSpeed(go.getXSpeed(), go.getYSpeed(), 0);
		}
		

		setSpeedByVector(200, getX() - go.getX(), getY() - go.getY());
	}

}
