package worldObjects.food;

import input.HostileController;
import game.GPhysics;
import game.GWorld;
import game.GWorldObject;
import game.Game;

public class BlueBerry extends GFood{

	public BlueBerry(float xPos, float yPos) {
		super(xPos, yPos, 40, 10,0.13f,0.73f);
		createShadow();
		setTexture("blueBerry");

		setWalkingSpeed(40);

		setController(new HostileController(this));
		setRadie(19);
	}

}
