package world.objects.ingredients;

import options.Controlls;
import game.tools.GMouse;
import game.tools.GTimer;
import world.WorldObject;
import world.objects.GIngredient;
import world.objects.ingredients.bases.Activateable;
import world.objects.ingredients.bases.ShiftClickAble;

public class Sallad extends GIngredient implements Activateable, ShiftClickAble{

	boolean activated = false;
	
	GTimer flyTimer = new GTimer(0.25f);
	public Sallad(float x, float y) {
		super(x, y, 120, 60,"sallad", 0.40f, 0.60f,50, 10, -1);
	}

	public void update(){
		if(getTexWidth() > 120)
			setSize(getTexWidth()-10,getTexHeight()-5);
		activated = false;
	}
	
	public void useFirstAbility(WorldObject user) {
		activated = true;
		if(getTexWidth() < 200)
			setSize(200,100);
		if(user.getZSpeed() < 0)
			user.setZSpeed(user.getZSpeed()/2);
		
	}

	public void useSecondAbility(WorldObject user) {
		if(flyTimer.hasExceeded()){
			setSize(250,125);
			user.setZSpeed(user.getZSpeed() + 30000/user.getWeight());
			flyTimer.reset();
		}
	}
	
	public void useShiftAbility(WorldObject user){
		if(!activated)
			useFirstAbility(user);
	}

}
