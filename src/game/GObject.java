package game;
import static java.lang.Math.*;
import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;


public abstract class GObject {

	private float xPos, yPos;
	private float textureWidth;
	private float textureHeight;
	private float hitBoxRadie = -1;
	
	private float red = 1, green = 1, blue = 1;
	private float transparency = 1;
	
	private Texture texture;
	
	public GObject(float xPos, float yPos, float textureSize) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.textureWidth = textureSize;
		this.textureHeight = textureSize;
	}

	public GObject(float xPos, float yPos, float textureWidth, float textureHeight) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.textureWidth = textureWidth;
		this.textureHeight = textureHeight;
	}

	public abstract void update();

	public void render() {
		draw(xPos,yPos);
	}
	
	public void setTexture(Texture texture){
		this.texture = texture;
	}

	public void setTexture(String fileName) {
		texture = GTexture.getTexture(fileName);
	}
	

	public Texture getTexture() {
		return texture;
	}

	public void draw(float x, float y) {
		glPushMatrix();
		{
			if(texture != null)
				texture.bind();
			else
				glBindTexture(GL_TEXTURE_2D,0);

			glColor4f(red, green, blue, transparency);
			glTranslatef(x, y, 0);
			glBegin(GL_QUADS);
			{
				glTexCoord2f(0, 0);
				glVertex2f(-textureWidth/2f,textureHeight/2f);

				glTexCoord2f(1f, 0);
				glVertex2f(textureWidth/2f,textureHeight/2f);

				glTexCoord2f(1f, 1f);
				glVertex2f(textureWidth/2f,-textureHeight/2f);

				glTexCoord2f(0, 1f);
				glVertex2f(-textureWidth/2f,-textureHeight/2f);
			}
			glEnd();
		}
		glPopMatrix();
	}

	public float getX() {
		return xPos;
	}

	public float getY() {
		return yPos;
	}


	public void move(float x, float y) {
		this.xPos += x;
		this.yPos += y;
	}
	
	public void moveByAngle(float amt, float angle){
		float dx = (float) (amt * sin(toRadians(angle)));
		float dy = (float) (amt * cos(toRadians(angle)));
		move(dx,dy);
	}

	public void setPosition(float x, float y) {
		this.xPos = x;
		this.yPos = y;
	}	

	public void useColor() {
		glColor4f(red, green, blue, transparency);
	}

	public void setColor(float r, float g, float b) {
		this.red = r / 255;
		this.green = g / 255;
		this.blue = b / 255;
	}


	public void setTransparency(float transparency) {
		this.transparency = transparency;
	}
	
	public float getTextureWidth(){
		return textureWidth;
	}
	
 public float getTextureHeight(){
	 return textureHeight;
 }
	
	public float getTextureSize(){
		if(textureWidth != textureHeight)
			System.out.println("This object has is not kvadratic");
		
		return textureWidth;
	}
	

	
	public float getRadie(){
		return hitBoxRadie;
	}
	public void setRadie(float f){
		hitBoxRadie = f;
	}

}
