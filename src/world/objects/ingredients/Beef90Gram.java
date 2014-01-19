package world.objects.ingredients;

import world.objects.GIngredient;
import world.objects.ingredients.bases.HealthGiving;

public class Beef90Gram extends GIngredient implements HealthGiving{

	public Beef90Gram(float x, float y) {
		super(x, y, 110,110,"beef", 0.44f, 0.62f, 54, 90, 90);
	}
}
