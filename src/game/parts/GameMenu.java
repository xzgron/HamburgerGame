package game.parts;

import game.GButton;
import game.GSprite;
import game.Game;
import game.GamePart;
import game.Main;

import java.util.LinkedList;

import org.lwjgl.opengl.Display;


public class GameMenu implements GamePart{
	
	GSprite background = new GSprite(Display.getWidth()/2,Display.getHeight()/2, 600, 480, 0.48f,0.69f,0.71f,0.9f);
	
	GButton continueButton;
	GButton exitButton;
	
	public GameMenu(){
		continueButton = new GButton(Display.getWidth()/2,Display.getHeight()/2-100, 300, 80);
		continueButton.setTexture("continueButton");
		exitButton = new GButton(Display.getWidth()/2,Display.getHeight()/2+100, 300, 80);
		exitButton.setTexture("exitButton");
	}
	
	
	public void handleInput() {
		if(continueButton.isRightReleasedOver())
			Game.setGameState(Game.GStates.GAME);
		if(exitButton.isRightReleasedOver())
			Main.close();
	}
	public void update() {
		//continue button
		continueButton.update();
		
		if(continueButton.isRightClicked())
			continueButton.setColor(255,255,255,0.7f);
		else
			continueButton.setColor(255,255,255,1);
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
		continueButton.render();
		exitButton.render();
	}




}
