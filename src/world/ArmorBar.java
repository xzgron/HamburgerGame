package world;

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
import world.objects.food.Hamburger;

public class ArmorBar extends GSprite{
	
	Hamburger owner;
	
	boolean follow = false;
	
	public ArmorBar(float xPos, float yPos, float texWidth, float texHeight,String texture, Hamburger parent) {
		super(xPos, yPos, texWidth, texHeight, texture);
		this.owner = parent;
	}

	
	public ArmorBar(float xPos, float yPos, float texWidth, float texHeight, float r, float g, float b, float t, Hamburger parent) {
		super(xPos, yPos, texWidth, texHeight, r, g, b, t);
		this.owner = parent;
	}

	public void render() {
		if(follow)
			draw(texture, xPos + owner.getX(), yPos + owner.getY() - owner.getZ(), texWidth, texHeight, red, green, blue, transparency);	
		else
			draw(texture, xPos, yPos, texWidth, texHeight, red, green, blue, transparency);	
	}
	
	
	public void draw(Texture tex, float x, float y, float w, float h,
			float r, float g, float b, float t) {
		glPushMatrix();
		{
			if (tex != null)
				tex.bind();
			else
				glBindTexture(GL_TEXTURE_2D, 0);

			glColor4f(r, g, b, t);
			glTranslatef(x, y, 0);
			glBegin(GL_QUADS);
			{
				glTexCoord2f(0, 0);
				glVertex2f(-w / 2, -h / 2);

				glTexCoord2f(owner.getArmor()/owner.getMaxArmor(), 0);
				glVertex2f(-(w / 2 - w * owner.getArmor()/owner.getMaxArmor()), -h / 2);

				glColor4f(r*0.7f, g*0.7f, b*0.7f, t);
				glTexCoord2f(-(w / 2 - w * owner.getArmor()/owner.getMaxArmor()), 1);
				glVertex2f(w / 2, h / 2);
				

				glTexCoord2f(0, 1);
				glVertex2f(-w / 2, h / 2);
			}
			glEnd();
		}
		glPopMatrix();
	}
	
	public void setFollow(boolean b){
		follow = b;
	}
	
	public int getArmor(){
		return owner.getArmor();
	}
	
	public int getMaxArmor(){
		return owner.getMaxArmor();
	}


}
