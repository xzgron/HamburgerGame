package world.objects.food;

import world.objects.GFood;
import game.Main;
import game.parts.GameWorld;
import game.tools.GTimer;

public abstract class HostileFood extends GFood{

	public HostileFood(float xPos, float yPos, float texWidth, float texHeight, String texture,
			float footPos, float headPos, int weight, int health) {
		super(xPos, yPos, texWidth, texHeight,texture, footPos, headPos, weight, health);
	}

	GTimer deathTimer = new GTimer(-1);
	float deathTime = 10f;
	
	@Override
	public void update(GameWorld world){
		super.update(world);
		if(isDead() && deathTimer.hasExceeded()){
			world.deSpawn(this);
		}	
		
	}
	
	@Override
	public void die(GameWorld world){
		super.die(world);
		deathTimer.setLength(deathTime);
		deathTimer.reset();
	}
}
