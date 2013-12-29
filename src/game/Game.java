package game;

import static org.lwjgl.opengl.GL11.*;

import java.util.LinkedList;

import game.parts.Actionbar;
import game.parts.GameWorld;
import game.parts.GameMenu;
import game.parts.Intro;
import game.parts.Inventory;
import game.parts.Options;
import game.parts.StartMenu;

public class Game {

	// //en pixel motsvarar en mm i verkligheten s�ger vi.

	public static enum GState {
		INTRO, START_MENU, GAME_MENU, GAME, INVENTORY_MENU, OPTIONS
	}

	private GamePart intro = new Intro();
	private GamePart gameWorld = new GameWorld();
	private GamePart startMenu = new StartMenu();
	private GamePart gameMenu = new GameMenu();
	private GamePart actionbar = new Actionbar();
	private GamePart inventory = new Inventory();
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
			inventory.handleInput();
			break;
		case GAME:
			gameWorld.handleInput();
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
			inventory.update();
			break;
		case GAME:
			gameWorld.update();
			break;
		case OPTIONS:
			options.update();
			break;
		}
	}

	public void render() {
		switch (gameStateList.getLast()) {
		case INTRO:
			intro.render();
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
			inventory.render();
			break;
		case GAME:
			glClearColor(1, 1, 1, 1);
			glPushMatrix();
			focusTarget(GameWorld.getPlayer());
			gameWorld.render();
			glPopMatrix();

			actionbar.render();
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

	public void focusTarget(GSprite target) {
		glTranslatef(-target.getX() + Main.window_width / 2, -target.getY()
				+ Main.window_height / 2, 0);
	}

}
