package world.objects;

import world.WorldObject;

public class GItem extends WorldObject {

	int cost = 0;
	
	public GItem(float xPos, float yPos, float texWidth, float texHeight, String texture, float footPos, float headPos) {
		super(xPos, yPos, texWidth, texHeight, texture, footPos, headPos);
	}
	
	public void click(){
		
	}
	
	public void setCost(int cost){
		this.cost = cost;
	}
	
	public int getCost(){
		return cost;
	}
}	
