package world.objects.ingredients;

public class Beef extends GIngredient implements HealthGiving{

	public Beef(float x, float y) {
		super(x, y, 100, 49, 0.415f, 0.585f, 90, 90);
		setTexture("beef");
	}
}
