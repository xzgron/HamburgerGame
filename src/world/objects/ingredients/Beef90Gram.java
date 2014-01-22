package world.objects.ingredients;

import world.objects.GIngredient;
import world.objects.ingredients.bases.HealthGiving;

public class Beef90Gram extends GIngredient implements HealthGiving{

	public Beef90Gram(float x, float y) {
		super(x, y, 110,55,"beef", 0.33f, 0.67f, 54, 90, 90);
	}
}
