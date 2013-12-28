package game.parts;

import game.GButton;
import static org.lwjgl.input.Keyboard.*;
import game.GSound;
import game.GSprite;
import game.GTexture;
import game.Game;
import game.GamePart;
import game.Main;
import game.input.GMouse;

import java.util.LinkedList;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.opengl.Texture;


public class StartMenu implements GamePart {
	
	GSprite background = new GSprite(Display.getWidth()/2,Display.getHeight()/2, Display.getWidth(), Display.getHeight(), 0.48f,0.69f,0.71f,0.9f);
	
	GButton startButton;
	GButton exitButton;
	Texture start1 = GTexture.getTexture("buttons/start1");
	Texture start2 = GTexture.getTexture("buttons/start2");
	Texture exit1 = GTexture.getTexture("buttons/exit1");
	Texture exit2 = GTexture.getTexture("buttons/exit2");
	public StartMenu(){
		startButton = new GButton(Display.getWidth()/2,Display.getHeight()/2-100, 300, 80);
		startButton.setTexture(start1);
		exitButton = new GButton(Display.getWidth()/2,Display.getHeight()/2+100, 300, 80);
		exitButton.setTexture(exit1);
	}
	
	
	public void handleInput() {
		if(startButton.isReleasedOver(0))
			Game.setGameState(Game.GStates.GAME);
		if(exitButton.isReleasedOver(0))
			Main.close();
		if(isKeyDown(KEY_O)){
			System.out.println("Play soundeffect");
		GSound.blueberrydeath.playAsSoundEffect(1.0f, 1.0f, false);
		SoundStore.get().poll(0);
		}
	}
	public void update() {
		//start button
		startButton.update();
		

		if(startButton.isPressed(0))
			startButton.setTexture(start2);
		else
			startButton.setTexture(start1);
		///////////////////
		
		//exit button
		exitButton.update();
		

		if(exitButton.isPressed(0))
			exitButton.setTexture(exit2);
		else
			exitButton.setTexture(exit1);
		
		////////////////

		
	}

	public void render() {
		background.render();
		startButton.render();
		exitButton.render();
	}

}

