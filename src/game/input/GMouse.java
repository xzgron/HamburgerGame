package game.input;

import static org.lwjgl.input.Keyboard.isKeyDown;
import static org.lwjgl.input.Mouse.*;

import java.util.LinkedList;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class GMouse {
	private static boolean[] wasDown = new boolean[5];
	
	private static int dx;
	private static int dy;
	
	public static void update(){
		for(int i = 0; i < wasDown.length; i++)
			wasDown[i] = isButtonDown(i);
		dx = Mouse.getDX();
		dy = -Mouse.getDY();
	}
	
	public static boolean isButtonPressed(int button){
		return (isButtonDown(button) && !wasButtonDown(button));
	}
	
	public static boolean isButtonReleased(int button){
		return (!isButtonDown(button) && wasButtonDown(button));
	}
	
	public static boolean wasButtonDown(int button){
		return wasDown[button];
	}
	
	public static int[] getPressedButtons(){
		LinkedList<Integer> pressedButtons = new LinkedList<Integer>();
		for(int i = 0; i < wasDown.length; i++)
			if(wasDown[i])
				pressedButtons.add(i);
		
		int[] arr = new int[pressedButtons.size()];
		
		for(int i = 0; i < arr.length; i++)
			arr[i] = pressedButtons.get(i);
		
		return arr;
	}
	
	public static int getY(){
		return Display.getHeight()-Mouse.getY()-1;
	}
	
	public static int getX(){
		return Mouse.getX();
	}
	
	public static int getDY(){
		return dy;
	}
	
	public static int getDX(){
		return dx;
	}
	
	
	
}
