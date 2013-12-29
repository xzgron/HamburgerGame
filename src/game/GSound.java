package game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.openal.AL;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class GSound {

	private static LinkedList<Audio> sounds = new LinkedList<Audio>();


	public static Audio getAudio(String fileName){
		Audio a = null;
		try {
			a = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("resources/sounds/" + fileName + ".wav"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		for (Audio a2 : sounds)
			if (a.equals(a2))
				return a2;
		return a;
	}
}
