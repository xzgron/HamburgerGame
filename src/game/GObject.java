package game;
import static java.lang.Math.*;
import static org.lwjgl.opengl.GL11.*;
import input.GController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;


public abstract class GObject {

	private float xPos, yPos;
	private float xPrev, yPrev;
	
	private Texture texture;
	private float xTex = 0, yTex = 0;
	private float texWidth, texHeight;
	
	private float radie = -1;
	
	private float red = 1, green = 1, blue = 1, transparency = 1;
	
	private GController controller;
	
	private String texFolder = "";

	public GObject(float xPos, float yPos, float texWidth, float texHeight) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.texWidth = texWidth;
		this.texHeight = texHeight;
	}

	public abstract void update();

	public void render() {
		GSprite.draw(texture, xPos + xTex, yPos + yTex, texWidth, texHeight, red, green, blue, transparency);	
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
		float dx = (float) (amt * sin(toRadians(angle)));
		float dy = (float) (amt * cos(toRadians(angle)));
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
	
	public void setTexPos(float x, float y){
		xTex = x;
		yTex = y;
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


	

	public void setRadie(float f){
		radie = f;
	}
	
	public float getRadie(){
		return radie;
	}
	/*
	public void giveController(GController controller){
		controller.addReciever(this);	
	}
	
	public void removeController()*/
}
