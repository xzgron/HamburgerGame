package game;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Font;
import java.util.LinkedList;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import game.parts.*;

public class Game {

	// //en pixel motsvarar en mm i verkligheten säger vi.

	public static enum GState {
		INTRO, START_MENU, GAME_MENU, GAME, INVENTORY_MENU, OPTIONS
	}

	private GamePart intro = new Intro();
	private GamePart startMenu = new StartMenu();

	private GameWorld world;
	private GamePart gameMenu = new GameMenu();
	
	private GamePart HUD;
	private GamePart inventoryMenu;
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
			world.handleInput();
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
			HUD.update();
			inventoryMenu.update();
			break;
		case GAME:
			world.update();
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
			world.render();
			gameMenu.render();
			break;
		case INVENTORY_MENU:
			glClearColor(1, 1, 1, 1);
			world.render();
			HUD.render();
			inventoryMenu.render();
			break;
		case GAME:
			glClearColor(1, 1, 1, 1);
			world.render();
			HUD.render();
			break;
		case OPTIONS:
			options.render();
			break;
		}
	}
	TrueTypeFont loadingInfo = new TrueTypeFont(new Font("Times New Roman", Font.BOLD, 32), false);	
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

			if(world == null){
				Main.cleanseDisplay();
				loadingInfo.drawString(Display.getWidth()/2-100, Display.getHeight()/2-200, "Loading World..",Color.black);
				Display.update();
				world = new GameWorld();
				}
			if(HUD == null){
				Main.cleanseDisplay();
				loadingInfo.drawString(Display.getWidth()/2-100, Display.getHeight()/2-200, "Loading HUD..",Color.black);
				Display.update();
				HUD = new HUD(world);
			}
			if(inventoryMenu == null){
				Main.cleanseDisplay();
				loadingInfo.drawString(Display.getWidth()/2-100, Display.getHeight()/2-200, "Loading Inventory..",Color.black);
				Display.update();
				inventoryMenu = new InventoryMenu(world);
			}
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

}
