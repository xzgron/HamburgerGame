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
	
	GButton startButton = new GButton(Display.getWidth()/2,Display.getHeight()/2-100, 300, 80);
	GButton optionButton = new GButton(Display.getWidth()/2,Display.getHeight()/2, 300, 80);
	GButton exitButton = new GButton(Display.getWidth()/2,Display.getHeight()/2+100, 300, 80);
	
	Texture startNormal = GTexture.getTexture("buttons/startNormal");
	Texture startClicked = GTexture.getTexture("buttons/startClicked");
	
	Texture optionNormal = GTexture.getTexture("buttons/optionNormal");
	Texture optionClicked = GTexture.getTexture("buttons/optionClicked");
	
	Texture exitNormal = GTexture.getTexture("buttons/exitNormal");
	Texture exitClicked = GTexture.getTexture("buttons/exitClicked");
	public StartMenu(){
		startButton.setTexture(startNormal);
		exitButton.setTexture(exitNormal);
	}
	
	
	public void handleInput() {
		/////START BUTTON//////
		if(startButton.isReleasedOver(0))
			Main.game.setGameState(Game.GState.GAME);
		
		if(startButton.isHeldIn(0))
			startButton.setTexture(startClicked);
		else
			startButton.setTexture(startNormal);
		
		/////OPTION BUTTON//////
		if(optionButton.isReleasedOver(0))
			Main.game.setGameState(Game.GState.OPTIONS);
		
		if(optionButton.isHeldIn(0))
			optionButton.setTexture(optionClicked);
		else
			optionButton.setTexture(optionNormal);
		
		/////EXIT BUTTON/////
		if(exitButton.isReleasedOver(0))
			Main.close();
		
		if(exitButton.isHeldIn(0))
			exitButton.setTexture(exitClicked);
		else
			exitButton.setTexture(exitNormal);
	}
	public void update() {
		startButton.update();
		optionButton.update();
		exitButton.update();
	}

	public void render() {
		background.render();
		startButton.render();
		optionButton.render();
		exitButton.render();
	}

}

