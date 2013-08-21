package input;


import static java.lang.Math.*;
import static org.lwjgl.input.Keyboard.*;

import java.util.LinkedList;

import game.GObject;
import game.GStates;
import game.Game;
import game.Main;



public class Input {
	
	private LinkedList<Controller> controllers = new LinkedList<Controller>();
	
	
	public Input(){
		controllers.add(new DefaultController());
	}
	
	public void handleIntro() {
	}
	
	public void handleMenu() {
		/*if(isKeyDown(KEY_ESCAPE))
			Main.game.setGameState(GStates.GAME);*/
	}
	
	
	public void handleGame() {
		if(isKeyDown(KEY_ESCAPE))
			Main.game.setGameState(GStates.MENU);
		
		for(Controller c: controllers){
			
			c.handleInput();
		}
	}
	

	


}
