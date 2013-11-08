package worldObjects.food.ingredients;

public class Sallad extends GIngredient implements Activateable{

	public Sallad(float x, float y) {
		super(x, y, 120, 5, 0.45f, 0.55f,40);
		setTexture("sallad");
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
