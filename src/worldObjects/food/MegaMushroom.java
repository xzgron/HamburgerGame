package worldObjects.food;

import world.WorldObject;
import controllers.HostileController;
import game.GPhysics;
import game.Game;
import game.parts.GameWorld;

public class MegaMushroom extends GFood{

	public MegaMushroom(float xPos, float yPos) {
		super(xPos, yPos, 160, 200,0.13f,0.73f, 1480);
		createShadow();
		setTexture("SMushrooms/SSvamp" + ((int)(Math.random()*2)));

		setWalkingSpeed(40);

		setController(new HostileController(this));
		setRadie(76);
	}

}
