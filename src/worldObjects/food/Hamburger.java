package worldObjects.food;

import java.util.ArrayList;

import worldObjects.food.ingredients.*;
import game.GObject;

public class Hamburger extends GFood {

	ArrayList<GIngredient> ingredients = new ArrayList<GIngredient>();
	
	public Hamburger(float xPos, float yPos, float texSize) {
		super(xPos, yPos, texSize, texSize);
		ingredients.add(new HamburgerBreadUnderPart(xPos, yPos, texSize));
		ingredients.add(new Beef(xPos, yPos, texSize));
		ingredients.add(new HamburgerBreadUnderPart(xPos, yPos, texSize));
		ingredients.add(new Beef(xPos, yPos, texSize));
		ingredients.add(new HamburgerBreadOverPart(xPos, yPos, texSize));
		createShadow();
	}


	
	public void update() {
		updateShadow();		
	}
	
	public void render() {
		
		renderShadow();
		float totalHeight = 0;
		for(int i = 0; i < ingredients.size(); i++){
			ingredients.get(i).setPosition(getX(),getY() + totalHeight + getZ());
			ingredients.get(i).render();
	
			totalHeight += ingredients.get(i).getHeight();
		}
	}
	

}
