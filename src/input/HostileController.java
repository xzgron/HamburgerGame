package input;

import game.GPhysics;
import game.GWorld;
import game.Game;
import worldObjects.food.GFood;

public class HostileController extends GController {

	public HostileController(GFood gf) {
		super(gf);
	}

float a = 0;
float a2 = 45;
	protected void handleInput() {

		if(getReciever().getZ()==0){
			a2 =  -a2;
			a = GPhysics.getAngle(getReciever().getX(), getReciever().getY(),GWorld.player.getX(), GWorld.player.getY()) + a2;
		}
		getReciever().moveByAngle(getReciever().getWalkingSpeed() * Game.deltaTime , a );
	}
	
	

}
