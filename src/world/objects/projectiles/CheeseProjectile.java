package world.objects.projectiles;

import game.GMath;
import game.Main;
import world.WorldObject;
import world.objects.GFood;
import world.objects.GProjectile;
import world.objects.HostileFood;

public class CheeseProjectile extends GProjectile{


	
	public CheeseProjectile(float xPos, float yPos, float zPos, float xDir, float yDir, float speed) {
		super(xPos, yPos, zPos, 70, 70, "ingredients/Cheese", 0.45f, 0.55f);
		setRadius(35);
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
