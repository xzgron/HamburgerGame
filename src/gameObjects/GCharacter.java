package gameObjects;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import game.GObject;
import game.GPhysics;
import game.GTexture;
import game.Game;
import game.Main;

import input.Controller;

public abstract class GCharacter extends GObject {

	private String characterFolder;
	float speed;
	boolean walking = false;
	int textureMode;
	int rotationState;
	
	Texture[] up = new Texture[3];
	Texture[] rightUp = new Texture[3];
	Texture[] right = new Texture[3];
	Texture[] rightDown = new Texture[3];
	Texture[] down = new Texture[3];
	Texture[] leftDown = new Texture[3];
	Texture[] left = new Texture[3];
	Texture[] leftUp = new Texture[3];

	public GCharacter(float xPos, float yPos, String characterFolder,
			float textureSize, float hitBoxSize) {
		super(xPos, yPos, textureSize);
		this.characterFolder = characterFolder;
		setRadie(hitBoxSize / 2);
		
		
		for (int i = 0; i < 3; i++) {
			up[i] = getTexture("up"+i);
			rightUp[i] = getTexture("rightUp"+i);
			right[i] = getTexture("right"+i);
			rightDown[i] = getTexture("rightDown"+i);
			down[i] = getTexture("down"+i);
			leftDown[i] = getTexture("leftDown"+i);
			left[i] = getTexture("left"+i);
			leftUp[i] = getTexture("leftUp"+i);
		}
		
		setTexture(down[0]);
	}

	public void changeTexture(int state, int mode) {

		switch (state) {
		case 0:
			setTexture(up[mode]);
			break;
		case 1:
			setTexture(rightUp[mode]);
			break;
		case 2:
			setTexture(right[mode]);
			break;
		case 3:
			setTexture(rightDown[mode]);
			break;
		case 4:
			setTexture(down[mode]);
			break;
		case 5:
			setTexture(leftDown[mode]);
			break;
		case 6:
			setTexture(left[mode]);
			break;
		case 7:
			setTexture(leftUp[mode]);
			break;
		}
	}

	public Texture getTexture(String fileName) {
		return GTexture.getTexture("characters/" + characterFolder + "/"
				+ fileName);
	}

	float walkCounter = 0;
	int textureCounter = 0;
	public void update() {
		if (walking) {
			walkCounter += Game.deltaTime;
			
			if (walkCounter > 15/speed) {
				textureCounter++;
				textureMode = textureCounter%4;
				switch(textureMode){
				case 0:
					textureMode = 0;
					break;
				case 1:
					textureMode = 1;
					break;
				case 2:
					textureMode = 0;
					break;
				case 3:
					textureMode = 2;
					break;
				}
				walkCounter = 0;
			}
		} 
		else{
			walkCounter = 1;
			textureMode = 0;
		}
		 changeTexture(rotationState, textureMode);
		 
		 
		 for(GObject go: Main.game.world.worldObjects){
			 if(go != this)
				 GPhysics.checkCollision(this,go);
		 }
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getSpeed() {
		return speed;
	}

	public void handleCollision() {

	}

	public void setRotationState(int rotationState) {
		this.rotationState = rotationState;
	}
	
	public void setWalking(boolean b){
		walking = b;
	}

}
