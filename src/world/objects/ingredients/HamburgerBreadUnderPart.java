package world.objects.ingredients;

import world.GIngredient;
import world.objects.ingredients.interfaces.Armor;

public class HamburgerBreadUnderPart extends GIngredient implements Armor{

	public HamburgerBreadUnderPart(float x, float y) {
		super(x, y, 103, 51, 0.415f, 0.585f,30,90);
		setTexture("hamburgerBreadUnderPart");
	}
}
