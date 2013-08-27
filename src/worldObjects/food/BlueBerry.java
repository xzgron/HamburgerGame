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

		if(getZ() == 0){
			float r = (float) (Math.random()*4 +2);
			setZSpeed(r);
			setWalkingSpeed(r * 30);
		}
		GPhysics.handleGravity(this);
		
		updateShadow();
	}
	
	public void render(){
		renderShadow();
		super.render();
	}

}
