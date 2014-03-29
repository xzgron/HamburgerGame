package world.objects.ingredients;

import game.GPhysics;
import game.parts.GameWorld;
import world.WorldObject;
import world.objects.GFood;
import world.objects.GProjectile;
import world.objects.food.HostileFood;
import world.objects.ingredients.bases.Armor;
import world.objects.ingredients.bases.OverPartIngredient;

public class BreadTopSpiked extends OverPartIngredient implements Armor{

	public BreadTopSpiked(float x, float y) {
		super(x, y, 123,70,"Breadtopspike", 0.34f, 0.66f, 51, 30, 200);
	}
	
	@Override
	public void gotLandedOnBy(WorldObject go, GameWorld world) {
		if(go instanceof HostileFood){
				((GFood)go).underDamage((int) GPhysics.calculateDamage((Math.abs(go.getZSpeed()-this.getZSpeed())), go.getWeight(), 1), go,world);	
		}
	}
}
