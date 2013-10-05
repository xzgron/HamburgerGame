package worldObjects.food;

import input.GController;
import game.GObject;
import game.GPhysics;
import game.GWorld;
import game.GWorldObject;

public abstract class GFood extends GWorldObject {
	
	float walkingSpeed = 200; // pixels per second
	float zSpeed = 0;
	float health;
	
	
	private GController controller;
	
	
	
	public GFood(float xPos, float yPos, float texSize, float weight, float headPos, float footPos) {
		super(xPos, yPos, texSize,  headPos, footPos);
		setWeight(weight);
		setTexFolder("food/");
	}
	
	public void update(){
		GPhysics.useGravity(this);
		useSpeed();	
	}
	
	public void setController(GController controller){
		controller.add();
		this.controller = controller;
	}
	
	public void removeController(){
		controller.remove();
		controller = null;
		
	}
	
	public void setWalkingSpeed(float f){
		walkingSpeed = f;
	}
	
	public float getWalkingSpeed(){
		return walkingSpeed;
	}
	
	

	
	public void jumpedOn(float weight){
		/////////////OM MAN BLIR HOPPAD PÅ//////
	}
}
