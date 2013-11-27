package worldObjects.food;

import world.WorldObject;
import controllers.HostileController;
import game.GPhysics;
import game.Game;
import game.parts.GameWorld;

public class Mushroom extends GFood{

	public Mushroom(float xPos, float yPos) {
		super(xPos, yPos, 80, 20,0.13f,0.73f, 150);
		createShadow();
		setTexture("Mushrooms/Svamp" + ((int)(Math.random()*3)));

		setWalkingSpeed(40);

		setController(new HostileController(this));
		setRadie(38);
	}

}
