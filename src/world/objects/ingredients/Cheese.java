package world.objects.ingredients;

public class Cheese extends GIngredient implements Activateable{

	public Cheese(float x, float y) {
		super(x, y, 115, 10, 0.5f, 0.55f,30);
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
