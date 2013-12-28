package world.objects.food;

import java.util.LinkedList;


import world.HealthBar;
import world.WorldObject;
import world.objects.ingredients.*;
import game.GSprite;
import game.GPhysics;
import game.Game;
import game.parts.GameWorld;

public class Hamburger extends GFood {

	LinkedList<GIngredient> ingredients = new LinkedList<GIngredient>();
	
	public Hamburger(float xPos, float yPos, float texSize) {
		super(xPos, yPos, 0, 0, 0, 0, 0);
		setRadius(texSize/2);
		
		ingredients.add(new HamburgerBreadUnderPart(xPos, yPos));

		//ingredients.add(new UnionRings(xPos, yPos));
		ingredients.add(new Beef(xPos, yPos));
		ingredients.add(new Sallad(xPos, yPos));
		ingredients.add(new Cheese(xPos, yPos));
		ingredients.add(new HamburgerBreadOverPart(xPos, yPos));
		createShadow();
		
		setWalkingSpeed(200);
		setJumpingSpeed(400);
	}

	public void update() {
		super.update();
		updateIngredients();
	}

	public void render() {
		updateIngredients();
		renderShadow();
		
		for(GIngredient gi: ingredients)
			gi.render();
	}
	
	
	public void updateIngredients(){
		ingredients.get(0).setPosition(getX(),getY(),getZ());
		
		float totalHeight = ingredients.get(0).getHeight();
		
		for (int i = 1; i < ingredients.size(); i++){
			GIngredient gi = ingredients.get(i);
			
			gi.setPosition(getX(),getY(),getZ() + totalHeight + gi.getZ() - gi.getFootZPos());
			
			totalHeight += gi.getHeight();

		}	
	}
	
	
	
	/////////////////////////////HEADS AND FOOTS////////////////////////////
	
	// huvudet bestämmer procentuellt var på bilden som går i taget...?
	
	public void setHeadZPos(float f){
		setZ(f + ingredients.getLast().getTexHeight()/2 - ingredients.getLast().getTexHeight()*ingredients.getLast().getHeadPosVar());
	}
	
	public float getHeadZPos(){
		return(ingredients.getLast().getHeadZPos());
	}
	
	public float getHeadZPrev() {
		return(ingredients.getLast().getHeadZPrev());
	}
	
	
	// foten bestämmer procentuellt var på bilden som går i marken

	
	public void setFootZPos(float f){
		setZ(f + ingredients.getFirst().getTexHeight()/2 - ingredients.getFirst().getTexHeight()*ingredients.getFirst().getFootPosVar());
	}

	
	public float getFootZPos(){
		return(ingredients.getFirst().getFootZPos());	}
	
	
	public float getFootZPrev() {
		return(ingredients.getFirst().getFootZPrev());	}
	
	//grounden är kort sagt i mitten av skuggan
	
	//////////////GROUND POSITION////////////////////////
	
	public void setGroundYPos(float y){
		yPos = (y + ingredients.getFirst().getTexHeight()/2 - ingredients.getFirst().getTexHeight()*ingredients.getFirst().getFootPosVar());
	}
	
	public float getGroundYPos(){
		return(ingredients.getFirst().getGroundYPos());
	}
	

	public float getGroundYPrev() {
		return(ingredients.getFirst().getGroundYPrev());
	}
	
	
	////////////////////////////////////////////////////////////////////////
	
	
	/////////////////SIZES//////////
	
	public float getHeight(){
		return ingredients.getLast().getHeadZPos() - ingredients.get(0).getFootZPos();
		
	}
	
	public int getWeight(){
		int weight = 0;
		for(GIngredient gi: ingredients)
			weight += gi.getWeight();
		return weight;
	}
	
	
	////////////////ABOUT HEALTH///////////////////
	
	public void damage(int amt){
		for(GIngredient gi: ingredients)
			if(gi instanceof HealthGiving){
				gi.use(amt);
				break;
				}
		if (getHealth() <= 0) {
			setHealth(0);
			this.die();
		}
		
	}
	public int getHealth(){
		int health = 0;
		for(GIngredient gi: ingredients)
			if(gi instanceof HealthGiving){
				health += gi.getDurability();
			}
		return health;
	}
	
	public int getMaxHealth(){
		int health = 0;
		for(GIngredient gi: ingredients)
			if(gi instanceof HealthGiving){
				health += gi.getMaxDurability();
			}
		return health;
	}
	
	public int getArmor(){
		int armor = 0;
		for(GIngredient gi: ingredients)
			if(gi instanceof Armor){
				armor += gi.getDurability();
			}
		return armor;
	}
	
	public int getMaxArmor(){
		int armor = 0;
		for(GIngredient gi: ingredients)
			if(gi instanceof Armor){
				armor += gi.getMaxDurability();
			}
		return armor;
	}
	
	
	/////////////////////////////
	
	public float getRadius(){
		float rMax = 0;
		for(GIngredient gi: ingredients)
			if(gi instanceof HealthGiving || gi instanceof Armor)
				rMax = Math.max(rMax,gi.getRadius());
		return rMax;
	}
	
	public float getTexHeight(){
		return ingredients.getLast().getHeadZPos() - ingredients.getFirst().getFootZPos();
	}
	
	public float getTexWidth(){
		return getRadius()*2;
		
	}
	
	///////////////////////////
	
	///////////////////COMBAT/////////////////
	
	public void landedOn(WorldObject go){
		if(go instanceof GFood){
			jump();
			((GFood)go).damage(30);
		}
	}
}