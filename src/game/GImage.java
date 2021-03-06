package game;

import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;

public class GImage {

	public static void draw(Texture tex, float x, float y, float w, float h) {
		glPushMatrix();
		{
			if (tex != null)
				tex.bind();
			else
				glBindTexture(GL_TEXTURE_2D, 0);
			
			glColor4f(1, 1, 1, 1);
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

				glTexCoord2f(1, 1);
				glVertex2f(w / 2, h / 2);

				glTexCoord2f(0, 1);
				glVertex2f(-w / 2, h / 2);
			}
			glEnd();
		}
		glPopMatrix();
	}

	public static void shadowDraw(Texture tex, float x, float y, float w,
			float h, float r, float g, float b, float t) {
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

				glColor4f(r * 0.7f, g * 0.7f, b * 0.7f, t);
				glTexCoord2f(1, 1);
				glVertex2f(w / 2, h / 2);

				glTexCoord2f(0, 1);
				glVertex2f(-w / 2, h / 2);
			}
			glEnd();
		}
		glPopMatrix();
	}

	public static void drawDisplayBar(Texture tex, float x, float y, float w,
			float h, float r, float g, float b, float t, float full,
			float current) {
		glPushMatrix();
		{
			if (tex != null)
				tex.bind();
			else
				glBindTexture(GL_TEXTURE_2D, 0);

			glTranslatef(x, y, 0);
			glBegin(GL_QUADS);
			glColor4f(r, g, b, t);
			{
				glTexCoord2f(0, 0);
				glVertex2f(-w / 2, -h / 2);
				glTexCoord2f(current / full, 0);
				glVertex2f(-(w / 2 - w * current / full), -h / 2);

				glColor4f(r * 0.7f, g * 0.7f, b * 0.7f, t);
				glTexCoord2f(current / full, 1);
				glVertex2f(-(w / 2 - w * current / full), h / 2);

				glTexCoord2f(0, 1);
				glVertex2f(-w / 2, h / 2);
			}
			glEnd();
		}
		glPopMatrix();
	}
}