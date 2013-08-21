package game;

import static org.lwjgl.opengl.GL11.*;
import menu.GMenu;
import input.Input;

public class Game {
	public static boolean isCloseRequest = false;
	
	public Input input;
	public GWorld world;
	public GMenu menu;


	public GStates gameState = GStates.MENU;

	public static float deltaTime;

	public Game() {
		input = new Input();
		menu = new GMenu();
		world = new GWorld();
		
	}

	public void handleInput() {
		switch (gameState) {
		case INTRO:
			input.handleIntro();
			break;
		case MENU:
			input.handleMenu();
			break;
		case GAME:
			input.handleGame();
			break;
		}
	}

	public void update() {
		switch (gameState) {
		case INTRO:
			break;
		case MENU:
			menu.update();
			break;
		case GAME:
			world.update();
			break;
		}
	}

	public void render() {
		switch (gameState) {
		case INTRO:
			break;
		case MENU:
			menu.render();
			break;
		case GAME:
			glClearColor(1,1,1,1);
			focusTarget(world.player);
			world.render();
			break;
		}
	}
	
	public void setGameState(GStates STATE){
		gameState = STATE;
	}
	
	public static void close(){
		isCloseRequest = true;
	}
	public static boolean isCloseRequested(){
		return isCloseRequest;
	}
	public void focusTarget(GObject target){
		glTranslatef(-target.getX()+Main.window_width/2, -target.getY()+Main.window_height/2, 0);
	}
	
}
