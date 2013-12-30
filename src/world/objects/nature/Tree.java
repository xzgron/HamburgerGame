package world.objects.nature;

import world.WorldObject;
import game.GSprite;
import game.Game;
import game.GPhysics;


public class Tree extends WorldObject {
	
	public Tree(float xPos, float yPos, float texWidth, float texHeight) {
		super(xPos, yPos, texWidth, texHeight, 0.12f, 0.85f);
		setRadius(texWidth / 17);
		setTexture("nature/tree");
	}
}
