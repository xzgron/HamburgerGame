package world.objects.ingredients;

import game.parts.GameWorld;
import world.WorldObject;
import world.objects.GFood;
import world.objects.GProjectile;
import world.objects.food.HostileFood;
import world.objects.ingredients.bases.Armor;
import world.objects.ingredients.bases.OverPartIngredient;

public class BreadTopSpiked extends OverPartIngredient implements Armor{

	public BreadTopSpiked(float x, float y) {
<<<<<<< HEAD
		super(x, y, 153,90,"breadTopSpike", 0.34f, 0.66f, 51, 30, 200);
=======
		super(x, y, 123,70,"Breadtopspike", 0.34f, 0.66f, 51, 30, 200);
>>>>>>> FETCH_HEAD
	}
	
	@Override
	public void gotLandedOnBy(WorldObject go, GameWorld world) {
		if(go instanceof HostileFood){
				((GFood)go).underDamage(90, go,world);	
		}
	}
}
