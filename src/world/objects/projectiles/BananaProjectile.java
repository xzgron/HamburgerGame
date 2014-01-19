package world.objects.projectiles;

import java.util.LinkedList;

import game.GMath;
import game.GPhysics;
import game.Main;
import game.tools.GTimer;
import world.WorldObject;
import world.objects.GFood;
import world.objects.GProjectile;
import world.objects.food.HostileFood;
import world.objects.ingredients.Banana;

public class BananaProjectile extends GProjectile {

	GTimer despawnTimer = new GTimer(0.2f);
	LinkedList<WorldObject> objectsHit = new LinkedList<WorldObject>();
	LinkedList<GTimer> hitTime = new LinkedList<GTimer>();
	
	WorldObject caster;
	Banana banana;
	float angleDirection;
	float startSpeed; // så att inte bananen rör sig för fort tillbaka

	public BananaProjectile(float xPos, float yPos, float zPos, float xDir,
			float yDir, float speed, WorldObject caster, Banana banana,
			boolean right) {
		super(xPos, yPos, zPos, 70, 70, "ingredients/banana1", 0.3f, 0.7f);
		setRadius(35f);
		setWeight(10);
		setSpeedByVector(speed, xDir, yDir);
		moveByVector(10, xDir, yDir);
		this.caster = caster;
		this.banana = banana;
		startSpeed = speed;
		
		if (right)
			angleDirection = -1;
		else
			angleDirection = 1;
	}

	public void collidedWith(WorldObject obj) {
		if (despawnTimer.hasExceeded() && obj == caster) {
			Main.game.world.deSpawn(this);
			banana.recover(1);
		} else if (!objectsHit.contains(obj)) {
			if (obj instanceof HostileFood && this.isMoving()) {
				//float dSpeed = GMath.getLength(getXSpeed()-obj.getXSpeed(), getYSpeed()-obj.getYSpeed());
				//float damage = GPhysics.calculateDamage(dSpeed,getWeight(),2.5f);
				((GFood) obj).damage(40,this);
			}
		}
		objectsHit.addLast(obj);
		hitTime.add(new GTimer(0));
	}

	public void update() {
		
		for(int i = 0; i < hitTime.size();i++){
			if(hitTime.get(i).getPastTime() > 0.2f){ //tid för borttagning av objects som den redan träffat
				hitTime.remove(i); //så den kan träffa igen.
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
			accelerateByAngle(5000, angle);
			
			if(getXYSpeed() > startSpeed) //inte för fort
				setXYSpeed(startSpeed);
		}
		
		
		
		//annat
		setZSpeed(0);
		super.update();
		GPhysics.handleGroundCollision(this);
		
		if (!isInAir()) {
			stop();
		}
	}

}
