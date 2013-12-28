package world.objects.food;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.opengl.Texture;

import world.WorldObject;
import controllers.GController;
import game.*;

public abstract class GFood extends WorldObject {

	float walkingSpeed = 100; // pixels per second
	float jumpingSpeed = 100; // utgångs hastighet för ett hopp

	int maxHealth;
	int currentHealth;
	
	String deathTexture = null;
	String deathSound = null;

	private GController controller;

	public GFood(float xPos, float yPos, float texSize, float footPos,
			float headPos, int weight, int health) {
		super(xPos, yPos, texSize, footPos, headPos);
		maxHealth = health;
		currentHealth = maxHealth;
		setWeight(weight);
		setTexFolder("food/");
	}

	public void update() {
		if(!isDead()){
			if (controller != null)
				controller.handle(this);
		}
		else
			stop();
		
		GPhysics.useGravity(this);
		
		updatePrevPos();
		useSpeed();
		
		if (getZ() <= 0){
			setZ(0);
			setZSpeed(0);
			}
		


	}

	// ///////////CONTROLLING/////////////
	public void setController(GController controller) {
		this.controller = controller;
	}

	public void removeController() {
		controller = null;
	}

	// ////////////////////////////////

	// ///////////ABOUT HEALTH/////////////////////

	public void damage(int amt) {
		currentHealth -= amt;
		if (currentHealth <= 0) {
			currentHealth = 0;
			this.die();
		}
	}

	public void heal(int amt) {
		currentHealth += amt;
		if (currentHealth > maxHealth)
			currentHealth = maxHealth;
	}

	public void die() {
		setTexture(deathTexture);
		GSound.playSound(deathSound);
		setIfSurface(true);
		removeShadow();
	}
	
	public void setDeathTexture(String fileName){
		deathTexture = fileName;
	}
	
	public void setDeathSound(String fileName){
		deathSound = fileName;
	}

	public boolean isDead() {
		if (getHealth() == 0)
			return true;
		else
			return false;
	}
	
	public void setHealth(int h){
		currentHealth = h;
	}
	public void setMaxHealth(int h){
		maxHealth = h;
	}

	public int getHealth() {
		return currentHealth;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	// /////////////////////////////

	// /////////COLLISION HANDLING///////
	public void landedOn(GFood gf) {
	}

	public void collidedWith(GFood gf) {
	}

	// ////////////////////////////

	// ///////////MOVING PREFERENCES/////////////

	public void setWalkingSpeed(float f) {
		walkingSpeed = f;
	}

	public float getWalkingSpeed() {
		return walkingSpeed;
	}

	public void setJumpingSpeed(float f) {
		jumpingSpeed = f;
	}

	public float getJumpingSpeed() {
		return jumpingSpeed;
	}

	// //////MOVEMENT HANDLING//////////////

	public void jump() {
		setZSpeed(jumpingSpeed);
		GSound.playSound("hamburger/movement/jump");
	}

	
	public void tryJump() {
		if (!isInAir())
			jump();
	}

	public void walk(float a) {
		setSpeedByAngle(walkingSpeed, a);

	}

	public void walk(float xDir,float yDir) {
		setSpeedByVector(walkingSpeed, xDir,yDir);

	}

	
	public void tryGroundWalk(float a) {
		if (!isInAir())
			setSpeedByAngle(walkingSpeed, a);
	}
	
	public void tryGroundWalk(float xDir,float yDir) {
		if (!isInAir())
			setSpeedByVector(walkingSpeed, xDir,yDir);
	}

	// ////////////////////////////

	// /////////////COLLISION//////////////////

	public void landedOn(WorldObject go) {
		jump();
	}

	public void collidedWith(WorldObject go) {

	}

	// ///////////////////////////////////////
}
