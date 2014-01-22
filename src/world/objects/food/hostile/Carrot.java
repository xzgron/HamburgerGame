package world.objects.food.hostile;

import game.GMath;
import game.GPhysics;
import game.Main;
import game.HUDMaterial.HealthBar;
import game.tools.GTimer;
import world.WorldObject;
import world.objects.GFood;
import world.objects.food.HostileFood;

public class Carrot extends HostileFood{
	
	private WorldObject target = Main.game.world.getPlayer();
	
	private float walkingSpeed; // pixels per second
	private float jumpForce; // utgångs kraft från ett hopp v = jf/w
	
	private float attackJumpForce; // utgångs kraft från ett hopp v = jf/w

	private GTimer jumpWaitTimer = new GTimer(0.3f);
	
	private HealthBar healthBar = new HealthBar(0, -30, 50, 7,  this);
	
	public Carrot(float xPos, float yPos) {
		super(xPos, yPos, 40, 125, "Carrot/carrot", 0.1f, 0.9f, 75, 75);
		setRadius(20);
		
		setDeathTexture("shadow");
		setDeathSound("blueberry/death" + (int)GMath.random(1,4));
		
		healthBar.setFollow(true);
		healthBar.setBackgroundColor(0.8f,0,0,1);
		healthBar.setColor(0, 0.8f,0,1);

		
		walkingSpeed = GMath.random(60, 100);
		jumpForce = (float)Math.sqrt(getWeight())*120f;
		attackJumpForce = 5000;
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
			if (dist < r + 300 /*&& !GPhysics.objectsOverlapp(Main.game.world.getPlayer(), this)*/) {
				if (this.isOnGround()) {
					this.tryWalk(dist/1.6f, dx, dy);
					this.tryJump(attackJumpForce);	
				}
			// gå
			} else {
				if(this.tryJump(jumpForce))
					this.tryWalk(walkingSpeed, dx, dy);
			}
		}
		
	}
	public void update(){
		if(this.getZSpeed() < 0)
			this.accelerate(0, -400, 0);
		super.update();
	}
	                    
	public void render() {
		super.render();
		if(!isDead())
			healthBar.render();
	}

	@Override
	public void landedOn(WorldObject go) {
		if(go == target)
			((GFood)go).damage(20, this);	
	}

	@Override
	public void gotLandedOnBy(WorldObject go) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collidedWith(WorldObject go) {
		// TODO Auto-generated method stub
		
	}

}
