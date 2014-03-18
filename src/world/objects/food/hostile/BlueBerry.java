package world.objects.food.hostile;

import org.lwjgl.input.Keyboard;

import world.Player;
import world.WorldObject;
import world.objects.GFood;
import world.objects.food.HostileFood;
import game.*;
import game.HUDMaterial.HealthBar;
import game.parts.GameWorld;
import game.tools.GTimer;

public class BlueBerry extends HostileFood {
	
	private WorldObject target;
	
	private float walkingSpeed; // pixels per second
	private float jumpForce; // utgångs kraft från ett hopp v = jf/w
	
	private float attackJumpSpeed; // pixels per second
	private float attackJumpForce; // utgångs kraft från ett hopp v = jf/w

	private GTimer jumpWaitTimer = new GTimer(0.3f);
	
	private HealthBar healthBar = new HealthBar(0, -30, 50, 7,  this);
	

	
	public BlueBerry(float xPos, float yPos, float size, WorldObject target) {
		super(xPos, yPos, size, size,"Blueberrys/B" + ((int) (Math.random() * 4)), 0.13f, 0.73f, (int)(GMath.getSphereVolume(size/2)/700+1), (int)(GMath.getSphereVolume(size/2)/700+1));
		setRadius(size/2.1f);
		
		setDeathTexture("shadow");
		setDeathSound("blueberry/death" + (int)GMath.random(1,4));
		
		healthBar.setFollow(true);
		healthBar.setBackgroundColor(0.8f,0,0,1);
		healthBar.setColor(0, 0.8f,0,1);

		walkingSpeed = GMath.random(60, 100);
		jumpForce = (float)Math.sqrt(getWeight())*120f;
		attackJumpForce = (float)Math.sqrt(getWeight())*250f;
		attackJumpSpeed = 170f;
		
		this.target = target;
	}

	@Override
	public void handleAI(){
		
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
				if (GMath.getDistance(target, this) < r + 100 /*&& !GPhysics.objectsOverlapp(Main.game.world.getPlayer(), this)*/) {
					this.tryWalk(attackJumpSpeed, dx, dy);
					this.tryJump(attackJumpForce);	
				// gå
				} else {
					if(this.tryJump(jumpForce))
						this.tryWalk(walkingSpeed, dx, dy);
				}
			}

	}
	
	@Override
	public void render() {
		super.render();
		if(!isDead())
			healthBar.render();
	}

	
	
	//////////ACTION////////////////////
	@Override
	public void landedOn(WorldObject go, GameWorld world) {
		
	}
	
	@Override
	public void collidedWith(WorldObject go, GameWorld world) {
		if (go == target && go instanceof GFood){
			float dx = getXSpeed()-go.getXSpeed();
			float dy = getYSpeed()-go.getYSpeed();
			float dmg = (float) (GMath.getLength(dx,dy)*Math.sqrt(getWeight())/500); // genom 1000 om roten ur
			if((dx > 0) == (getXSpeed() > 0) && (dy > 0) == (getYSpeed() > 0)){ //rör sig mot targeten
				((GFood) go).damage((int) dmg,this,world);
				airJump(jumpForce);
			}
			addSpeed(go.getXSpeed(), go.getYSpeed(), 0);
		}
		

		setSpeedByVector(200, getX() - go.getX(), getY() - go.getY());
	}
	@Override
	public void gotLandedOnBy(WorldObject go, GameWorld world) {
		
	}

}
