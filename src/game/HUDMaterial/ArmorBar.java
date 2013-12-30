package game.HUDMaterial;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;
import game.GSprite;

import org.newdawn.slick.opengl.Texture;

import world.objects.GFood;
import world.objects.food.Hamburger;

public class ArmorBar extends DisplayBar{
	
	public ArmorBar(float xPos, float yPos, float texWidth, float texHeight, String texture, GFood owner) {
		super(xPos, yPos, texWidth, texHeight, texture, owner);
	}

	public ArmorBar(float xPos, float yPos, float texWidth, float texHeight, GFood owner){
		super(xPos, yPos, texWidth, texHeight, owner);
	}
	
	public float getMax() {
		return ((Hamburger)owner).getMaxArmor();
	}

	public float getCurrent() {
		return ((Hamburger)owner).getArmor();
	}
}
