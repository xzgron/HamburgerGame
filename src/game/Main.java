package game;

import game.tools.GKeyboard;
import game.tools.GMouse;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import org.lwjgl.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;
import org.newdawn.slick.openal.SoundStore;

import static org.lwjgl.opengl.GL11.*;

public class Main {

	private static boolean isCloseRequested = false;

	static String title = "Game";

	static int window_height = 600;
	static int window_width = 900;

	static int fps;
	static int wanted_fps = 60;

	static long time = System.nanoTime();
	static float delta_time;

	public static Game game;

	public static void main(String[] arg) throws Exception {
		LibraryLoader.loadNativeLibraries();

		initDisplay();
		initInputTools();
		initGL();
		
		initGame();
		
		
		gameLoop();

		cleanUp();
	}

	private static void initGame() {
		game = new Game();
	}
	

	private static void handleInput() {
		game.handleInput();
	}

	private static void update() {
		game.update();
	}

	private static void render() {
		glClear(GL_COLOR_BUFFER_BIT);
		glLoadIdentity();
		glClearColor(1,1,1,1);
		game.render();
		Display.update();
		Display.sync(wanted_fps);
	}

	public static void cleanseDisplay(){
		glClear(GL_COLOR_BUFFER_BIT);
		glLoadIdentity();
		glClearColor(1,1,1,1);
		Display.update();
	}
	
	//detta �r lopen som g�r om och om igen under spelets g�ng och uppdaterar sk�rmen. Den leder vidare till Game classen

	private static void gameLoop() {
		long frames = 0;
		float deltaTimePerWantedFps = 0;
		float unprossesedSeconds = 0;

		while (!Display.isCloseRequested() && !isCloseRequested) {
			frames++;

			// /////////////UPPDATERA TID///////////
			long newTime = System.nanoTime();
			delta_time = (newTime - time) / 1000000000.0f;
			unprossesedSeconds += delta_time;
			time = newTime;
			// //////////////////////
			
			// ////////////R�KNA UT FPS//////////
			deltaTimePerWantedFps += delta_time;
			if (frames % wanted_fps == 0) {
				fps = Math.round(wanted_fps / deltaTimePerWantedFps);
				deltaTimePerWantedFps = 0;
			}
			// //////////////////////////
				
	
			// ///UPPDATERA TITEL/////////
			Display.setTitle(title + "   fps: " + fps);
			// ///////////////////////////

			// //HANTERA SPELET////////////
			while (unprossesedSeconds > getDelta()) {
				handleInput();
				update();
				// ////UPPDATERA INPUT CLASSER////
				GKeyboard.update();
				GMouse.update();
				unprossesedSeconds -= getDelta();
				}
			// /////////////////
			
			render();
			// /////////////////////////////

		}
	}

	private static void initDisplay() {
		try {
			String[] displayOptions = {"Fullscreen", "800x600", "1200x720"};
			int i = JOptionPane.showOptionDialog(null, "", "Display thing", 0, 0, null, displayOptions, -1);
			switch(i){
			case -1:
				System.exit(0);
			case 0:
				Display.setFullscreen(true);
				break;
			case 1:
				window_width = 800;
				window_height = 600;
				Display.setDisplayMode(new DisplayMode(window_width, window_height));
				break;
			case 2:
				window_width = 1200;
				window_height = 720;
				Display.setDisplayMode(new DisplayMode(window_width, window_height));
				break;
			}
			Display.setResizable(false);
			Display.create();
			Display.setVSyncEnabled(true);
		} catch (LWJGLException e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	private static void initInputTools() {
		try {
			Keyboard.create();
			Mouse.create();
		} catch (LWJGLException e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}

	}

	private static void initGL() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Display.getWidth(), Display.getHeight(), 0, -1, 1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_BLEND);
		glEnable(GL_TEXTURE_2D);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glDisable(GL_DEPTH_TEST);
	}

	private static void cleanUp() {
		Display.destroy();
		Keyboard.destroy();
		Mouse.destroy();
		System.exit(0);
	}

	public static long getTime() {
		return time;
	}

	public static float getDelta() {
		return 1 / 60f;
	}

	public static void close() {
		isCloseRequested = true;
	}

}