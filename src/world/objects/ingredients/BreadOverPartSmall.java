package world.objects.ingredients;

import world.objects.ingredients.bases.Armor;
import world.objects.ingredients.bases.OverPartIngredient;

public class BreadOverPartSmall extends OverPartIngredient implements Armor{

	public BreadOverPartSmall(float x, float y) {
		super(x, y, 103,103,"hamburgerBreadOverPart", 0.415f, 0.585f, 51, 30, 90);
		setTexture("hamburgerBreadOverPart");
	}



}
