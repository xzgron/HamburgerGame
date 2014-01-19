package world.objects;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.opengl.Texture;

import world.WorldObject;

import game.*;

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
	
	public void update() {
		if(!isDead()){
			handleAI();
		}
		else
			stop();
		
		GPhysics.useGravity(this);
		GPhysics.handleGroundCollision(this);
		
		updatePrevPos();
		useSpeed();
		


	}


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

	public abstract void landedOn(WorldObject go);
	
	public abstract void gotLandedOnBy(WorldObject go);

	public abstract void collidedWith(WorldObject go);

	// ///////////////////////////////////////
}
