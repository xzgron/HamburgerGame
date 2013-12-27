package game;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.ResourceLoader;

import static org.lwjgl.opengl.GL11.*;

public class GImage {

	
	public static void draw(Texture tex, float x, float y, float w, float h){
		glPushMatrix();
		{
			if (tex != null)
				tex.bind();
			else
				glBindTexture(GL_TEXTURE_2D, 0);
			glColor4f(1,1,1,1);
			glTranslatef(x, y, 0);
			glBegin(GL_QUADS);
			{
				glTexCoord2f(0, 0);
				glVertex2f(-w / 2, -h / 2);

				glTexCoord2f(1, 0);
				glVertex2f(w / 2, -h / 2);
				
				glTexCoord2f(1, 1);
				glVertex2f(w / 2, h / 2);
				

				glTexCoord2f(0, 1);
				glVertex2f(-w / 2, h / 2);
			}
			glEnd();
		}
		glPopMatrix();
	}
	
	
	
	public static void draw(Texture tex, float x, float y, float w, float h,
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

				glTexCoord2f(1, 0);
				glVertex2f(w / 2, -h / 2);
				
				glColor4f(r*0.7f, g*0.7f, b*0.7f, t);
				glTexCoord2f(1, 1);
				glVertex2f(w / 2, h / 2);
				

				glTexCoord2f(0, 1);
				glVertex2f(-w / 2, h / 2);
			}
			glEnd();
		}
		glPopMatrix();
	}
}