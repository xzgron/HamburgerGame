package game.parts;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.opengl.Texture;

import game.GImage;
import game.GSound;
import game.GTexture;
import game.Game;
import game.GamePart;
import game.Main;
import game.input.GTimer;

public class Intro extends GamePart {

	GTimer introTime = new GTimer(7.0f);
	Audio introSound  = GSound.getAudio("introSound");
	Texture symbol = GTexture.getTexture("food/blueberry");
	public Intro() {
		introSound.playAsMusic(1, 1, false);
		Main.cleanseDisplay();
		GImage.draw(symbol, Display.getWidth()/2, Display.getHeight()/2, 300,300);
		Display.update();
	}
	
	public void handleInput() {
	}

	
	public void update() {
		if(introTime.hasExceeded())
			Main.game.setGameState(Game.GState.START_MENU);
		
		
	}

	public void render() {
		GImage.draw(symbol, Display.getWidth()/2, Display.getHeight()/2, 300,300);
	}

}
