package food;

import food.ingredients.*;

import java.util.ArrayList;

import game.GObject;

public class Hamburger extends GFood {

	ArrayList<GIngredient> ingredients = new ArrayList<GIngredient>();
	
	public Hamburger(float xPos, float yPos, float texSize) {
		super(xPos, yPos, 200, 200);
		ingredients.add(new HamburgerBreadUnderPart(xPos, yPos, texSize));
		ingredients.add(new Beef(xPos, yPos, texSize));
		ingredients.add(new HamburgerBreadOverPart(xPos, yPos, texSize));

	}


	
	public void update() {
		
	}
	
	public void render() {
		
		for(int i = 0; i < ingredients.size(); i++){
			ingredients.get(i).setPosition(getX(),getY() + i*50);
			ingredients.get(i).render();
			;
		}
	}
	

}
