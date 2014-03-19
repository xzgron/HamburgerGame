package world.objects.food.hostile;

import game.GMath;
import game.GPhysics;
import game.Main;
import game.HUDMaterial.HealthBar;
import game.parts.GameWorld;
import game.tools.GTimer;
import world.WorldObject;
import world.objects.GFood;
import world.objects.food.HostileFood;

public class Carrot extends HostileFood{
	
	private WorldObject target;
	
	private float walkingSpeed; // pixels per second
	private float jumpForce; // utgångs kraft från ett hopp v = jf/w
	
	private float attackJumpForce; // utgångs kraft från ett hopp v = jf/w

	private GTimer jumpWaitTimer = new GTimer(0.8f);
	
	private HealthBar healthBar = new HealthBar(0, -30, 50, 7,  this);
	
	public Carrot(float xPos, float yPos, WorldObject target) {
		super(xPos, yPos, 40, 125, "Carrot/carrot", 0.05f, 0.85f, 75, 75);
		setRadius(20);
		
		setDeathTexture("shadow");
		setDeathSound("blueberry/death" + (int)GMath.random(1,4));
		
		healthBar.setFollow(true);
		healthBar.setBackgroundColor(0.8f,0,0,1);
		healthBar.setColor(0, 0.8f,0,1);

		
		walkingSpeed = GMath.random(60, 100);
		jumpForce = (float)Math.sqrt(getWeight())*720f;
		attackJumpForce = 1000;
		
		this.target = target;
	}

	@Override
	public void handleAI() {
		if (this.justLanded()) {
			jumpWaitTimer.reset();
		}
	
		if(this.isOnGround())
			this.stop();

		
		if (jumpWaitTimer.hasExceeded()) {		

			float dx = target.getX() - this.getX();
			float dy = target.getY() - this.getY();
			
			float r = GPhysics.getWorldRadius(target.getRadius(), dx, dy);

			//attack
			float dist = GMath.getDistance(target, this);
			if (dist < r + 300) {
				if(dist < 100){
					this.tryJump(jumpForce);
					this.tryWalk(walkingSpeed, -dx, -dy);
				}
				else{
					this.tryJump(attackJumpForce);
					this.tryWalk(dist, dx, dy);
				}
			// gå
			} else {
				this.tryJump(jumpForce);
				this.tryWalk(walkingSpeed, dx, dy);
			}
		}
		
	}
	@Override
	public void update(GameWorld world){
		if(this.getZSpeed() < 0)
			this.accelerate(0, -400, 0);
		super.update(world);
	}

	@Override
	public void render() {
		super.render();
		if(!isDead())
			healthBar.render();
	}

	@Override
	public void landedOn(WorldObject go, GameWorld world) {
		if(go == target)
			((GFood)go).damage(60, this,world);	
		  airJump(attackJumpForce);
	}

	@Override
	public void gotLandedOnBy(WorldObject go, GameWorld world) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collidedWith(WorldObject go, GameWorld world) {
		// TODO Auto-generated method stub
		
	}
	

}
