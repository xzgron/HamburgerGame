package world.objects.projectiles;

import java.util.LinkedList;

import game.GMath;
import game.GPhysics;
import game.Main;
import game.parts.GameWorld;
import game.tools.GTimer;
import world.WorldObject;
import world.objects.GFood;
import world.objects.GProjectile;
import world.objects.food.HostileFood;
import world.objects.ingredients.Banana;

public class BananaProjectile extends GProjectile {

	GTimer despawnTimer = new GTimer(0.2f);
	LinkedList<WorldObject> objectsHit = new LinkedList<WorldObject>();
	LinkedList<GTimer> hitTimes = new LinkedList<GTimer>();

	WorldObject caster;
	Banana banana;
	float angleDirection;

	public BananaProjectile(float xPos, float yPos, float zPos, float xDir,
			float yDir, float speed, WorldObject caster, Banana banana,
			boolean right) {
		super(xPos, yPos, zPos, 80, 80, "bananarang", 0.3f, 0.7f);
		setRadius(40f);
		setWeight(9);
		setSpeedByVector(speed, xDir, yDir);
		moveByVector(10, xDir, yDir);
		this.caster = caster;
		this.banana = banana;

		if (right)
			angleDirection = -1;
		else
			angleDirection = 1;
	}
	
	@Override
	public void collidedWith(WorldObject obj, GameWorld world) {
		if (despawnTimer.hasExceeded() && obj == caster) {
			world.deSpawn(this);
			banana.recover(1);
		} else if (!objectsHit.contains(obj)) {
			if (obj instanceof HostileFood) {
				((GFood) obj).damage(50, this,world);
			}
		}
		objectsHit.addLast(obj);

	}

	@Override
	public void update(GameWorld world) {
		
		for(int i = 0; i < hitTimes.size();i++){
			if(hitTimes.get(i).getPastTime() > 0.2f){ //tid för borttagning av objects som den redan träffat
				hitTimes.remove(i); //så den kan träffa igen.
				objectsHit.remove(i);
			}
		}
		
		
		if (isInAir()) { //snurr
			float bonusAngleStart = 90f;
			float recoverTime = 0.6f;
			
			float bonusAngle = bonusAngleStart - despawnTimer.getPastTime() * bonusAngleStart/recoverTime;
			if (bonusAngle < -bonusAngleStart) //när vinkeln gått tillbaka
				bonusAngle = 0;
			
			float angle = GMath.getAngle(this, caster) + angleDirection
					* bonusAngle;
			this.accelerateByAngle(5000, angle);
		}
		
		this.setZSpeed(0);
		super.update(world);
		GPhysics.handleGroundCollision(this,world);

		if (this.isOnGround()) {
			this.stop();
		}
	}

}
