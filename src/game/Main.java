package game;

import game.input.GKeyboard;
import game.input.GMouse;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;

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
		game.render();
		Display.update();
		Display.sync(wanted_fps);
	}

	private static void gameLoop() {
		long frames = 0;
		float deltaTimePerWantedFps = 0;

		while (!Display.isCloseRequested() && !isCloseRequested) {
			frames++;
			
			///////////////UPPDATERA TID///////////
			long newTime = System.nanoTime();
			delta_time = (newTime - time)/1000000000.0f;
			time = newTime;
			delta_time = 1/60f;
			////////////////////////
			
			//////////////R�KNA UT FPS//////////
			deltaTimePerWantedFps += delta_time;
			if(frames%wanted_fps==0){
				fps = Math.round(wanted_fps/deltaTimePerWantedFps);
				deltaTimePerWantedFps = 0;
			}
			////////////////////////////
			
			/////UPPDATERA TITEL/////////
			Display.setTitle(title + "   fps: " + fps);
			/////////////////////////////
			
			
	
			
			////HANTERA SPELET////////////
			handleInput();
			update();
			//////UPPDATERA INPUT CLASSER////
			GKeyboard.update();
			GMouse.update();
			///////////////////
			render();
			///////////////////////////////

		}
	}

	private static void initDisplay() {
		try {
			Display.setDisplayMode(new DisplayMode(window_width, window_height));
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
		glOrtho(0, Display.getWidth(), Display.getHeight(),0, -1, 1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_BLEND);
		glEnable(GL_TEXTURE_2D);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		/*glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_MODULATE);
		glColor4f(1,1,1,1);*/
		glClearColor(0, 0, 0, 1);
		glDisable(GL_DEPTH_TEST);
	}

	private static void cleanUp() {
		Display.destroy();
		Keyboard.destroy();
		Mouse.destroy();
	}
	
	public static long getTime(){
		return time;
	}
	
	public static float getDelta(){
		return delta_time;
	}
	
	public static void close(){
		isCloseRequested = true;
	}
}