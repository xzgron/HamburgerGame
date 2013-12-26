package world.objects.food;

import java.util.LinkedList;


import world.HealthBar;
import world.objects.ingredients.*;
import game.GSprite;
import game.GPhysics;
import game.Game;
import game.parts.GameWorld;

public class Hamburger extends GFood {

	LinkedList<GIngredient> ingredients = new LinkedList<GIngredient>();
	
	public Hamburger(float xPos, float yPos, float texSize) {
		super(xPos, yPos, texSize, 0, 0.415f, 0, 500);
		setRadie(texSize/2);
		
		ingredients.add(new HamburgerBreadUnderPart(xPos, yPos));

		//ingredients.add(new UnionRings(xPos, yPos));
		ingredients.add(new Beef(xPos, yPos));
		ingredients.add(new Sallad(xPos, yPos));
		ingredients.add(new Cheese(xPos, yPos));
		ingredients.add(new HamburgerBreadOverPart(xPos, yPos));
		createShadow();
	}

	public void update() {
		super.update();
		
		
	}

	public void render() {
		updateIngredients();
		renderShadow();
		
		for(GIngredient gi: ingredients)
			gi.render();
	}
	
	
	public float getWeight(){
		float weight = 0;
		for(GIngredient gi: ingredients)
			weight += gi.getWeight();
		return weight;
	}
	
	public void updateIngredients(){
		
		
		ingredients.get(0).setPosition(getX(),getY(),getZ());
		
		float totalHeight = ingredients.get(0).getHeight();
		
		for (int i = 1; i < ingredients.size(); i++){
			GIngredient gi = ingredients.get(i);
			
			gi.setPosition(getX(),getY(),getZ() + totalHeight + gi.getOrigoAndFootDiff());
			
			totalHeight +=  gi.getHeight();

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
	
	public float getPrevHeadZPos() {
		return(ingredients.getLast().getPrevHeadZPos());
	}
	
	
	// foten bestämmer procentuellt var på bilden som går i marken

	
	public void setFootPos(float f){
		setZ(f + ingredients.getFirst().getTexHeight()/2 - ingredients.getFirst().getTexHeight()*ingredients.getFirst().getFootPosVar());
	}

	
	public float getFootZPos(){
		return(ingredients.getFirst().getFootZPos());	}
	
	
	public float getPrevFootZPos() {
		return(ingredients.getFirst().getPrevFootZPos());	}
	
	//grounden är kort sagt i mitten av skuggan
	
	public void setGroundPos(float y){
		yPos = y - texHeight/2 + texHeight*getFootPosVar();
	}
	
	public float getGroundPos(){
		return(yPos - texHeight/2 + texHeight*getFootPosVar());
	}
	

	public float getPrevGroundPos() {
		return(getPrevYPos() - texHeight/2 + texHeight*getFootPosVar());
	}
	
	// höjden är höjden i z mellan huvud och fot
	
	public float getHeight(){
		return ingredients.getLast().getHeadZPos() - ingredients.get(0).getFootZPos();
		
	}
	
	public float getOrigoAndFootDiff(){
		return texHeight/2-texHeight*getFootPosVar();
	}
	
}
