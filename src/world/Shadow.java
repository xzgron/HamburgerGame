package world;

import org.newdawn.slick.opengl.Texture;

import game.GPhysics;
import game.GSprite;
import game.GImage;
import game.GTexture;

public class Shadow {
	
	WorldObject owner;
	
	static Texture texture = GTexture.getTexture("food/shadow");
	
	public Shadow(WorldObject go) {
		owner = go;

	}
	
	public void render(){
		GImage.draw(texture, owner.getX(), owner.getY(), owner.getTexWidth()+ (owner.getFootZPos()/2), ( owner.getTexWidth()+ (owner.getFootZPos()/2))/GPhysics.viewPoint, 1, 1, 1, owner.getAlpha());	
	}

	public float getRadius(){
		return (owner.getTexWidth()+ (owner.getFootZPos()/2))/2;
	}

}
