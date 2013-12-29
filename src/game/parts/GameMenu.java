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
	
	GButton continueButton;
	GButton exitButton;
	Texture exit1 = GTexture.getTexture("buttons/exit1");
	Texture exit2 = GTexture.getTexture("buttons/exit2");
	Texture continue1 = GTexture.getTexture("buttons/continue1");
	Texture continue2 = GTexture.getTexture("buttons/continue2");
	public GameMenu(){
		continueButton = new GButton(Display.getWidth()/2,Display.getHeight()/2-100, 300, 80);
		continueButton.setTexture(continue1);
		
		exitButton = new GButton(Display.getWidth()/2,Display.getHeight()/2+100, 300, 80);
		exitButton.setTexture(exit1);
	}
	
	
	public void handleInput() {
		if(continueButton.isReleasedOver(2))
			Game.setGameState(Game.GStates.GAME);
		if(exitButton.isReleasedOver(2))
			Main.close();
	}
	public void update() {
		//continue button
		continueButton.update();
		
		if(continueButton.isClicked(2)){
			continueButton.setTexture(continue2);
			Game.setGameState(Game.GStates.GAME);
		}
		else{
			continueButton.setTexture(continue1);
		}
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
		continueButton.render();
		exitButton.render();
	}
}

