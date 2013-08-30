package worldObjects.food.ingredients;

public class Cheese extends GIngredient implements Activateable{

	public Cheese(float x, float y, float size) {
		super(x, y, size, size * 0.1f);
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
