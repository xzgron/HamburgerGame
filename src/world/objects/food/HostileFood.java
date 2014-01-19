package world.objects.food;

import world.objects.GFood;
import game.Main;
import game.tools.GTimer;

public abstract class HostileFood extends GFood{

	public HostileFood(float xPos, float yPos, float texWidth, float texHeight, String texture,
			float footPos, float headPos, int weight, int health) {
		super(xPos, yPos, texWidth, texHeight,texture, footPos, headPos, weight, health);
	}

	GTimer deathTimer = new GTimer(-1);
	float deathTime = 10f;
	
	public void update(){
		super.update();
		if(isDead() && deathTimer.hasExceeded()){
			Main.game.world.deSpawn(this);
		}	
		
	}
	
	public void die(){
		super.die();
		deathTimer.setLength(deathTime);
		deathTimer.reset();
	}
}
