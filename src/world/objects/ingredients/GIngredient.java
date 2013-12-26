package world.objects.ingredients;

import world.WorldObject;
import world.objects.food.GFood;
import game.GSprite;



public abstract class GIngredient extends GFood{

	boolean heldByMouse;
	
	public GIngredient(float xPos, float yPos, float texSize, float weight, float headPos, float footPos, int health) {
		super( xPos,  yPos,  texSize,  weight,  headPos,  footPos, health);
		setTexFolder("food/ingredients/");
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
