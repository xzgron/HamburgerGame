package game.parts;

import static game.input.GKeyboard.isKeyPressed;

import org.lwjgl.opengl.Display;

import controllers.Controlls;
import game.GamePart;
import game.Main;
import game.HUDMaterial.Inventory;

public class InventoryMenu extends GamePart {
	Inventory playerInventory = new Inventory(Display.getWidth()/2,Display.getHeight()/2, 6,4);
	@Override
	public void handleInput() {
		if (isKeyPressed(Controlls.INVENTORY_KEY))
			Main.game.revertGameState();
	}

	public void update() {
		playerInventory.update();

	}

	public void render() {
		playerInventory.render();

	}

}
