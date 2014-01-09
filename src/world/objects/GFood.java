package world.objects;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.opengl.Texture;

import world.WorldObject;

import controllers.GController;
import game.*;

public abstract class GFood extends WorldObject {

	float walkingSpeed = 100; // pixels per second
	float jumpingForce = 1000; // utgångs kraft från ett hopp v = jf/w

	int maxHealth;
	int currentHealth;
	
	Texture deathTexture = null;
	Audio deathSound = null;

	private GController controller;

	public GFood(float xPos, float yPos, float texWidth, float texHeight, String texture, float footPos,
			float headPos, int weight, int health) {
		super(xPos, yPos, texWidth, texHeight, "food/"+texture, footPos, headPos);
		setTexFolder("food/");
		
		maxHealth = health;
		currentHealth = maxHealth;
		setRadius(texWidth/2);
		setWeight(weight);
		
	}

	public void update() {
		if(!isDead()){
			if (controller != null)
				controller.handle(this);
		}
		else
			stop();
		
		GPhysics.useGravity(this);
		GPhysics.handleGroundCollision(this);
		
		updatePrevPos();
		useSpeed();
		


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

	public void aboveDamage(int amt, WorldObject attacker){
		damage(amt, attacker);
	}

	
	public void damage(int amt, WorldObject attacker) {
		if(amt < 0)
			return;
		currentHealth -= amt;
		if (currentHealth <= 0) {
			currentHealth = 0;
			this.die();
		}
	}
	
	public void underDamage(int amt, WorldObject attacker){
		damage(amt, attacker);
	}

	public void heal(int amt) {
		if(amt < 0)
			return;
		
		currentHealth += amt;
		if (currentHealth > maxHealth)
			currentHealth = maxHealth;
	}

	public void die() {
		currentHealth = 0;
		setTexture(deathTexture);
		if(deathSound != null)
			deathSound.playAsSoundEffect(1, 1, false,getX()-Main.game.world.getXTranslation(),getY()-Main.game.world.getYTranslation(),getZ());
		
		setIfSurface(true);
		removeShadow();
	}
	
	public void setDeathTexture(String fileName){
		deathTexture = GTexture.getTexture(getTexFolder() + fileName) ;
	}
	
	public void setDeathSound(String fileName){
		deathSound = GSound.getAudio(getTexFolder() + fileName);
	}

	public boolean isDead() {
		if (getHealth() <= 0)
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

	public void setJumpingForce(float f) {
		jumpingForce = f;
	}

	public float getJumpingForce() {
		return jumpingForce;
	}

	// //////MOVEMENT HANDLING//////////////

	public void jump() {
		jump(jumpingForce);
	}
	
	public void jump(float force){
		setZSpeed(force/(float)Math.sqrt(getWeight()));
	}

	
	public boolean tryJump() {
		if (isOnGround()){
			jump();
			return true;	
		}
		else 
			return false;
	}

	public void walk(float a) {
		setSpeedByAngle(walkingSpeed, a);

	}

	public void walk(float xDir,float yDir) {
		setSpeedByVector(walkingSpeed, xDir,yDir);

	}

	
	public boolean tryGroundWalk(float a) {
		if (isOnGround()){
			setSpeedByAngle(walkingSpeed, a);
			return true;
			}
		else 
			return false;
	}
	
	public void tryGroundWalk(float xDir,float yDir) {
		if (isOnGround())
			setSpeedByVector(walkingSpeed, xDir,yDir);
	}

	// ////////////////////////////

	// /////////////COLLISION//////////////////

	public void landedOn(WorldObject go) {
		jump();
	}
	
	public void gotLandedOnBy(WorldObject go) {
		
	}

	public void collidedWith(WorldObject go) {

	}

	// ///////////////////////////////////////
}
