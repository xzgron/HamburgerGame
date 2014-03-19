package world;

import org.lwjgl.opengl.Display;

import world.objects.food.hostile.BlueBerry;
import world.objects.food.hostile.Carrot;
import game.GMath;
import game.parts.GameWorld;
import game.tools.GTimer;

public class WaveHandler {
	GTimer waveTime = new GTimer(1);
	int releaseCounter = 0; //-1 betyder vågen är avstängd 0 betyder ohanterad ny våg
	int currentWave = 0;

	public void nextWave() {
		currentWave++;
		releaseCounter = 0;
		waveTime.reset();
	}

	public void setWave(int wave) {
		currentWave = wave;
		releaseCounter = 0;
		waveTime.reset();
	}

	public boolean update(GameWorld world) { // returnerar om vågen
														// fortsätter att spawna
		switch (currentWave) {
		case 1:
			if(releaseCounter == 0){
				releaseCounter++;
				waveTime.setLength(20);
			}
			if (waveTime.hasExceeded()){
				return false;
			}

			if (releaseCounter == 1 && waveTime.hasExceeded(0)) {
				for (int i = 0; i < 3; i++) {
					releaseCounter++;
					float[] pos = getPosOutsideScreen(world);
					world.spawn(new BlueBerry(pos[0], pos[1], GMath.random(20, 30),world.getPlayer()));
				}
				for(int i = 0; i< 5; i++){
				float[] pos = getPosOutsideScreen(world);
				world.spawn(new Carrot(pos[0], pos[1], world.getPlayer()));
				}
			}
			break;
		case 2:

			break;
		case 3:

			break;
		case 4:

			break;
		case 5:

			break;
		case 6:

			break;
		default:
			return false;
		}
		return true;

	}

	public int getLevel() {
		return currentWave;
	}
	
	public float[] getPosOutsideScreen(GameWorld world){
		float xPos = GMath.random(-1f, 1f) * Display.getWidth() / 2 + world.getPlayer().getX();
		float yPos = GMath.random(-1f, 1f) * Display.getHeight() / 2 + world.getPlayer().getY();
		int side = (int) GMath.random(0, 4);
		switch (side) {
		case 0:
			xPos = 100 + Display.getWidth() / 2 + world.getPlayer().getX();
			break;
		case 1:
			xPos = -100 - Display.getWidth() / 2 + world.getPlayer().getX();
			break;
		case 2:
			yPos = 100 + Display.getHeight() / 2 + world.getPlayer().getY();
			break;
		case 3:
			yPos = -100 - Display.getHeight() / 2 + world.getPlayer().getY();
			break;
		}
		float[] pos = {xPos,yPos};
		return pos;
		
	}
}
