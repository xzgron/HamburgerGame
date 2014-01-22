package world.objects;

import game.GPhysics;
import world.WorldObject;

public class GProjectile extends WorldObject {

	public GProjectile(float xPos, float yPos,float zPos, float texWidth, float texHeight, String texture,
			float footPos, float headPos) {
		super(xPos, yPos, texWidth, texHeight, "projectiles/" + texture, footPos, headPos);
		setTexFolder("projectiles/");
		setZ(zPos);
		createShadow();

	}

	public void update(){
		GPhysics.handleGravity(this);
		
		updatePrevPos();
		useSpeed();
		
		if (getZ() <= 0){
			setZ(0);
			setZSpeed(0);
			}
		
	}
}
