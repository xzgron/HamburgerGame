package world.objects;

import java.util.LinkedList;

import world.WorldObject;
import world.objects.ingredients.bases.Armor;
import world.objects.ingredients.bases.HealthGiving;

import game.GSprite;

//Ingredients är npgot som hamburgaren kan ha på sig

public abstract class GIngredient extends GItem{

	
	int maxDurability;
	int durability;
	
	public GIngredient(float xPos, float yPos, float texWidth, float texHeight, String texture,  float footPos, float headPos, float radius, int weight, int durability) {
		super(xPos,  yPos,  texWidth, texHeight, "ingredients/"+texture,  footPos,  headPos);
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
	
	public boolean isPositionLegal(LinkedList<GIngredient> list){
		return true;
	}
	
	public boolean isDamageable(){
		return (this instanceof Armor || this instanceof HealthGiving);
		
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
	
	public boolean use(int amt){ // returnerar om det gick
		if(durability - amt >= 0){
			durability -= amt;
			return true;
			}
		return false;
			
	}
	
	public boolean recover(int amt){ // returnerar om det gick
		if(durability+amt <= maxDurability){
			durability += amt;
			return true;
		}
		return false;
	}
	
	public boolean isEmpty(){
		if(durability == 0)
			return true;
		else
			return false;
	}
	
	public boolean isFull(){
		if(durability == maxDurability)
			return true;
		else
			return false;
	}
	
	
	
}
