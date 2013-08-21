package gameObjects;

import game.GObject;
import game.Game;
import game.GPhysics;


public class Dragon extends GObject {
	
	
	public Dragon(float xPos, float yPos, float textureSize,float hitBoxSize) {
		super(xPos, yPos, textureSize);
		setRadie(hitBoxSize/2);
		setTexture("nature/Dragon");

	}

	private float growingRate;

	public void update() {
		
	}

}
