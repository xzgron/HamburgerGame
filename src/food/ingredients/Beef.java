package food.ingredients;

public class Beef extends GIngredient{

	public Beef(float x, float y, float size) {
		super(x, y, size, size * 0.2f);
		setTexture("beef");
	}
}