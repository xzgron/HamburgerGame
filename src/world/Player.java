package world;

import org.lwjgl.opengl.Display;

import game.HUDMaterial.Inventory;
import world.objects.food.Hamburger;

public class Player extends Hamburger{

	private Inventory inventory = new Inventory(Display.getWidth()/2-80,Display.getHeight()/2+80, 6,3);
	
	public Player(float xPos, float yPos) {
		super(xPos, yPos);
	}
	
	public Inventory getInventory(){
		return inventory;
	}
	
}
