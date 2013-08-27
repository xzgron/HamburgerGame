package worldObjects.food;

import input.GController;
import game.GObject;
import game.GWorldObject;

public abstract class GFood extends GWorldObject {
	
	boolean walking = false;
	float walkingSpeed = 200; // pixels per second
	float zSpeed = 0;
	
	
	private GController controller;
	
	private GFoodShadow shadow = null;
	
	public GFood(float xPos, float yPos, float texWidth, float texHeight) {
		super(xPos, yPos, texWidth, texHeight);
		setTexFolder("food/");
	}


	
	public void setController(GController controller){
		controller.add();
		this.controller = controller;
	}
	
	public void removeController(){
		controller.remove();
		controller = null;
		
	}
	
	public void setWalking(boolean b){
		walking = b;
	}
	
	public void setWalkingSpeed(float f){
		walkingSpeed = f;
	}
	
	public float getWalkingSpeed(){
		return walkingSpeed;
	}
	
	
	public void createShadow(){
		shadow = new GFoodShadow(this);
	}
	
	public void renderShadow() {
		shadow.render();
	}

	public void updateShadow() {
		shadow.update();
	}
	
	public void setZSpeed(float f){
		zSpeed = f;
	}
	public float getZSpeed(){
		return zSpeed;
	}

}
