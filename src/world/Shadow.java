package world;

import org.lwjgl.opengl.GL11;
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
		GImage.draw(texture, owner.getX(), owner.getY(), getTexWidth(), getTexHeight(), 1, 1, 1, owner.getAlpha());	
	}

	public float getRadius(){
		return getTexWidth()/2;
	}
	public float getTexHeight(){
		return getTexWidth()/GPhysics.viewPoint;
	}
	public float getTexWidth(){
		return (float)(owner.getTexWidth()+owner.getFootZPos()/3);
	}

}
