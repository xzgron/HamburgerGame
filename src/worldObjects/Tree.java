package worldObjects;

import game.GObject;
import game.GWorldObject;
import game.Game;
import game.GPhysics;


public class Tree extends GWorldObject {
	
	public Tree(float xPos, float yPos, float texSize) {
		super(xPos, yPos, texSize, texSize * 3, 0.15f, 0.85f);
		setRadie(texSize / 20);
		setTexture("nature/tree");
		
	}



	public void update() {
		
	}

}
