package game.parts;

import static game.tools.GKeyboard.isKeyPressed;

import java.awt.Font;

import options.Controlls;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.TrueTypeFont;

import world.objects.ingredients.*;

import game.GamePart;
import game.Main;
import game.HUDMaterial.EraseSlot;
import game.HUDMaterial.HamburgerEquipmentbar;
import game.HUDMaterial.Inventory;
import game.HUDMaterial.InventorySlot;

public class InventoryMenu extends GamePart {
	Inventory inventory = Main.game.world.getPlayer().getInventory();
	HamburgerEquipmentbar equipment = new HamburgerEquipmentbar(inventory.getRightBorderX()+40, inventory.getBottomBorderY()-30,Main.game.world.getPlayer());
	EraseSlot eraser = new EraseSlot(inventory.getRightBorderX()-30, inventory.getTopBorderY()-30, 60, 50, "buttons/bars");
	
	public InventoryMenu(){		
		eraser.setColor(0.7f,0.2f,0.2f,0.9f);
		inventory.add(new Cheese(0,0));
		inventory.add(new Sallad(0,0));
		inventory.add(new Beef90Gram(0,0));
		inventory.add(new Banana(0,0));
		inventory.add(new BreadUnderPartSuperSize(0,0));
	}
	
	public void handleInput() {
		if (isKeyPressed(Controlls.INVENTORY_KEY)||isKeyPressed(Keyboard.KEY_ESCAPE))
			Main.game.revertGameState();
		
		inventory.handleInput();
		equipment.handleInput();
		eraser.handleInput();
	}

	public void update() {
		inventory.update();
		equipment.update();
		eraser.update();
	}

	public void render() {
		inventory.render();
		equipment.render();
		eraser.render();
		
		InventorySlot.renderGrabbedContent();

	}

}
