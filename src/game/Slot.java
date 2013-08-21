package game;

import gameObjects.GItem;

public class Slot {
	GItem item;
	float size;
	
	public void set(GItem item){
		this.item = item;
	}
	
	public GItem get(){
		return item;
	}
	
	public void render(){
		
		if (item != null)
			item.draw();
		
	}
}
