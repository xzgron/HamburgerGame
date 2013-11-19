package worldObjects.food;

import world.WorldObject;
import controllers.GController;
import game.GSprite;
import game.GPhysics;
import game.parts.GameWorld;

public abstract class GFood extends WorldObject {
	
	float walkingSpeed = 200; // pixels per second
	float zSpeed = 0;
	
	int maxHealth;
	int currentHealth;
	
	
	
	private GController controller;
	
	
	
	public GFood(float xPos, float yPos, float texSize, float weight, float headPos, float footPos, int health) {
		super(xPos, yPos, texSize,  headPos, footPos);
		maxHealth = health;
		currentHealth = maxHealth;
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
	
	public void damage(float amt){
		currentHealth -= amt;
		//System.out.println(currentHealth);
		if(currentHealth < 0) {
			currentHealth = 0;
			this.die();
			}
	}

	public void die() {
		System.out.println("Something fainted");
	}

	public void health(float amt){
		currentHealth += amt;
		if(currentHealth > maxHealth)
			currentHealth = maxHealth;
	}
	
	public boolean isDead(){
		if(currentHealth == 0)
			return true;
		else
			return false;
	}
	
	public void landedOn(WorldObject go){
		((GFood) go).damage((getWeight()*getYSpeed()/1000));
	}
	
	public int getHealth(){
		return currentHealth;
	}
	
	public int getMaxHealth(){
		return maxHealth;
	}
}
