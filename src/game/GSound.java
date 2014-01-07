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
	private static LinkedList<String> audioLocations = new LinkedList<String>();

	public static Audio getAudio(String fileName){
		if(fileName.substring(fileName.length()-4, fileName.length()).equals("null"))
			return null;
		Audio a = null;
		
		for (int i = 0; i < sounds.size(); i++)
			if (audioLocations.get(i).equals(fileName))
				return sounds.get(i);


		try {
			a = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("resources/sounds/" + fileName + ".wav"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	


		sounds.add(a);
		audioLocations.add(fileName);
		
		
		return a;
	}
}
