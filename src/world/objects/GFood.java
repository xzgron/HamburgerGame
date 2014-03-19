package world.objects;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.opengl.Texture;

import world.WorldObject;
import game.*;
import game.parts.GameWorld;

public abstract class GFood extends WorldObject {

	int maxHealth;
	int currentHealth;
	
	Texture deathTexture = null;
	Audio deathSound = null;

	public GFood(float xPos, float yPos, float texWidth, float texHeight, String texture, float footPos,
			float headPos, int weight, int health) {
		super(xPos, yPos, texWidth, texHeight, "food/"+texture, footPos, headPos);
		setTexFolder("food/");
		createShadow();
		maxHealth = health;
		currentHealth = maxHealth;
		setRadius(texWidth/2);
		setWeight(weight);
		
	}

	public abstract void handleAI();
	
	public void update(GameWorld world) {
		if(!isDead()){
			handleAI();
		}
		else
			stop();
		
		GPhysics.handleGravity(this , world);
		GPhysics.handleGroundCollision(this, world);
		
		updatePrevPos();
		useSpeed();
		


	}


	// ///////////ABOUT HEALTH/////////////////////

	public void aboveDamage(int amt, WorldObject attacker, GameWorld world){
		damage(amt, attacker,world);
	}

	
	public void damage(int amt, WorldObject attacker, GameWorld world) {
		if(amt < 0)
			return;
		currentHealth -= amt;
		if (currentHealth <= 0) {
			currentHealth = 0;
			this.die(world);
		}
	}
	
	public void underDamage(int amt, WorldObject attacker, GameWorld world){
		damage(amt, attacker,world);
	}

	public void heal(int amt) {
		if(amt < 0)
			return;
		
		currentHealth += amt;
		if (currentHealth > maxHealth)
			currentHealth = maxHealth;
	}

	public void die(GameWorld world) {
		currentHealth = 0;
		setTexture(deathTexture);
		if(deathSound != null)
			deathSound.playAsSoundEffect(1, 1, false);
		
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
		return (getHealth() <= 0);
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

	

	// ////////////////////////////


	// //////MOVEMENT HANDLING//////////////


	public void airJump(float force){
		setZSpeed(force/(float)Math.sqrt(getWeight()));
	}

	
	public boolean tryJump(float force) {
		if (isOnGround()){
			airJump(force);
			return true;	
		}
		else 
			return false;
	}


	
	public boolean tryWalk(float walkingSpeed, float a) {
		if (isOnGround()){
			setSpeedByAngle(walkingSpeed, a);
			return true;
			}
		else 
			return false;
	}
	
	public void tryWalk(float walkingSpeed, float xDir,float yDir) {
		if (isOnGround())
			setSpeedByVector(walkingSpeed, xDir,yDir);
	}

	// ////////////////////////////

	// /////////////COLLISION//////////////////

	public abstract void landedOn(WorldObject go, GameWorld world);
	
	public abstract void gotLandedOnBy(WorldObject go, GameWorld world);

	public abstract void collidedWith(WorldObject go, GameWorld world);

	// ///////////////////////////////////////
}
