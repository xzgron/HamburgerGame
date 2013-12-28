package game;

import java.io.IOException;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.openal.AL;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.util.ResourceLoader;

public class GSound {

	private Audio oggEffect;
	public static Audio blueberrydeath;
	private Audio aifEffect;
	private Audio oggStream;
	private Audio modStream;

	public void Sound() {
		try {

			blueberrydeath = AudioLoader
					.getAudio("WAV", ResourceLoader
							.getResourceAsStream("sounds/food/blueberry/blueberrydeath.wav"));

		} catch (IOException e) {

			e.printStackTrace();

		}
	}
}
