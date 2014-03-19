package world.objects.ingredients;

import game.parts.GameWorld;
import world.WorldObject;
import world.objects.GFood;
import world.objects.food.HostileFood;
import world.objects.ingredients.bases.UnderPartIngredient;

public class BreadBottomSpike extends UnderPartIngredient{

	public BreadBottomSpike(float x, float y) {
		super(x, y, 153,90, "breadBottomSpike", 0.41f, 0.67f,51,30,90);
		
	}
	@Override
	public void landedOn(WorldObject go, GameWorld world) {
		if(go instanceof HostileFood){
				((GFood)go).aboveDamage(90, go,world);	
		}
	}
	

}
