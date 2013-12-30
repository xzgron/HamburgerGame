package world.objects.ingredients;

import world.objects.GIngredient;
import world.objects.ingredients.interfaces.Armor;

public class HamburgerBreadOverPart extends GIngredient implements Armor{

	public HamburgerBreadOverPart(float x, float y) {
		super(x, y, 103, 51, 0.415f, 0.585f, 30, 90);
		setTexture("hamburgerBreadOverPart");
	}



}
