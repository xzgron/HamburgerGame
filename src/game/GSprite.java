package game;
import static java.lang.Math.*;
import static org.lwjgl.input.Mouse.isButtonDown;
import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import controllers.GController;


public class GSprite {

	protected float xPos, yPos;
	
	protected Texture texture = null;
	protected float texWidth, texHeight;
	
	protected String texFolder = "";
	
	protected float red = 1, green = 1, blue = 1, transparency = 1;
	


	public GSprite(float xPos, float yPos, float texWidth, float texHeight) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.texWidth = texWidth;
		this.texHeight = texHeight;
	}
	
	public GSprite(float xPos, float yPos, float texWidth, float texHeight, String texture) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.texWidth = texWidth;
		this.texHeight = texHeight;
		setTexture(texture);
	}

	public GSprite(float xPos, float yPos, float texWidth, float texHeight, float r, float g, float b, float t) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.texWidth = texWidth;
		this.texHeight = texHeight;
		red = r;
		green = g;
		blue = b;
		transparency = t;
	}

	public void render() {
		GImage.draw(texture, xPos, yPos, texWidth, texHeight, red, green, blue, transparency);	
	}
	
	public void setSize(float w, float h){
		texWidth = w;
		texHeight = h;
	}
	public void setPosition(float x, float y) {
		this.xPos = x;
		this.yPos = y;
	}
	
	public void move(float x, float y) {
		this.xPos += x;
		this.yPos += y;
	}
	
	public void moveByAngle(float amt, float angle){
		float dx = (float) (amt * cos(toRadians(angle)));
		float dy = (float) (amt * sin(toRadians(angle)));
		move(dx,dy);
	}

	public float getX() {
		return xPos;
	}

	public float getY() {
		return yPos;
	}
	
	public void setTexture(Texture texture){
		this.texture = texture;
	}

	public void setTexture(String fileName) {
		texture = GTexture.getTexture(texFolder + fileName);
	}
	
	public Texture getTexture() {
		return texture;
	}
	
	public void setTexFolder(String texFolder){
		this.texFolder = texFolder;
	}
	
	public String getTexFolder(){
		return texFolder;
	}
	
	public float getTexWidth(){
		return texWidth;
	}
	
	public float getTexHeight(){
	 return texHeight;
	}


	public void setColor(float r, float g, float b, float t) {
		this.red = r / 255;
		this.green = g / 255;
		this.blue = b / 255;
		this.transparency = t;
	}
	public void setTransparency(float f){
		transparency = f;
	}
	
	public float getRed(){
		return red;
	}
	
	public float getGreen(){
		return green;
	}
	
	public float getBlue(){
		return blue;
	}
	
	public float getTransparency(){
		return transparency;
	}
	
	public boolean isCursorWithin(){
		int mx = Mouse.getX();
		//Detta kan beh�va justeras.
		int my = Display.getHeight()-Mouse.getY();
		if(mx >= getX() - getTexWidth()/2 && mx <= getX()+getTexWidth()/2 && my >= getY() - getTexHeight()/2 && my <= getY()+getTexHeight()/2)
			return true;
		else 
			return false;
	}
}
