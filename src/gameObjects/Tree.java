package gameObjects;

import game.GObject;
import game.Game;
import game.GPhysics;


public class Tree extends GObject {
	
	private float hitBoxRadie;
	private float growingRate;
	
	public Tree(float xPos, float yPos, float textureSize,float hitBoxSize) {
		super(xPos, yPos, textureSize);
		setRadie(hitBoxSize / 2);
		setTexture("nature/tree");

	}



	public void update() {
		
	}

}
