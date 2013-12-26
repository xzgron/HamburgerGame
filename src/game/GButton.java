package game;

import game.GSprite;
import game.input.GMouse;

import org.lwjgl.input.Mouse;



public class GButton extends GSprite{
	
	public GButton(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	public GButton(int x, int y, int width, int height, String texture) {
		super(x, y, width, height, texture);
	}

	private boolean[] wasHeldIn = new boolean[5];
	
	public void update(){
		for(int i = 0; i < wasHeldIn.length; i++){
			if (isClicked(i))  //sann om man klickar på den med knappen
				wasHeldIn[i] = true;
			else if(!Mouse.isButtonDown(i)) //falsk om knappen inte är nere
				wasHeldIn[i] = false;
		}
	}
	public boolean isClicked(int button){
		return(GMouse.isButtonPressed(button) && isCursorWithin());
	}
	public boolean isReleasedOver(int button){
		return(!Mouse.isButtonDown(button) && isCursorWithin() && wasHeldIn[button]);
	}
	public boolean isReleased(int button){
		return(!Mouse.isButtonDown(button) && wasHeldIn[button]);
	}	
	public boolean isReleased(){
		for(int i = 0; i < 5; i++)
			if(isReleased(i))
				return true;
		return false;
	}	
	public boolean isPressed(int button){
		return(Mouse.isButtonDown(button) && isCursorWithin());
	}
	public boolean isHeldIn(int button){
		return(wasHeldIn[button] && Mouse.isButtonDown(button));
	}
	

	

	
}
