package gameObjects;

import ingredients.*;

import java.util.ArrayList;

import game.GObject;

public class Hamburger extends GFood {

	ArrayList<GIngredient> ingredients = new ArrayList<GIngredient>();
	
	public Hamburger(float xPos, float yPos, float texSize) {
		super(xPos, yPos, texSize, texSize);
		ingredients.add(new HamburgerBreadUnderPart(xPos, yPos, texSize));
		ingredients.add(new Beef(xPos, yPos, texSize));
		ingredients.add(new HamburgerBreadOverPart(xPos, yPos, texSize));

	}


	
	public void update() {
		
	}
	
	public void render() {
		
		for(int i = 0; i < ingredients.size(); i++){
			ingredients.get(i).render();
			ingredients.get(i).setTexPos(0,i*50);
		}
	}
	

}
