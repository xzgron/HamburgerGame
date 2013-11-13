package game.parts;

import game.GButton;
import game.GSprite;
import game.Game;
import game.GamePart;
import game.Main;

import java.util.LinkedList;

import org.lwjgl.opengl.Display;


public class StartMenu implements GamePart {
	
	GSprite background = new GSprite(Display.getWidth()/2,Display.getHeight()/2, 600, 480, 0.48f,0.69f,0.71f,0.9f);
	
	GButton startButton;
	GButton exitButton;
	
	public StartMenu(){
		startButton = new GButton(Display.getWidth()/2,Display.getHeight()/2-100, 300, 80);
		startButton.setTexture("startButton");
		exitButton = new GButton(Display.getWidth()/2,Display.getHeight()/2+100, 300, 80);
		exitButton.setTexture("exitButton");
	}
	
	
	public void handleInput() {
		if(startButton.isRightReleasedOver())
			Game.setGameState(Game.GStates.GAME);
		if(exitButton.isRightReleasedOver())
			Main.close();
	}
	public void update() {
		//continue button
		startButton.update();
		
		if(startButton.isRightClicked())
			startButton.setColor(255,255,255,0.7f);
		else
			startButton.setColor(255,255,255,1);
		///////////////////
		
		//exit button
		exitButton.update();
		
		if(exitButton.isRightClicked())
			exitButton.setColor(255,255,255,0.7f);
		else
			exitButton.setColor(255,255,255,1);
		
		////////////////

		
	}

	public void render() {
		background.render();
		startButton.render();
		exitButton.render();
	}

}

