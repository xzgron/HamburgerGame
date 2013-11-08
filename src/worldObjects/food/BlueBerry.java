package worldObjects.food;

import world.WorldObject;
import controllers.HostileController;
import game.GPhysics;
import game.Game;
import game.parts.GameWorld;

public class BlueBerry extends GFood{

	public BlueBerry(float xPos, float yPos) {
		super(xPos, yPos, 40, 10,0.13f,0.73f, 50);
		createShadow();
		setTexture("blueBerry");

		setWalkingSpeed(40);

		setController(new HostileController(this));
		setRadie(19);
	}

}
