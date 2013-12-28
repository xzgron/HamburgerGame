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
	private Audio wavEffect;
	private Audio aifEffect;
	private Audio oggStream;
	private Audio modStream;

	public void Sound() {
		try {

			oggEffect = AudioLoader.getAudio("OGG",
					ResourceLoader.getResourceAsStream("testdata/restart.ogg"));

			oggStream = AudioLoader.getStreamingAudio("OGG",
					ResourceLoader.getResource("testdata/bongos.ogg"));

			modStream = AudioLoader.getStreamingAudio("MOD",
					ResourceLoader.getResource("testdata/SMB-X.XM"));

			modStream.playAsMusic(1.0f, 1.0f, true);

			aifEffect = AudioLoader.getAudio("AIF",
					ResourceLoader.getResourceAsStream("testdata/burp.aif"));
			wavEffect = AudioLoader
					.getAudio("WAV", ResourceLoader
							.getResourceAsStream("testdata/cbrown01.wav"));

		} catch (IOException e) {

			e.printStackTrace();

		}
	}
}
