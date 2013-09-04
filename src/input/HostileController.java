package input;

import game.GMath;
import game.GPhysics;
import game.GTimer;
import game.GWorld;
import game.Game;
import worldObjects.food.GFood;

public class HostileController extends GController {

	public HostileController(GFood gf) {
		super(gf);
	}

	float a = 0;
	float a2 = 0;
	float time = 0.5f;

	GTimer timer = new GTimer(time);
	
	protected void handleInput() {
		GFood r = getReciever();
		
		

		

		if (r.getZ() == 0.0f) {
			timer.update();
			r.setWalking(false);
		}
		

		if (timer.exceeded()) {
			r.setWalking(true);
		}
		

 


		if (r.isWalking()) {
			if (r.getZ() == 0) {
				a2 = -a2;
				a = GMath.getAngle(r.getX(), r.getY(), GWorld.player.getX(),
						GWorld.player.getY()) + a2;
			}
			getReciever().moveByAngle(r.getWalkingSpeed() * Game.deltaTime, a);
		}
		
		
		
		
	}

}
