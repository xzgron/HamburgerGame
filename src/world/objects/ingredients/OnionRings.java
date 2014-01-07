package world.objects.ingredients;

import world.WorldObject;
import world.objects.GIngredient;
import world.objects.ingredients.bases.Activateable;

public class OnionRings extends GIngredient implements Activateable{

	public OnionRings(float x, float y) {
		super(x, y, 100,100,"onionRings", 0.45f, 0.55f,49,12,6);
	}

	public void useFirstAbility(WorldObject user) {

		
	}


	public void useSecondAbility(WorldObject user) {


	}

}
