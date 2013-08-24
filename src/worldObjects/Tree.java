package worldObjects;

import game.GObject;
import game.GWorldObject;
import game.Game;
import game.GPhysics;


public class Tree extends GWorldObject {
	
	public Tree(float xPos, float yPos, float texSize) {
		super(xPos, yPos, texSize,texSize);
		setRadie(texSize / 2);
		setTexture("nature/tree");
		setFootPos(0.15f);
		
	}



	public void update() {
		
	}

}
