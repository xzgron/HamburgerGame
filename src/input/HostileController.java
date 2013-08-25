package input;

import game.GPhysics;
import game.GWorld;
import game.Game;
import worldObjects.food.GFood;

public class HostileController extends GController {

	public HostileController(GFood gf) {
		super(gf);
	}


	protected void handleInput() {
		float a = GPhysics.getAngle(getReciever().getX(), getReciever().getY(),GWorld.player.getX(), GWorld.player.getY());
		getReciever().moveByAngle(getReciever().getWalkingSpeed() * Game.deltaTime, a);
	}
	
	

}
