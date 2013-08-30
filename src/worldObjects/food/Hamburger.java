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
		super(xPos, yPos, texSize, texSize);

		//ingredients.add(new HamburgerBreadUnderPart(xPos, yPos, texSize));

		//ingredients.add(new UnionRings(xPos, yPos, texSize));
		ingredients.add(new Beef(xPos, yPos, texSize));
		ingredients.add(new Cheese(xPos, yPos, texSize));
		//ingredients.add(new Sallad(xPos, yPos, texSize));
		//ingredients.add(new HamburgerBreadOverPart(xPos, yPos, texSize));
		createShadow();
		setFootPos(0.4f);
	}


	public void update() {

		setSize(100 + getZ()/2, 100 + getZ()/2);
		
		GPhysics.handleGravity(this);
		if (getZ() == 0 && walking)
			setZSpeed(4);
		updateShadow();
	}

	public void render() {

		renderShadow();
		
		for (GIngredient gi: ingredients) {
			if (i != 0)
				totalHeight += ingredients.get(i).getHeight();
			ingredients.get(i).setPosition(getX(),
					getY() + totalHeight + getZ());
			ingredients.get(i).render();

		}
	}
	
	
	public float getHeadPos(){
		return ingredients.getLast().getHeadPos();
	}
	
	public float getFootPos(){
		return ingredients.getFirst().getFootPos();
	}
	
	public void updateIngredients(){
		for(GIngredient gi: ingredients){
			gi.setPosition(getX(), getY(), getZ());
			
			
		}
		
		
	}
}
