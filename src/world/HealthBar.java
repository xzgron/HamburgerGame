package world;

import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.opengl.Texture;

import world.objects.food.GFood;
import world.objects.food.Hamburger;

import game.*;

public class HealthBar extends DisplayBar {
	
	public HealthBar(float xPos, float yPos, float texWidth, float texHeight, String texture, GFood owner) {
		super(xPos, yPos, texWidth, texHeight, texture, owner);
	}

	public HealthBar(float xPos, float yPos, float texWidth, float texHeight, GFood owner){
		super(xPos, yPos, texWidth, texHeight, owner);
	}
	
	public float getMax() {
		return owner.getMaxHealth();
	}

	public float getCurrent() {
		return owner.getHealth();
	}

}
