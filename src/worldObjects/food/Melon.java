package worldObjects.food;

import world.WorldObject;
import controllers.HostileController;
import game.GPhysics;
import game.Game;
import game.parts.GameWorld;

public class Melon extends GFood{

	public Melon(float xPos, float yPos) {
		super(xPos, yPos, 160, 100,0.8f,3.2f);
		createShadow();
		setTexture("melon");

		setFootPos(0.2f);
		setWalkingSpeed(40);


		setWalkingSpeed(80);


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
