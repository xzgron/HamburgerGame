package ingredients;

import game.GObject;



public abstract class GIngredient extends GObject {

	boolean heldByMouse;
	
	
	public GIngredient(float x, float y,float size) {
		super(x, y, size,size);
		setTexFolder("food/");
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
