package game.HUDMaterial;

import game.GButton;

public class InventorySlot extends GButton {
	
	GItem item;
	
	float itemSize;
	
	public InventorySlot(float x, float y, float size, float itemSize) {
		super(x, y, size, size);
		this.itemSize = itemSize;
	}
	public InventorySlot(float x, float y, float size, float itemSize, String texture) {
		super(x, y, size, size, texture);
		this.itemSize = itemSize;
	}


	public void render(){
		
	}
	
	
}
