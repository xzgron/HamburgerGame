package actionbar;

import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.opengl.Texture;

import worldObjects.food.GFood;

import game.*;

public class MeasureBar extends GSprite {
	
	GFood parent;
	
	boolean follow = false;
	
	public MeasureBar(float xPos, float yPos, float texWidth, float texHeight,String texture, GFood parent) {
		super(xPos, yPos, texWidth, texHeight, texture);
		this.parent = parent;
	}

	
	public MeasureBar(float xPos, float yPos, float texWidth, float texHeight,
			float r, float g, float b, float t, GFood parent) {
		super(xPos, yPos, texWidth, texHeight, r, g, b, t);
		this.parent = parent;
	}

	public void render() {
		if(follow)
			draw(texture, xPos + parent.getX(), yPos + parent.getY() - parent.getZ(), texWidth, texHeight, red, green, blue, transparency);	
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

				glTexCoord2f(parent.getHealth()/parent.getMaxHealth(), 0);
				glVertex2f(w / 2, -h / 2);

				
				glTexCoord2f(parent.getHealth()/parent.getMaxHealth(), 1);
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

}
