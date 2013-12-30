package world.objects.ingredients;

import world.GIngredient;
import world.objects.ingredients.interfaces.Activateable;

public class Cheese extends GIngredient implements Activateable{

	public Cheese(float x, float y) {
		super(x, y, 115, 57, 0.5f, 0.55f, 30, 4);
		setTexture("Cheese");
	}

	@Override
	public void useFirstAbility() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void useSecondAbility() {
		// TODO Auto-generated method stub
		
	}

}
