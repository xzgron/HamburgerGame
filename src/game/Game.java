package game;

import static org.lwjgl.opengl.GL11.*;
import menu.GameMenu;
import menu.StartMenu;
import input.Input;

public class Game {
	public static boolean isCloseRequest = false;
	
	public Input input;
	public GWorld world;
	public StartMenu startMenu;
	public GameMenu gameMenu;


	public GStates gameState = GStates.STARTMENU;

	public static float deltaTime;

	public Game() {
		input = new Input();
		startMenu = new StartMenu();
		gameMenu = new GameMenu();
		world = new GWorld();
		
	}

	public void handleInput() {
		switch (gameState) {
		case INTRO:
			input.handleIntro();
			break;
		case STARTMENU:
			input.handleMenu();
			break;
		case GAMEMENU:
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
		case STARTMENU:
			startMenu.update();
			break;
		case GAMEMENU:
			gameMenu.update();
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
		case STARTMENU:
			glClearColor(0,0,0,1);
			startMenu.render();
			break;
		case GAMEMENU:
			glClearColor(0,0,0,1);
			gameMenu.render();
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
