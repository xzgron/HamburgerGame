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
		setSize(owner.getTexWidth()*owner.getZ()/100,owner.getTexHeight()*owner.getZ()/100);
	}

}
