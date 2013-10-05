package game;

import ingredients.GIngredient;

public class Slot {
	GIngredient item;
	float size;
	
	public void set(GIngredient item){
		this.item = item;
	}
	
	public GIngredient get(){
		return item;
	}
	
	public void render(){
		
		if (item != null)
			item.draw();
		
	}
}
