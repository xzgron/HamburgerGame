package input;


import static java.lang.Math.*;
import static org.lwjgl.input.Keyboard.*;

import java.util.LinkedList;

import game.GObject;
import game.GStates;
import game.Game;
import game.Main;



public class Input {
	
	private LinkedList<GController> controllers = new LinkedList<GController>();
	
	
	public Input(){
		//controllers.add(new HumanController());
	}
	
	public void handleIntro() {
	}
	
	public void handleMenu() {
		/*if(isKeyDown(KEY_ESCAPE))
			Main.game.setGameState(GStates.GAME);*/
	}
	
	
	public void handleGame() {
		if(isKeyDown(KEY_ESCAPE))
			Main.game.setGameState(GStates.GAMEMENU);
		
		for(GController c: controllers){
			
			c.handleInput();
		}
	}
}
