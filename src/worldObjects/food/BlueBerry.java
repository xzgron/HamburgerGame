package worldObjects.food;

import input.HostileController;
import game.GPhysics;
import game.GWorld;
import game.GWorldObject;
import game.Game;

public class BlueBerry extends GFood{

	public BlueBerry(float xPos, float yPos) {
		super(xPos, yPos, 40, 10,0.2f,0.8f);
		createShadow();
		setTexture("blueBerry");

		setFootPos(0.2f);
		setWalkingSpeed(40);

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
