package food;

import game.GObject;

public class GFoodShadow extends GObject{
	
	GFood owner;
	
	public GFoodShadow(GFood gf) {
		super(gf.getX(), gf.getY(), gf.getTexWidth(), gf.getTexHeight());
		owner = gf;
		setTexFolder("food/");
		setTexture("shadow");
	}

	public void update() {	
		setPosition(owner.getX(), owner.getY());
		setSize(owner.getTexWidth()*(1+owner.getZ()),owner.getTexHeight()*(1+owner.getZ()));
	}

}
