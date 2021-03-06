package game;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Font;
import java.util.LinkedList;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import game.parts.*;

public class Game {

	// //en pixel motsvarar en mm i verkligheten s�ger vi.
	
	// h�rifr�n sk�ts hela spelet. Main klassen tar hand om det som m�ste g�ras likt att synca fps:en eller uppdatera sk�rmen.
	// h�rifr�n v�ljs allt som ska finnas i spelet, likt menu och ladningssk�rm etc. 
	// i parts mappen finns alla klasser som �r delar av spelet likt menu och intro etc. Se GameWorld classen i parts mappen f�r mer info om sj�lva spelet
	public static enum GState {
		INTRO, START_MENU, GAME_MENU, GAME, INVENTORY_MENU, OPTIONS, DEATH_SCREEN
	}

	private GamePart intro = new Intro();
	private GamePart startMenu = new StartMenu();

	private GameWorld world;
	private GamePart gameMenu = new GameMenu();
	
	private GamePart HUD;
	private GamePart inventoryMenu;
	private GamePart options = new Options();
	
	private GamePart deathScreen = new DeathScreen();

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
		case DEATH_SCREEN:
			deathScreen.handleInput();
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
		case DEATH_SCREEN:
			world.update();
			deathScreen.update();
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
		case DEATH_SCREEN:
			world.render();
			deathScreen.render();
			break;
		case OPTIONS:
			options.render();
			break;
		}
	}
	TrueTypeFont loadingInfo = new TrueTypeFont(new Font("Times New Roman", Font.BOLD, 32), false);	
	public void setGameState(GState state) {
		switch (state) {
		case INTRO:
			break;
		case START_MENU:
			if(gameStateList.getLast() == GState.DEATH_SCREEN)
				world = null;
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
		case DEATH_SCREEN:
			break;
		case OPTIONS:
			break;
		}
		gameStateList.add(state);
	}
	
	public GState getGameState() {
		return gameStateList.getLast();
	}

	public void revertGameState() {
		gameStateList.removeLast();
	}

}
