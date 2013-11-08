package game;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import org.lwjgl.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;

import static org.lwjgl.opengl.GL11.*;

public class Main {

	public static int window_height = 600;
	public static int window_width = 800;

	public static int wanted_fps = 60;
	public static int fps;
	public static String title = "SPEL";
	public static int fullscreenChoice=-1;

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

		long previousTime = System.nanoTime();
		long frames = 0;
		float deltaTimePerWantedFps = 0;
		
		while (!Display.isCloseRequested() && !Game.isCloseRequested()) {
			frames++;
			long currentTime = System.nanoTime();
			Game.deltaTime = (currentTime - previousTime)/1000000000.0f;
			previousTime = currentTime;
			
			deltaTimePerWantedFps += Game.deltaTime;
			if(frames%wanted_fps==1){
				fps = (int) (wanted_fps/deltaTimePerWantedFps);
				deltaTimePerWantedFps = 0;
			}
			Display.setTitle(title + "   fps: " + fps);
			
			handleInput();
			update();
			render();
		}
	}

	private static void initDisplay() {
		try {
			
			Display.setDisplayMode(new DisplayMode(window_width, window_height));
			
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
		glOrtho(0, Display.getWidth(), 0, Display.getHeight(), -1, 1);

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
}