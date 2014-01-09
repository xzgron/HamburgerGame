package world.objects.projectiles;

import game.GMath;
import game.Main;
import world.WorldObject;
import world.objects.GFood;
import world.objects.GProjectile;
import world.objects.HostileFood;

public class BananaProjectile extends GProjectile{


	
	public BananaProjectile(float xPos, float yPos, float zPos, float xDir, float yDir, float speed) {
		super(xPos, yPos, zPos, 70, 70, "ingredients/banana1", 0.45f, 0.55f);
		setRadius(35f);
		setWeight(9);
		setSpeedByVector(speed,xDir,yDir);
		moveByVector(10,xDir,yDir);

	}
	
	public void collidedWith(WorldObject obj){
		if(obj instanceof HostileFood){
			((GFood) obj).damage(50, this);
			}
		}
	

	public void update(){
		setZSpeed(0);
		super.update();
		if(!isInAir()){
			Main.game.world.deSpawn(this);
		}
	}

}
