package worldObjects.food;

import input.HostileController;
import game.GWorld;
import game.GWorldObject;
import game.Game;

public class BlueBerry extends GFood{

	public BlueBerry(float xPos, float yPos) {
		super(xPos, yPos, 40, 40);
		createShadow();
		setTexture("blueBerry");
		setFootPos(0.2f);
		setWalkingSpeed(40);
		setController(new HostileController(this));
		setWalking(true);
	}

	float zSpeed = 0;
	
	public void update() {
		if(walking){			
			if(getZ() == 0)
				zSpeed = 4;
			setZ(getZ() + zSpeed);
			zSpeed -= GWorld.gravity * Game.deltaTime;
			if(getZ() < 0)
				setZ(0);
		}
		updateShadow();
	}
	
	public void render(){
		renderShadow();
		super.render();
	}

}
