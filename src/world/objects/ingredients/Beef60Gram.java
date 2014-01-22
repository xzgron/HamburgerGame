package world.objects.ingredients;

import world.objects.GIngredient;
import world.objects.ingredients.bases.HealthGiving;

public class Beef60Gram extends GIngredient implements HealthGiving{

	public Beef60Gram(float x, float y) {
		super(x, y, 100,50,  "beef", 0.33f, 0.67f, 49 ,60, 60);
	}
}
