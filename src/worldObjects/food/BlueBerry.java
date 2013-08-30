package worldObjects.food;

import input.HostileController;
import game.GPhysics;
import game.GWorld;
import game.GWorldObject;
import game.Game;

public class BlueBerry extends GFood{

	public BlueBerry(float xPos, float yPos) {
		super(xPos, yPos, 40, 40);
		createShadow();
		setTexture("blueBerry");
		setFootPos(0.2f);
		setWalkingSpeed(80);
		setController(new HostileController(this));
		setWalking(true);
	}
	

	public void update() {
		//setSize(40 + getZ()/2, 40 + getZ()/2);

		if(getZ() == 0 && isWalking()){
			float r = (float) (Math.random()*4 +2);
			setZSpeed(r);
			setWalkingSpeed(r * 40);
		}
		
		GPhysics.handleGravity(this);
		
		updateShadow();
	}
	
	public void render(){
		renderShadow();
		super.render();
	}

}
