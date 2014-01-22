package world.objects.projectiles;

import game.GMath;
import game.Main;
import world.WorldObject;
import world.objects.GFood;
import world.objects.GProjectile;
import world.objects.food.HostileFood;

public class CheeseProjectile extends GProjectile{


	
	public CheeseProjectile(float xPos, float yPos, float zPos, float xDir, float yDir, float speed) {
		super(xPos, yPos, zPos,40, 20,  "cheeseBullet", 0.35f, 0.65f);
		setRadius(20);
		setWeight(5);
		setSpeedByVector(speed,xDir,yDir);
		moveByVector(30,xDir,yDir);

	}
	
	public void collidedWith(WorldObject obj){
		if(obj instanceof HostileFood){
			((GFood) obj).damage(25, this);
		}
		if(obj!=Main.game.world.getPlayer() && !(obj instanceof GProjectile))
			Main.game.world.deSpawn(this);
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
