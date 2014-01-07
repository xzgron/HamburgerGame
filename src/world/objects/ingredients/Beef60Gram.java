package world.objects.ingredients;

import world.objects.GIngredient;
import world.objects.ingredients.bases.HealthGiving;

public class Beef60Gram extends GIngredient implements HealthGiving{

	public Beef60Gram(float x, float y) {
		super(x, y, 100, 100, "beef", 0.415f, 0.585f, 49 ,60, 60);
	}
}
