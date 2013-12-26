package game.input;

import static org.lwjgl.input.Keyboard.*;

import java.util.LinkedList;

import org.lwjgl.input.Keyboard;

public class GKeyboard {
	private static boolean[] wasDown = new boolean[256];
	
	public static void update(){
		for(int i = 0; i < wasDown.length; i++)
			wasDown[i] = isKeyDown(i);
	}
	
	public static boolean isKeyPressed(int key){
		return (isKeyDown(key) && !wasKeyDown(key));
	}
	
	public static boolean isKeyReleased(int key){
		return (!isKeyDown(key) && wasKeyDown(key));
	}
	
	public static boolean wasKeyDown(int key){
		return wasDown[key];
	}
	
	public static int[] getPressedKeys(){
		LinkedList<Integer> pressedKeys = new LinkedList<Integer>();
		for(int i = 0; i < wasDown.length; i++)
			if(wasDown[i])
				pressedKeys.add(i);
		
		int[] arr = new int[pressedKeys.size()];
		
		for(int i = 0; i < arr.length; i++)
			arr[i] = pressedKeys.get(i);
		
		return arr;
	}
	
}
