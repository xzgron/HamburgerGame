package world;

<<<<<<< HEAD
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
import game.GImage;
import game.GSprite;
import game.GTexture;

import org.newdawn.slick.opengl.Texture;

import world.objects.food.GFood;

public abstract class DisplayBar extends GSprite{
	Texture backgroundTexture = null;
	
	float backgroundRed = 0;
	float backgroundGreen = 0;
	float backgroundBlue = 0;
	float backgroundAlpha = 0;
	
	
	GFood owner;

	boolean follow = false;

	public DisplayBar(float xPos, float yPos, float texWidth, float texHeight, String texture, GFood owner) {
		super(xPos, yPos, texWidth, texHeight, texture);
		this.owner = owner;
	}

	public DisplayBar(float xPos, float yPos, float texWidth, float texHeight, GFood owner){
		super(xPos, yPos, texWidth, texHeight);
		this.owner = owner;
	}

	public abstract void render();
	
	public void draw(float full, float current) {
		if (follow){
			GImage.shadowDraw(backgroundTexture, xPos + owner.getX(),yPos + owner.getY() - owner.getZ(), texWidth, texHeight, backgroundRed, backgroundGreen, backgroundBlue, backgroundAlpha);
			GImage.drawDisplayBar(texture, xPos + owner.getX(),yPos + owner.getY() - owner.getZ(), texWidth, texHeight, red, green, blue, alpha, full, current);
		}else{
			GImage.shadowDraw(backgroundTexture, xPos ,yPos, texWidth, texHeight, backgroundRed, backgroundGreen, backgroundBlue, backgroundAlpha);
			GImage.drawDisplayBar(texture, xPos, yPos, texWidth, texHeight, red, green, blue, alpha, full, current);
			}
	}

	

	public void setFollow(boolean b) {
		follow = b;
	}

	public int getHealth() {
		return owner.getHealth();
	}

	public int getMaxHealth() {
		return owner.getMaxHealth();
	}
	
	public void setBackgroundColor(float r, float g, float b, float a){
		backgroundRed = r;
		backgroundGreen = g;
		backgroundBlue = b;
		backgroundAlpha = a;
	}
	
	public void setBackgroundTexture(Texture texture){
		this.backgroundTexture = texture;
	}
=======
public class DisplayBar {
>>>>>>> d62e047f2b14654ee8a6b16277754e787a5e7309

	public void setBackgroundTexture(String fileName) {
		this.backgroundTexture = GTexture.getTexture(texFolder + fileName);
	}
	
	public Texture getBackgroundTexture() {
		return backgroundTexture;
	}
	
	public abstract float getMax();
	public abstract float getCurrent();
	}
