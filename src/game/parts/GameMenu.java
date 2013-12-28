package game.parts;

import game.GButton;
import game.GSprite;
import game.GTexture;
import game.Game;
import game.GamePart;
import game.Main;

import java.util.LinkedList;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;


public class GameMenu implements GamePart {
	
	GSprite background = new GSprite(Display.getWidth()/2,Display.getHeight()/2, 600, 480, "buttons/area");
	
	GButton startButton;
	GButton exitButton;
	Texture exit1 = GTexture.getTexture("buttons/exit1");
	Texture exit2 = GTexture.getTexture("buttons/exit2");
	Texture start1 = GTexture.getTexture("buttons/start1");
	Texture start2 = GTexture.getTexture("buttons/start2");
	public GameMenu(){
		startButton = new GButton(Display.getWidth()/2,Display.getHeight()/2-100, 300, 80);
		startButton.setTexture(start1);
		
		exitButton = new GButton(Display.getWidth()/2,Display.getHeight()/2+100, 300, 80);
		exitButton.setTexture(exit1);
	}
	
	
	public void handleInput() {
		if(startButton.isReleasedOver(2))
			Game.setGameState(Game.GStates.GAME);
		if(exitButton.isReleasedOver(2))
			Main.close();
	}
	public void update() {
		//start button
		startButton.update();
		
		if(startButton.isClicked(2))
			startButton.setTexture(start2);
		else
			startButton.setTexture(start1);
		///////////////////
		
		//exit button
		exitButton.update();
		
		if(exitButton.isClicked(1))
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

