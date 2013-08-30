package worldObjects.food.ingredients;

public class Sallad extends GIngredient implements Activateable{

	public Sallad(float x, float y, float size) {
		super(x, y, size, size *0.1f);
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
