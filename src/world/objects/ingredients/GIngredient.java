package world.objects.ingredients;

import world.WorldObject;
import world.objects.food.GFood;
import game.GSprite;



public abstract class GIngredient extends WorldObject{

	int maxDurability;
	int durability;
	
	public GIngredient(float xPos, float yPos, float texSize, float radius, float footPos, float headPos, int weight, int durability) {
		super(xPos,  yPos,  texSize,  footPos,  headPos);
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
