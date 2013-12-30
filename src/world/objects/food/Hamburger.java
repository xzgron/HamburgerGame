package world.objects.food;

import java.util.LinkedList;

import world.HealthBar;
import world.WorldObject;
import world.objects.ingredients.*;
import game.GMath;
import game.GSprite;
import game.GPhysics;
import game.Game;
import game.parts.GameWorld;

public class Hamburger extends GFood {

	LinkedList<GIngredient> equipments = new LinkedList<GIngredient>();

	public Hamburger(float xPos, float yPos, float texSize) {
		super(xPos, yPos, 0, 0, 0, 0, 0, 0);

		 equipments.add(new HamburgerBreadUnderPart(xPos, yPos));

		// ingredients.add(new UnionRings(xPos, yPos));
		equipments.add(new Beef90Gram(xPos, yPos));
		equipments.add(new Beef60Gram(xPos, yPos));
		// equipments.add(new Sallad(xPos, yPos));
		// equipments.add(new Cheese(xPos, yPos));
		 equipments.add(new HamburgerBreadOverPart(xPos, yPos));
		createShadow();

		setWalkingSpeed(200);
		setJumpingForce(4000);
	}

	public void update() {
		super.update();
		// /UPDATE PREV POSITION//////
		for (GIngredient gi : equipments)
			gi.updatePrevPos();

		updateEquipmentPos();
	}

	public void render() {
		updateEquipmentPos();
		renderShadow();

		for (GIngredient gi : equipments)
			gi.render();
	}

	// ////////////////EQUIPMENTS/////////////////

	public void updateEquipmentPos() {
		equipments.get(0).setPosition(getX(), getY(), getZ());

		float totalHeight = equipments.get(0).getHeight();

		for (int i = 1; i < equipments.size(); i++) {
			GIngredient gi = equipments.get(i);

			gi.setPosition(getX(), getY(), getZ() + totalHeight + gi.getZ()
					- gi.getFootZPos());

			totalHeight += gi.getHeight();
		}
	}

	public LinkedList<GIngredient> getEquipments() {
		return equipments;
	}

	// /////////////////////////////////////////////

	// ///////////////////////////HEADS AND FOOTS////////////////////////////

	// huvudet bestämmer procentuellt var på bilden som går i taget...?

	public void setHeadZPos(float f) {
		setZ(f + equipments.getLast().getTexHeight() / 2
				- equipments.getLast().getTexHeight()
				* equipments.getLast().getHeadPosVar());
	}

	public float getHeadZPos() {
		return (equipments.getLast().getHeadZPos());
	}

	public float getHeadZPrev() {
		return (equipments.getLast().getHeadZPrev());
	}

	// foten bestämmer procentuellt var på bilden som går i marken

	public void setFootZPos(float f) {
		setZ(f + equipments.getFirst().getTexHeight() / 2
				- equipments.getFirst().getTexHeight()
				* equipments.getFirst().getFootPosVar());
	}

	public float getFootZPos() {
		return (equipments.getFirst().getFootZPos());
	}

	public float getFootZPrev() {
		return (equipments.getFirst().getFootZPrev());
	}

	// grounden är kort sagt i mitten av skuggan

	// ////////////GROUND POSITION////////////////////////

	public void setGroundYPos(float y) {
		yPos = (y + equipments.getFirst().getTexHeight() / 2 - equipments
				.getFirst().getTexHeight()
				* equipments.getFirst().getFootPosVar());
	}

	public float getGroundYPos() {
		return (equipments.getFirst().getGroundYPos());
	}

	public float getGroundYPrev() {
		return (equipments.getFirst().getGroundYPrev());
	}

	// //////////////////////////////////////////////////////////////////////

	// ///////////////SIZES//////////

	public float getHeight() {
		return equipments.getLast().getHeadZPos()
				- equipments.get(0).getFootZPos();

	}

	public int getWeight() {
		int weight = 0;
		for (GIngredient gi : equipments)
			weight += gi.getWeight();
		return weight;
	}

	// //////////////ABOUT HEALTH///////////////////
	
	public void aboveDamage(int amt){
		for (int i = equipments.size() - 1; i >= 0; i--) {
			GIngredient gi = equipments.get(i);
			if (gi instanceof HealthGiving || gi instanceof Armor) {
				gi.use(amt);
				if(gi.getDurability() <= 0){
					gi.setDurability(0);
					if(equipments.size() > 1)
						equipments.remove(gi);
					}
				break;
			}
		}
		if (getHealth() <= 0)
			this.die();
	}
	


	public void damage(int amt) {
		for (int i = equipments.size() - 1; i >= 0; i--) {
			GIngredient gi = equipments.get(i);
			if (gi instanceof HealthGiving) {
				gi.use(amt);
				if(gi.getDurability() <= 0){
					amt = -gi.getDurability();
					gi.setDurability(0);
					if(equipments.size() > 1)
						equipments.remove(gi);
				}
				else
					break;
			}
		}
		if (getHealth() <= 0) 
			this.die();
	}

	public void underDamage(int amt){
		for (int i = 0; i < equipments.size(); i++) {
			GIngredient gi = equipments.get(i);
			if (gi instanceof HealthGiving || gi instanceof Armor) {
				gi.use(amt);
				if(gi.getDurability() <= 0){
					gi.setDurability(0);
					if(equipments.size() > 1)
						equipments.remove(gi);
					}
				break;
			}
		}	
		if (getHealth() <= 0)
			this.die();
	}
	
	public int getHealth() {
		int health = 0;
		for (GIngredient gi : equipments)
			if (gi instanceof HealthGiving) {
				health += gi.getDurability();
			}
		return health;
	}

	public int getMaxHealth() {
		int health = 0;
		for (GIngredient gi : equipments)
			if (gi instanceof HealthGiving) {
				health += gi.getMaxDurability();
			}
		return health;
	}

	public int getArmor() {
		int armor = 0;
		for (GIngredient gi : equipments)
			if (gi instanceof Armor) {
				armor += gi.getDurability();
			}
		return armor;
	}

	public int getMaxArmor() {
		int armor = 0;
		for (GIngredient gi : equipments)
			if (gi instanceof Armor) {
				armor += gi.getMaxDurability();
			}
		return armor;
	}

	// ///////////////////////////

	public float getRadius() {
		float rMax = 0;
		for (GIngredient gi : equipments)
			if (gi instanceof HealthGiving || gi instanceof Armor)
				rMax = Math.max(rMax, gi.getRadius());
		return rMax;
	}

	public float getTexHeight() {
		return equipments.getLast().getHeadZPos()
				- equipments.getFirst().getFootZPos();
	}

	public float getTexWidth() {
		float wMax = 0;
		for (GIngredient gi : equipments)
			wMax = Math.max(wMax, gi.getTexWidth());
		return wMax;
	}

	// /////////////////////////

	// ///////////////////SORTING//////////////////
/*
	public LinkedList<GIngredient> sortOut(Class c) {
		LinkedList<GIngredient> sortedIngredients = new LinkedList<GIngredient>();
		for (GIngredient gi : player.getEquipments())
			if (gi instanceof c)
				sortedIngredients.add(gi);
		return sortedIngredients;

	}

	public LinkedList<GIngredient> getConsumedEquipments() {
		LinkedList<GIngredient> deadIngredients = new LinkedList<GIngredient>();

		for (GIngredient gi : equipments)
			;

		return deadIngredients;

	}*/

	// /////////////////////////////

	// /////////////////COMBAT/////////////////

	public void landedOn(WorldObject go) {
		if (go instanceof GFood) {
			float dmg = -(getZSpeed() - go.getZSpeed()) * getWeight() / 1000;
			System.out.println(dmg);
			((GFood) go).damage((int) dmg);

			if (!((GFood) go).isDead())
				jump();
		}
	}

	public void gotLandedOnBy(WorldObject go) {

	}

	public void collidedWith(WorldObject go) {

	}
}