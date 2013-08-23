package food.ingredients;

import game.GObject;



public abstract class GIngredient extends GObject {

	boolean heldByMouse;
	
	private final float height;
	
	public GIngredient(float x, float y, float size, float height) {
		super(x, y, size,size);
		this.height = height;
		setTexFolder("food/ingredients/");
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	
	public float getHeight(){
		return height;
	}
}
