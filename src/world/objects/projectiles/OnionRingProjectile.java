package world.objects.projectiles;

import game.Main;
import world.WorldObject;
import world.objects.GFood;
import world.objects.GProjectile;
import world.objects.food.HostileFood;

public class OnionRingProjectile extends GProjectile{
	public OnionRingProjectile(float xPos, float yPos, float zPos, float xDir, float yDir, float speed) {
		super(xPos, yPos, zPos, 85, 85, "ingredients/onionRings", 0.42f, 0.58f);
		setRadius(42f);
		setWeight(10);
		setSpeedByVector(speed,xDir,yDir);
		moveByVector(10,xDir,yDir);

	}
	
	public void collidedWith(WorldObject obj){
		if(obj instanceof HostileFood){
			((GFood) obj).damage(50, this);
			if(!((GFood)obj).isDead()){
				Main.game.world.deSpawn(this);
			}
		}
	}
	public void landedOn(WorldObject obj){
		collidedWith(obj);
	}

	public void update(){
		super.update();
		if(!isInAir()){
			Main.game.world.deSpawn(this);
		}
	}
}
