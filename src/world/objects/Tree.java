package world.objects;

import world.WorldObject;
import game.GSprite;
import game.Game;
import game.GPhysics;


public class Tree extends WorldObject {
	
	public Tree(float xPos, float yPos, float texSize) {
		super(xPos, yPos, texSize, 0.15f, 0.85f);
		setRadie(texSize / 20);
		setTexture("nature/tree");
		
	}



	public void update() {
		
	}

}
