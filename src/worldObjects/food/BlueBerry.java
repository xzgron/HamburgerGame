package worldObjects.food;

import input.HostileController;
import game.GPhysics;
import game.GWorld;
import game.GWorldObject;
import game.Game;

public class BlueBerry extends GFood{

	public BlueBerry(float xPos, float yPos) {
		super(xPos, yPos, 40, 0.1f,0.2f,0.8f);
		createShadow();
		setTexture("blueBerry");
<<<<<<< HEAD
		setFootPos(0.2f);
		setWalkingSpeed(40);
=======
		setWalkingSpeed(80);
>>>>>>> 9d83df220e1c9e6b6624d6d039e096ad9fb1e190
		setController(new HostileController(this));
		setRadie(19);
	}
	

	public void update() {
		//setSize(40 + getZ()/2, 40 + getZ()/2);

		
		GPhysics.handleGravity(this);
		
		updateShadow();
	}
	
	public void render(){
		renderShadow();
		super.render();
	}

}
