package world.objects.ingredients;

import world.objects.ingredients.bases.Armor;
import world.objects.ingredients.bases.UnderPartIngredient;

public class BreadUnderPartSuperSize extends UnderPartIngredient implements Armor{

	public BreadUnderPartSuperSize(float xPos, float yPos) {
		super(xPos, yPos, 300,300,"hamburgerBreadUnderPart" , 0.41f, 0.67f,150,10,400);
	}

}
