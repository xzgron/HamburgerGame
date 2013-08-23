package food;

import input.GController;
import game.GObject;

public class GFood extends GObject {
	

	
	private GController controller;
	
	
	public GFood(float xPos, float yPos, float texWidth, float texHeight) {
		super(xPos, yPos, texWidth, texHeight);
		setTexFolder("food/");
	}


	public void update() {
		
	}
	
	
	
	

}
