package world;

import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.opengl.Texture;

import world.objects.food.GFood;

import game.*;

public class HealthBar extends GSprite {
	
	GFood owner;
	
	boolean follow = false;
	
	public HealthBar(float xPos, float yPos, float texWidth, float texHeight,String texture, GFood parent) {
		super(xPos, yPos, texWidth, texHeight, texture);
		this.owner = parent;
	}

	
	public HealthBar(float xPos, float yPos, float texWidth, float texHeight,float r, float g, float b, float t, GFood parent) {
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

		
			glTranslatef(x, y, 0);
			glBegin(GL_QUADS);
			glColor4f(1,0,0,1);
			{
				
				glVertex2f(-w / 2, -h / 2);

				glVertex2f(w / 2, -h / 2);
				
				glColor4f(0.7f,0,0,1);
				glVertex2f(w / 2 , h / 2);
				
				glVertex2f(-w / 2, h / 2);
			}
			glColor4f(r, g, b, t);
			{
				glTexCoord2f(0, 0);
				glVertex2f(-w / 2, -h / 2);

				glTexCoord2f(owner.getHealth()/owner.getMaxHealth(), 0);
				glVertex2f(-(w / 2 - w * owner.getHealth()/owner.getMaxHealth()), -h / 2);

				glColor4f(r*0.7f, g*0.7f, b*0.7f, t);
				glTexCoord2f(owner.getHealth()/owner.getMaxHealth(), 1);
				glVertex2f(-(w / 2 - w * owner.getHealth()/owner.getMaxHealth()), h / 2);
				

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
	
	public int getHealth(){
		return owner.getHealth();
	}
	
	public int getMaxHealth(){
		return owner.getMaxHealth();
	}

}
