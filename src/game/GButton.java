package game;

import game.GSprite;


import org.lwjgl.input.Mouse;



public class GButton extends GSprite{
	
	
	boolean[] held = new boolean[3];
	boolean[] clicked = new boolean[3];
	
	public GButton(int x, int y, int width, int height) {
		super(x, y, width, height);
		setTexFolder("buttons/");
	}
	public boolean rightClicked = false;
	
	public void update(){
		rightClicked = isRightClicked();
	}
	
	public boolean isRightClicked(){
		if(Mouse.isButtonDown(0) && isCursorOver())
			return true;
		else
			return false;
	}
	
	public boolean wasRightClicked(){
		return rightClicked;
	}
	
	public boolean isRightReleasedOver(){
		if(!Mouse.isButtonDown(0) && wasRightClicked() && isCursorOver())
			return true;
		else
			return false;
	}
	
	public boolean isRightReleased(){
		if(!Mouse.isButtonDown(0) && wasRightClicked())
			return true;
		else
			return false;
	}
	

	
}
