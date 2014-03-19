package world.objects.food;

import java.util.LinkedList;

import world.WorldObject;
import world.objects.GFood;
import world.objects.GIngredient;
import world.objects.ingredients.*;
import world.objects.ingredients.bases.Armor;
import world.objects.ingredients.bases.HealthGiving;
import game.GMath;
import game.GSprite;
import game.GPhysics;
import game.Game;
import game.HUDMaterial.HealthBar;
import game.parts.GameWorld;

public abstract class Hamburger extends GFood {

	LinkedList<GIngredient> equipments = new LinkedList<GIngredient>();

	public Hamburger(float xPos, float yPos) {
		super(xPos, yPos, 0, 0, null, 0, 0, 0, 0);

		equip(new Beef90Gram(xPos, yPos));
		equip(new Sallad(xPos, yPos));
		equip(new BreadTop(xPos, yPos));
		
		equip(0,new OnionRings(xPos, yPos));
		equip(0,new BreadBottom(xPos, yPos));


		
		//equipments.add(new Beef60Gram(xPos, yPos));
		
		// equipments.add(new Cheese(xPos, yPos));

	}
	@Override
	public void handleAI(){
		
	}
	
	@Override
	public void update(GameWorld world) {
		super.update(world);
		// /UPDATE PREV POSITION//////
		for (GIngredient gi : equipments){
			gi.update(world);
			gi.updatePrevPos();
		}
		updateEquipmentPos();
	}

	@Override
	public void render() {
		updateEquipmentPos();
		renderShadow();

		for (GIngredient gi : equipments)
			gi.render();
	}

	// ////////////////EQUIPMENTS/////////////////

	public void updateEquipmentPos() {
		equipments.get(0).setPosition(getX(), getY(), getZ());

		for (int i = 1; i < equipments.size(); i++) {
			GIngredient gi = equipments.get(i);

	
			//System.out.println(getGroundYPos());
			gi.setPosition(getX(), getY(),0);
			gi.setFootZPos(equipments.get(i-1).getHeadZPos());
		}
	}
	public boolean isEquipmentLegal(){
		if(isDead())
			return false;
		boolean legal = true;
		for(GIngredient ingredient: getEquipments())
			if(!ingredient.isPositionLegal(getEquipments()))
				legal = false;
		
		return legal;
			
	}
	
	public boolean equip(int position, GIngredient ingredient){ //returnerar om det gick.
		getEquipments().add(position, ingredient);
		if(isEquipmentLegal())
			return true;
		else{
			getEquipments().remove(ingredient);
			return false;
		}
			
	}
	
	public boolean equip(GIngredient ingredient){ //returnerar om det gick.
		getEquipments().add(ingredient);
		if(isEquipmentLegal())
			return true;
		else{
			getEquipments().removeLast();
			return false;
		}
			
	}
	
	public LinkedList<GIngredient> getEquipments() {
		return equipments;
	}

	// /////////////////////////////////////////////

	// ///////////////////////////HEADS AND FOOTS////////////////////////////

	// huvudet best‰mmer procentuellt var pÂ bilden som gÂr i taget...?
	@Override
	public void setHeadZPos(float f) {
		setZ(f + equipments.getLast().getTexHeight() / 2
				- equipments.getLast().getTexHeight()
				* equipments.getLast().getHeadPosVar());
	}
	@Override
	public float getHeadZPos() {
		return (equipments.getLast().getHeadZPos());
	}
	@Override
	public float getHeadZPrev() {
		return (equipments.getLast().getHeadZPrev());
	}

	// foten best‰mmer procentuellt var pÂ bilden som gÂr i marken
	@Override
	public void setFootZPos(float f) {
		setZ(f + equipments.getFirst().getTexHeight() / 2
				- equipments.getFirst().getTexHeight()
				* equipments.getFirst().getFootPosVar());
	}
	@Override
	public float getFootZPos() {
		return (equipments.getFirst().getFootZPos());
	}
	@Override
	public float getFootZPrev() {
		return (equipments.getFirst().getFootZPrev());
	}

	// grounden ‰r kort sagt i mitten av skuggan


	// ///////////////SIZES//////////
	@Override
	public float getHeight() {
		return equipments.getLast().getHeadZPos()
				- equipments.get(0).getFootZPos();

	}
	@Override
	public int getWeight() {
		int weight = 0;
		for (GIngredient gi : equipments)
			weight += gi.getWeight();
		return weight;
	}

	// //////////////DAMAGE///////////////////
	@Override
	public void aboveDamage(int amt, WorldObject attacker, GameWorld world){
		if(isDead())
			return;
		
		for (int i = equipments.size() - 1; i >= 0; i--) {
			GIngredient gi = equipments.get(i);
			if (gi instanceof HealthGiving || gi instanceof Armor) {
				gi.damage(amt);
				if(gi.getDurability() <= 0){
					gi.setDurability(0);
					if(equipments.size() > 1)
						equipments.remove(gi);
					}
				break;
			}
		}
		if (isDead())
			this.die(world);
	}
	
	@Override
	public void damage(int amt, WorldObject attacker, GameWorld world) {
		if(isDead())
			return;
		
		float attackerMidLineZPos = (attacker.getFootZPos() + attacker.getHeadZPos())/2;

		GIngredient damagedIngredient = null;
		GIngredient hitIngredient = null;

		
		
		if(equipments.getLast().getHeadZPos() <= attackerMidLineZPos)
			hitIngredient = equipments.getLast();
		else if(equipments.getFirst().getFootZPos() >= attackerMidLineZPos)
			hitIngredient = equipments.getFirst();
		else 
			for (GIngredient gi: equipments) {
				if (gi.getFootZPos() <= attackerMidLineZPos && gi.getHeadZPos() >= attackerMidLineZPos) {
					hitIngredient = gi;
					break;
				}
			}
				
		if(hitIngredient.isDamageable()){ // träffar något som kan skadas.
			damagedIngredient = hitIngredient;
		}	
		else if(attackerMidLineZPos - hitIngredient.getFootZPos() >= hitIngredient.getHeadZPos() - attackerMidLineZPos){ // närmare under
			boolean foundSomething = false;
			for (int i = equipments.indexOf(hitIngredient); i >= 0; --i){ //sök neråt
				if(equipments.get(i).isDamageable()){
						damagedIngredient = equipments.get(i);
						foundSomething = true;
						break;
					}
				}	
			if(!foundSomething)
				for (int i = equipments.indexOf(hitIngredient); i < equipments.size(); ++i){ //sök neråt
					if(equipments.get(i).isDamageable()){
							damagedIngredient = equipments.get(i);
							break;
						}
					}
			
		} else { // närmare över
			boolean foundSomething = false;
			for (int i = equipments.indexOf(hitIngredient); i < equipments.size(); ++i){ //sök neråt
				if(equipments.get(i).isDamageable()){
						damagedIngredient = equipments.get(i);
						foundSomething = true;
						break;
					}
				}	
			if(!foundSomething)
				for (int i = equipments.indexOf(hitIngredient); i >= 0; --i){ //sök neråt
					if(equipments.get(i).isDamageable()){
							damagedIngredient = equipments.get(i);
							break;
						}
					}
		}
				
		damagedIngredient.damage(amt);		
		
		if(damagedIngredient.getDurability() <= 0){			
			damagedIngredient.setDurability(0);				
			if(equipments.size() > 1)			
				equipments.remove(damagedIngredient);
		}
		
		if (isDead())
			this.die(world);
	}
	
	@Override
	public void underDamage(int amt, WorldObject attacker, GameWorld world){
		if(isDead())
			return;
		for (int i = 0; i < equipments.size(); i++) {
			GIngredient gi = equipments.get(i);
			if (gi instanceof HealthGiving || gi instanceof Armor) {
				gi.damage(amt);
				if(gi.getDurability() <= 0){
					gi.setDurability(0);
					if(equipments.size() > 1)
						equipments.remove(gi);
					}
				break;
			}
		}	
		if (isDead())
			this.die(world);
	}
	
	
	//////////////////////////////////////////
	
	// //////////////ABOUT HEALTH///////////////////
	@Override
	public int getHealth() {
		int health = 0;
		for (GIngredient gi : equipments)
			if (gi instanceof HealthGiving) {
				health += gi.getDurability();
			}
		return health;
	}
	@Override
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
	
	@Override
	public void die(GameWorld world){
		
	}


	// ///////////////////////////
	@Override
	public float getRadius() {
		float rMax = 0;
		for (GIngredient gi : equipments)
			if (gi instanceof HealthGiving || gi instanceof Armor)
				rMax = Math.max(rMax, gi.getRadius());
		return rMax;
	}
	@Override
	public float getTexHeight() {
		return equipments.getLast().getHeadZPos()
				- equipments.getFirst().getFootZPos();
	}
	@Override
	public float getTexWidth() {
		float wMax = 0;
		for (GIngredient gi : equipments)
			wMax = Math.max(wMax, gi.getTexWidth());
		return wMax;
	}

	// /////////////////////////

	
	
///////////////COLLISION//////////////////
	@Override
	public void landedOn(WorldObject go, GameWorld world){
		equipments.getFirst().landedOn(go, world);
	}
	@Override
	public void gotLandedOnBy(WorldObject go, GameWorld world){

		equipments.getLast().gotLandedOnBy(go, world);
	}
	@Override
	public void collidedWith(WorldObject go, GameWorld world){
		
	}

/////////////////////////////////////////

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



}