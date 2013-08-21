package game;

import static org.lwjgl.opengl.GL11.*;
import menu.GMenu;
import input.Input;

public class Game {
	public static boolean isCloseRequest = false;
	
	public Input input;
	public GWorld world;
	public GMenu menu;
	public GObject focusedTarget = world.player;

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
			paintBackground(255, 255, 255);
			focusTarget();
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
	public static boolean isCloseRequest(){
		return isCloseRequest;
	}
	public void focusTarget(){
		if(focusedTarget == null)
			return;
		glTranslatef(-focusedTarget.getX()+Main.window_width/2, -focusedTarget.getY()+Main.window_height/2, 0);
	}
	
	public void paintBackground(float red, float green, float blue) {
		glBindTexture(GL_TEXTURE_2D,0);
		glColor4f(red / 255, green / 255, blue / 255, 1);
		glBegin(GL_QUADS);
		{
			glVertex2f(0, Main.window_height);

			glVertex2f(Main.window_width, Main.window_height);

			glVertex2f(Main.window_width, 0);

			glVertex2f(0, 0);
		}
		glEnd();
	}
}
