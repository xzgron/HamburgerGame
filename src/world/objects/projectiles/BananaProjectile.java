package world.objects.projectiles;

import java.util.LinkedList;

import game.GMath;
import game.GPhysics;
import game.Main;
import game.input.GTimer;
import world.WorldObject;
import world.objects.GFood;
import world.objects.GProjectile;
import world.objects.HostileFood;
import world.objects.ingredients.Banana;

public class BananaProjectile extends GProjectile {

	GTimer despawnTimer = new GTimer(0.2f);
	LinkedList<WorldObject> ObjectsHit = new LinkedList<WorldObject>();

	WorldObject caster;
	Banana banana;
	float angleDirection;

	public BananaProjectile(float xPos, float yPos, float zPos, float xDir,
			float yDir, float speed, WorldObject caster, Banana banana,
			boolean right) {
		super(xPos, yPos, zPos, 70, 70, "ingredients/banana1", 0.3f, 0.7f);
		setRadius(35f);
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

	public void collidedWith(WorldObject obj) {
		if (despawnTimer.hasExceeded() && obj == caster) {
			Main.game.world.deSpawn(this);
			banana.recover(1);
		} else if (!ObjectsHit.contains(obj)) {
			if (obj instanceof HostileFood) {
				((GFood) obj).damage(50, this);
			}
		}
		ObjectsHit.addLast(obj);
	}

	public void update() {
		/*
		 * float xDir = caster.getX() - getX(); float yDir = caster.getY() -
		 * getY(); accelerateByVector(5000, xDir, yDir, 0);
		 */
		if (isInAir()) {
			float bonusAngle = 60 - despawnTimer.getPastTime() * 180;
			if (bonusAngle < -60)
				bonusAngle = 0;
			float angle = GMath.getAngle(this, caster) + angleDirection
					* bonusAngle;
			accelerateByAngle(5000, angle);
		}
		setZSpeed(0);
		super.update();
		GPhysics.handleGroundCollision(this);
		
		if (!isInAir()) {
			stop();
		}
	}

}
