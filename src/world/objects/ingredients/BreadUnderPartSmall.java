package world.objects.ingredients;



import world.objects.ingredients.bases.Armor;
import world.objects.ingredients.bases.UnderPartIngredient;

public class BreadUnderPartSmall extends UnderPartIngredient implements Armor{

	public BreadUnderPartSmall(float x, float y) {
		super(x, y, 103,103, "hamburgerBreadUnderPart", 0.415f, 0.585f,51,30,90);
		
	}
}
