package game;

import static org.lwjgl.opengl.GL11.*;

import java.util.LinkedList;

import org.lwjgl.opengl.Display;

import game.parts.*;

public class Game {

	// //en pixel motsvarar en mm i verkligheten säger vi.

	public static enum GState {
		INTRO, START_MENU, GAME_MENU, GAME, INVENTORY_MENU, OPTIONS
	}

	private GamePart intro = new Intro();
	private GamePart startMenu = new StartMenu();

	private GamePart gameWorld = new GameWorld();
	private GamePart gameMenu = new GameMenu();
	
	private GamePart HUD = new HUD();
	private GamePart inventoryMenu = new InventoryMenu();
	private GamePart options = new Options();

	private LinkedList<GState> gameStateList = new LinkedList<GState>();

	public Game() {
		setGameState(GState.INTRO);
		
	}

	public void handleInput() {
		switch (gameStateList.getLast()) {
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
			inventoryMenu.handleInput();
			break;
		case GAME:
			gameWorld.handleInput();
			HUD.handleInput();
			break;
		case OPTIONS:
			options.handleInput();
			break;
		}
	}

	public void update() {
		switch (gameStateList.getLast()) {
		case INTRO:
			intro.update();
			break;
		case START_MENU:
			startMenu.update();
			break;
		case GAME_MENU:
			gameMenu.update();
			break;
		case INVENTORY_MENU:
			inventoryMenu.update();
			break;
		case GAME:
			gameWorld.update();
			HUD.update();
			break;
		case OPTIONS:
			options.update();
			break;
		}
	}

	public void render() {
		switch (gameStateList.getLast()) {
		case INTRO:
			glClearColor(1, 1, 1, 1);
			intro.render();
			break;
		case START_MENU:
			glClearColor(1, 1, 1, 1);
			startMenu.render();
			break;
		case GAME_MENU:
			glClearColor(1, 1, 1, 1);
			gameWorld.render();
			gameMenu.render();
			break;
		case INVENTORY_MENU:
			glClearColor(1, 1, 1, 1);
			gameWorld.render();
			HUD.render();
			inventoryMenu.render();
			break;
		case GAME:
			glClearColor(1, 1, 1, 1);
			gameWorld.render();
			HUD.render();
			break;
		case OPTIONS:
			options.render();
			break;
		}
	}

	public void setGameState(GState STATE) {
		switch (STATE) {
		case INTRO:
			break;
		case START_MENU:
			break;
		case GAME_MENU:
			break;
		case INVENTORY_MENU:
			break;
		case GAME:
			break;
		case OPTIONS:
			break;
		}
		gameStateList.add(STATE);
	}
	
	public GState getGameState() {
		return gameStateList.getLast();
	}

	public void revertGameState() {
		gameStateList.removeLast();
	}

	public static void focusPoint(float x, float y) {
		glTranslatef(-x + Main.window_width / 2, -y + Main.window_height / 2, 0);
	}

}
