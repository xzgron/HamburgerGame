package world;

import org.newdawn.slick.opengl.Texture;

import game.GSprite;
import game.GImage;
import game.GTexture;

public class Shadow {
	
	WorldObject owner;
	
	Texture texture = GTexture.getTexture("food/shadow");
	
	public Shadow(WorldObject go) {
		owner = go;

	}
	
	public void render(){
		GImage.draw(texture, owner.getX(), owner.getGroundYPos(), owner.getTexWidth()+ (owner.getFootZPos()/2), ( owner.getTexWidth()+ (owner.getFootZPos()/2))/2, 1, 1, 1, owner.getAlpha());	
	}

}
