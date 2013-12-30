package world.objects.ingredients;

import world.GIngredient;
import world.objects.ingredients.interfaces.HealthGiving;

public class Beef60Gram extends GIngredient implements HealthGiving{

	public Beef60Gram(float x, float y) {
		super(x, y, 100, 49, 0.415f, 0.585f, 60, 60);
		setTexture("beef");
	}
}
