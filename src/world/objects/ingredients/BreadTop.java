package world.objects.ingredients;

import world.objects.ingredients.bases.Armor;
import world.objects.ingredients.bases.OverPartIngredient;

public class BreadTop extends OverPartIngredient implements Armor{

	public BreadTop(float x, float y) {
		super(x, y, 103,50,"breadTop", 0.34f, 0.66f, 51, 30, 90);
	}



}
