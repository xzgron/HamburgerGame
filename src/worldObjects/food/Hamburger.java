package worldObjects.food;

import java.util.LinkedList;

import worldObjects.food.ingredients.*;
import game.GObject;
import game.GPhysics;
import game.GWorld;
import game.Game;

public class Hamburger extends GFood {

	LinkedList<GIngredient> ingredients = new LinkedList<GIngredient>();

	public Hamburger(float xPos, float yPos, float texSize) {
		super(xPos, yPos, texSize, 0, 0.415f, 0);

		ingredients.add(new HamburgerBreadUnderPart(xPos, yPos));

		//ingredients.add(new UnionRings(xPos, yPos));
		ingredients.add(new Beef(xPos, yPos));
		ingredients.add(new Cheese(xPos, yPos));
		//ingredients.add(new Sallad(xPos, yPos));
		ingredients.add(new HamburgerBreadOverPart(xPos, yPos));
		createShadow();
	}


	public void update() {

		//setSize(100 + getZ()/2, 100 + getZ()/2);
		
		GPhysics.handleGravity(this);
		
		if (getZ() == 0 && walking)
			setZSpeed(4);
		
		updateIngredients();

		updateShadow();
	}

	public void render() {

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
	
	
	
	public float getHeadPos(){
		return ingredients.getLast().getHeadPos();
	}
	
	public float getFootPos(){
		return ingredients.getFirst().getFootPos();
	}
}
