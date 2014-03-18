package world;

import static org.lwjgl.input.Keyboard.isKeyDown;
import options.Controlls;

import org.lwjgl.opengl.Display;

import game.GPhysics;
import game.HUDMaterial.Inventory;
import game.parts.GameWorld;
import world.objects.GFood;
import world.objects.food.Hamburger;

public class Player extends Hamburger{
	////////////INVENTORY//////////////
	public MoneyPouch money = new MoneyPouch();
	///////////////////////////////////
	
	//////////////LEVEL///////////////
	public LevelHandler level = new LevelHandler();
	///////////////////////////////////
	
	////////////INVENTORY//////////////
	private Inventory inventory = new Inventory(Display.getWidth()/2-80,Display.getHeight()/2+80, 6,3);
	
	public Inventory getInventory(){
		return inventory;
	}
	///////////////////////////////////
	
	////////////////MOVEMENT///////////
	private boolean jumping = false;
	
	private float walkingSpeed = 200;
	private float bounceForce = 1500;
	private float jumpForce = 4000;
	///////////////////////////////////
	
	//////////////COMBAT///////////////
	float landingOnDamageMultiplier = 1;
	float projectileDamageMultiplier = 1;
	
	///////////////////////////////////
	

	public Player(float xPos, float yPos) {
		super(xPos, yPos);
	}
	
	public void handleAI(){		
	//G�//
	float x = 0;
	float y = 0;
	if (isKeyDown(Controlls.UP_KEY))
		y -= 1;
	if (isKeyDown(Controlls.RIGHT_KEY))
		x += 1;
	if (isKeyDown(Controlls.DOWN_KEY))
		y += 1;
	if (isKeyDown(Controlls.LEFT_KEY))
		x -= 1;
	if(x==0 && y==0)
		stop();
	else
		this.setSpeedByVector(walkingSpeed,x,y);

	//SKUTTA///
	if (isMoving())
		this.tryJump(bounceForce);
	
	//HOPPA////
	if(this.isOnGround())
		jumping = false;
	if (isKeyDown(Controlls.JUMP_KEY) && !jumping) {
		jumping = true;
		this.airJump(jumpForce);
		}
	}
	
	
	
	///////////////////COMBAT/////////////////

	public void landedOn(WorldObject go, GameWorld world) {
		if (go instanceof GFood) {
			float dmg = GPhysics.calculateDamage(go.getZSpeed() - getZSpeed(), getWeight(), 1f);
			((GFood) go).damage((int) dmg,this, world);

			if (!((GFood) go).isDead())
				airJump(jumpForce);
		}
		
	}

	public void gotLandedOnBy(WorldObject go, GameWorld world) {

	}

	public void collidedWith(WorldObject go, GameWorld world) {

	}
	
	//////////////////////////////////////////
}
