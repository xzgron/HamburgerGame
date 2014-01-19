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
import game.GImage;
import game.GSprite;
import game.GTexture;
import game.tools.GTimer;

import org.newdawn.slick.opengl.Texture;

import world.objects.GFood;

public abstract class DisplayBar extends GSprite{
	Texture backgroundTexture = null;
	
	GTimer damageColorTimer = new GTimer(-1);
	float prevCurrent;
	
	float backgroundRed = 1;
	float backgroundGreen = 1;
	float backgroundBlue = 1;
	float backgroundAlpha = 0;
	
	float damageRed = 1;
	float damageGreen = 1;
	float damageBlue = 1;
	float damageAlpha = 0;
	
	float tempRed;
	float tempGreen;
	float tempBlue;
	float tempAlpha;
	
	GFood owner;

	boolean follow = false;

	public DisplayBar(float xPos, float yPos, float texWidth, float texHeight, String texture, GFood owner) {
		super(xPos, yPos, texWidth, texHeight, texture);
		this.owner = owner;
		prevCurrent = owner.getHealth();
		tempRed = this.getRed();
		tempGreen = this.getGreen();
		tempBlue = this.getBlue();
		tempAlpha = this.getAlpha();
	}

	public DisplayBar(float xPos, float yPos, float texWidth, float texHeight, GFood owner){
		super(xPos, yPos, texWidth, texHeight);
		this.owner = owner;
		prevCurrent = owner.getHealth();
		tempRed = this.getRed();
		tempGreen = this.getGreen();
		tempBlue = this.getBlue();
		tempAlpha = this.getAlpha();
	}
	
	public void update(){
		if(prevCurrent > getCurrent()){
			super.setColor(damageRed, damageGreen, damageBlue, damageAlpha);		
			damageColorTimer.setLength(0.1f);
			damageColorTimer.reset();
		}

		if(damageColorTimer.hasExceeded()){
			super.setColor(tempRed, tempGreen, tempBlue, tempAlpha);		
			damageColorTimer.setLength(-1);
		}
		
		prevCurrent = getCurrent();
	}



	public void render(){
		draw(getMax(),getCurrent());
	}
	
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
	
	public void setDamageColor(float r, float g, float b, float a){
		damageRed = r;
		damageGreen = g;
		damageBlue = b;
		damageAlpha = a;
	}
	
	public void setColor(float r, float g, float b, float a){
		super.setColor(r, g, b, a);
		
		tempRed = this.getRed();
		tempGreen = this.getGreen();
		tempBlue = this.getBlue();
		tempAlpha = this.getAlpha();
	}
	
	public void setBackgroundTexture(Texture texture){
		this.backgroundTexture = texture;
	}
	
	public void setBackgroundTexture(String fileName) {
		this.backgroundTexture = GTexture.getTexture(texFolder + fileName);
	}
	
	public Texture getBackgroundTexture() {
		return backgroundTexture;
	}
	
	public abstract float getMax();
	public abstract float getCurrent();
	}
