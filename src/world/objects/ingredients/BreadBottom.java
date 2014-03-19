package world.objects.ingredients;


import world.objects.ingredients.bases.Armor;

import world.objects.ingredients.bases.UnderPartIngredient;

public class BreadBottom extends UnderPartIngredient implements Armor{

	public BreadBottom(float x, float y) {
		super(x, y, 103,60, "breadBottom", 0.41f, 0.67f,51,30,90);
		
	}
}
