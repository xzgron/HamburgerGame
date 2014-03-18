package game;

import org.newdawn.slick.openal.Audio;

public class GAudio {

	
	public static boolean soundOn = true;
	
	private Audio audio;
	
	float volume = 1;
	
	float gain = 1;
	float pitch = 1;

	public GAudio(String fileName){
		audio = GSound.getAudio(fileName);
	}
	
	public void play(){
		//audio.playAsSoundEffect(arg0, arg1, arg2, arg3, arg4, arg5);
	}


}
