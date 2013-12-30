package game.parts;

import static game.input.GKeyboard.isKeyPressed;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import world.objects.ingredients.*;

import controllers.Controlls;
import game.GamePart;
import game.Main;
import game.HUDMaterial.Inventory;

public class InventoryMenu extends GamePart {
	Inventory playerInventory = new Inventory(Display.getWidth()/2,Display.getHeight()/2, 6,4);
	
	public InventoryMenu(){
		playerInventory.add(new Cheese(0,0));
		playerInventory.add(new Sallad(0,0));
		playerInventory.add(new Beef90Gram(0,0));
	}
	
	public void handleInput() {
		if (isKeyPressed(Controlls.INVENTORY_KEY)||isKeyPressed(Keyboard.KEY_ESCAPE))
			Main.game.revertGameState();
	}

	public void update() {
		playerInventory.update();

	}

	public void render() {
		playerInventory.render();

	}

}
