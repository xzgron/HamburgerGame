package game.tools;

import static org.lwjgl.input.Mouse.*;

import java.util.LinkedList;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class GMouse {
	public final static int BUTTON_LEFT = 0;
	public final static int BUTTON_RIGHT = 1;
	public final static int BUTTON_WHEEL = 2;
	public final static int BUTTON_SPECIAL_1 = 3;
	public final static int BUTTON_SPECIAL_2 = 4;
	
	private static boolean[] wasDown = new boolean[5];
	
	private static int dx;
	private static int dy;
	private static int dWheel;
	
	public static void update(){
		for(int i = 0; i < wasDown.length; i++)
			wasDown[i] = isButtonDown(i);
		dx = Mouse.getDX();
		dy = -Mouse.getDY()+1;
		dWheel = Mouse.getDWheel();
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
	
	public static int getDWheel(){
		return dWheel;
		
	}
	
	
	
}
