package gameObjects;

import game.GObject;
import game.Game;
import game.GPhysics;


public class Dragon extends GObject {
	
	
	public Dragon(float xPos, float yPos, float texSize,float hitBoxSize) {
		super(xPos, yPos, texSize, texSize);
		setRadie(hitBoxSize/2);
		setTexture("nature/Dragon");

	}

	private float growingRate;

	public void update() {
		
	}

}
