package worldObjects.food;

import org.newdawn.slick.opengl.Texture;

import game.GObject;
import game.GSprite;
import game.GTexture;
import game.GWorldObject;

public class GFoodShadow {
	
	GWorldObject owner;
	
	Texture texture = GTexture.getTexture("food/shadow");
	
	public GFoodShadow(GWorldObject go) {
		owner = go;

	}
	
	public void render(){
		GSprite.draw(texture, owner.getX(), owner.getGroundYPos(), owner.getTexWidth()+ (owner.getFootZPos()/2), ( owner.getTexWidth()+ (owner.getFootZPos()/2))/2, 1, 1, 1, owner.getTransparency());	
	}

}
