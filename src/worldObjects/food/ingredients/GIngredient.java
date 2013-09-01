package worldObjects.food.ingredients;

import game.GObject;
import game.GWorldObject;



public abstract class GIngredient extends GWorldObject {

	boolean heldByMouse;
	
	public GIngredient(float xPos, float yPos, float texSize, float weight, float headPos, float footPos) {
		super( xPos,  yPos,  texSize,  weight,  headPos,  footPos);
		setTexFolder("food/ingredients/");
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
