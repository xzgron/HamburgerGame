package gameObjects;

import game.GObject;
import game.Game;
import game.GPhysics;

public class Tree extends GObject {
	
	private float hitBoxRadie;
	
	public Tree(float xPos, float yPos, float textureSize,float hitBoxSize) {
		super(xPos, yPos, textureSize);
		this.hitBoxRadie = hitBoxSize/2;
		setTexture("nature/tree");

	}

	private float growingRate;

	public void update() {
		
	}

}
