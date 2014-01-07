package world.objects.ingredients;

import world.WorldObject;
import world.objects.GIngredient;
import world.objects.ingredients.bases.Activateable;
import world.objects.ingredients.bases.ShiftClickAble;

public class Sallad extends GIngredient implements Activateable, ShiftClickAble{

	boolean activated = false;
	public Sallad(float x, float y) {
		super(x, y, 120,120,"sallad", 0.45f, 0.55f,50, 10, -1);
		setTexture("sallad");
	}


	public void update(){
		if(!activated)
			setSize(120,120);
		activated = false;
	}
	
	public void useFirstAbility(WorldObject user) {
		activated = true;
		setSize(200,200);
		if(user.getZSpeed() < 0)
			user.setZSpeed(user.getZSpeed()/2);
		
	}

	public void useSecondAbility(WorldObject user) {
		
	}
	
	public void useShiftAbility(WorldObject user){
		useFirstAbility(user);
	}

}
