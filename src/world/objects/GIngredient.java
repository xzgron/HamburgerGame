package world.objects;

import java.util.LinkedList;

import world.WorldObject;

import game.GSprite;

//Ingredients är npgot som hamburgaren kan ha på sig

public abstract class GIngredient extends GItem{

	
	int maxDurability;
	int durability;
	
	public GIngredient(float xPos, float yPos, float texSize, float radius, float footPos, float headPos, int weight, int durability) {
		super(xPos,  yPos,  texSize, texSize,  footPos,  headPos);
		setTexFolder("ingredients/");
		
		setWeight(weight);
		setRadius(radius);
		
		this.maxDurability = durability;
		this.durability = maxDurability;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isPositionLegal(LinkedList<GIngredient> list, int requestedPosition){
		return true;
	}
	
	public int getMaxDurability(){
		return maxDurability;
	}
	
	public int getDurability(){
		return durability;
	}
	
	public void setDurability(int i) {
		durability = i;
	}
	
	public void use(float amt){
		durability -= amt;
	}
}
