package world.objects.projectiles;

import game.GMath;
import game.Main;
import game.parts.GameWorld;
import world.WorldObject;
import world.objects.GFood;
import world.objects.GProjectile;
import world.objects.food.HostileFood;

public class CheeseProjectile extends GProjectile{


	
	public CheeseProjectile(float xPos, float yPos, float zPos, float xDir, float yDir, float speed) {
		super(xPos, yPos, zPos,20, 20,  "cheeseball", 0.35f, 0.65f);
		setRadius(20);
		setWeight(5);
		setSpeedByVector(speed,xDir,yDir);
		moveByVector(30,xDir,yDir);

	}
	
	@Override
	public void collidedWith(WorldObject obj, GameWorld world){
		if(obj instanceof HostileFood){
			((GFood) obj).damage(25, this,world);
		}
		if(obj!=world.getPlayer() && !(obj instanceof GProjectile))
			world.deSpawn(this);
	}
	
	@Override
	public void landedOn(WorldObject obj, GameWorld world){
		collidedWith(obj,world);
	}

	@Override
	public void update(GameWorld world){
		super.update(world);
		if(!isInAir()){
			world.deSpawn(this);
		}
	}

}
