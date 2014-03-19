package world.objects.projectiles;

import game.Main;
import game.parts.GameWorld;
import world.WorldObject;
import world.objects.GFood;
import world.objects.GProjectile;
import world.objects.food.HostileFood;

public class OnionRingProjectile extends GProjectile{
	public OnionRingProjectile(float xPos, float yPos, float zPos, float xDir, float yDir, float speed) {
		super(xPos, yPos, zPos, 85, 42.5f,  "../ingredients/onion", 0.40f, 0.60f);
		setRadius(42f);
		setWeight(10);
		setSpeedByVector(speed,xDir,yDir);
		moveByVector(10,xDir,yDir);

	}
	@Override
	public void collidedWith(WorldObject obj,GameWorld world){
		if(obj instanceof HostileFood){
			((GFood) obj).damage(50, this,world);
			if(!((GFood)obj).isDead()){
				world.deSpawn(this);
			}
		}
	}	
	@Override
	public void landedOn(WorldObject obj,GameWorld world){
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
