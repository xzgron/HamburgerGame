package world.objects.ingredients;

import world.GIngredient;
import world.objects.ingredients.interfaces.HealthGiving;

public class Beef90Gram extends GIngredient implements HealthGiving{

	public Beef90Gram(float x, float y) {
		super(x, y, 110, 49, 0.415f, 0.585f, 90, 90);
		setTexture("beef");
	}
}
