package worldObjects.food;

import java.util.ArrayList;

import worldObjects.food.ingredients.*;
import game.GObject;
import game.GPhysics;
import game.GWorld;
import game.Game;

public class Hamburger extends GFood {

	ArrayList<GIngredient> ingredients = new ArrayList<GIngredient>();

	public Hamburger(float xPos, float yPos, float texSize) {
		super(xPos, yPos, texSize, texSize);

		ingredients.add(new HamburgerBreadUnderPart(xPos, yPos, texSize));
		ingredients.add(new Cheese(xPos, yPos, texSize));
		ingredients.add(new UnionRings(xPos, yPos, texSize));
		ingredients.add(new Beef(xPos, yPos, texSize));
		ingredients.add(new Sallad(xPos, yPos, texSize));
		ingredients.add(new HamburgerBreadOverPart(xPos, yPos, texSize));
		createShadow();
		setFootPos(0.4f);
	}


	public void update() {

		
		
		GPhysics.handleGravity(this);
		if (getZ() == 0 && walking)
			setZSpeed(4);
		updateShadow();
	}

	public void render() {

		renderShadow();
		float totalHeight = 0;
		for (int i = 0; i < ingredients.size(); i++) {
			if (i != 0)
				totalHeight += ingredients.get(i).getHeight();
			ingredients.get(i).setPosition(getX(),
					getY() + totalHeight + getZ());
			ingredients.get(i).render();

		}
	}

}
