package world.objects.ingredients;

import game.parts.GameWorld;
import world.WorldObject;
import world.objects.GFood;
import world.objects.GProjectile;
import world.objects.food.HostileFood;
import world.objects.ingredients.bases.Armor;
import world.objects.ingredients.bases.OverPartIngredient;

public class BreadTop extends OverPartIngredient implements Armor{

	public BreadTop(float x, float y) {
		super(x, y, 103,50,"breadTop", 0.34f, 0.66f, 51, 30, 90);
	}



}
