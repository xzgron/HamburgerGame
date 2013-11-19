package game;

import static org.lwjgl.opengl.GL11.*;

import game.parts.Actionbar;
import game.parts.GameWorld;
import game.parts.GameMenu;
import game.parts.Intro;
import game.parts.Inventory;
import game.parts.StartMenu;

public class Game {

	public enum GStates {
		INTRO, START_MENU, GAME_MENU, GAME, INVENTORY_MENU
	}

	private static boolean isCloseRequested = false;

	public GamePart intro = new Intro();
	public GamePart gameWorld = new GameWorld();
	public GamePart startMenu = new StartMenu();
	public GamePart gameMenu = new GameMenu();
	public GamePart actionbar = new Actionbar();

	public static GStates gameState = GStates.START_MENU;

	public Game() {

	}

	public void handleInput() {


		switch (gameState) {
		case INTRO:
			intro.handleInput();
			break;
		case START_MENU:
			startMenu.handleInput();
			break;
		case GAME_MENU:
			gameMenu.handleInput();
			break;
		case INVENTORY_MENU:
			Inventory.handleInput();
			break;
		case GAME:
			gameWorld.handleInput();
			break;
		}
	}

	public void update() {
		switch (gameState) {
		case INTRO:
			break;
		case START_MENU:
			startMenu.update();
			break;
		case GAME_MENU:
			gameMenu.update();
			break;
		case INVENTORY_MENU:
			Inventory.update();
			break;
		case GAME:
			gameWorld.update();
			break;
		}
	}

	public void render() {
		switch (gameState) {
		case INTRO:
			break;
		case START_MENU:
			glClearColor(1, 1, 1, 1);
			startMenu.render();
			break;
		case GAME_MENU:
			glClearColor(1, 1, 1, 1);
			glPushMatrix();
			focusTarget(GameWorld.getPlayer());
			gameWorld.render();
			glPopMatrix();
			gameMenu.render();
			break;
		case INVENTORY_MENU:
			glClearColor(1, 1, 1, 1);
			glPushMatrix();
			focusTarget(GameWorld.getPlayer());
			gameWorld.render();
			glPopMatrix();
			Inventory.render();
			break;
		case GAME:
			glClearColor(1, 1, 1, 1);
			glPushMatrix();
			focusTarget(GameWorld.getPlayer());
			gameWorld.render();
			glPopMatrix();

			actionbar.render();
			break;
		}
	}

	public static void setGameState(GStates STATE) {
		gameState = STATE;
	}

	public static void close() {
		isCloseRequested = true;
	}

	public static boolean isCloseRequested() {
		return isCloseRequested;
	}

	public void focusTarget(GSprite target) {
		glTranslatef(-target.getX() + Main.window_width / 2, -target.getY()
				+ Main.window_height / 2, 0);
	}

}
