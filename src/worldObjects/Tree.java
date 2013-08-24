package worldObjects;

import game.GObject;
import game.GWorldObject;
import game.Game;
import game.GPhysics;


public class Tree extends GWorldObject {
	
	public Tree(float xPos, float yPos, float texSize,float hitBoxSize) {
		super(xPos, yPos, texSize,texSize);
		setRadie(hitBoxSize / 2);
		setTexture("nature/tree");
		setFootPos(0.2f);
		
	}



	public void update() {
		
	}

}
