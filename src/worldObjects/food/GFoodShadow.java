package worldObjects.food;

import game.GObject;
import game.GWorldObject;

public class GFoodShadow extends GObject{
	
	GFood owner;
	
	public GFoodShadow(GFood gf) {
		super(gf.getX(), gf.getY(), gf.getTexWidth(), gf.getTexHeight());
		owner = gf;
		setTexFolder("food/");
		setTexture("shadow");
	}

	public void update() {	
		setPosition(owner.getX(), owner.getFootPos());
		setSize(owner.getTexWidth() + (owner.getZ()/2),owner.getTexHeight()+(owner.getZ())/2);
	}

}
