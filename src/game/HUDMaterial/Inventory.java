package game.HUDMaterial;

import static org.lwjgl.input.Keyboard.*;

import game.GButton;
import game.GSprite;
import game.Game;
import game.GamePart;
import game.Main;
import game.Game.GState;
import static game.input.GKeyboard.*;

import java.util.ArrayList;

import org.lwjgl.opengl.Display;

import world.objects.GItem;

import controllers.Controlls;

public class Inventory {

	InventorySlot[][] slots;
	float slotSize = 60;
	float itemSize = 50;
	float yPos;
	float xPos;

	public Inventory(float xPos, float yPos, int width, int height) {
		this.xPos = xPos;
		this.yPos = yPos;
		slots = new InventorySlot[width][height];
		for (int x = 0; x < slots.length; x++)
			for (int y = 0; y < slots[0].length; y++) {
				slots[x][y] = new InventorySlot(xPos - slotSize * width / 2 + x
						* slotSize + slotSize / 2, yPos - slotSize * height / 2
						+ y * slotSize + slotSize / 2, slotSize, itemSize,
						"buttons/bars");
				slots[x][y].setColor(0.2f, 0.2f, 0.7f, 0.9f);
			}
	}
	public void handleInput(){
		for (int x = 0; x < slots.length; x++)
			for (int y = 0; y < slots[0].length; y++)
				slots[x][y].handleInput();
	}
	
	public void update() {
		for (int x = 0; x < slots.length; x++)
			for (int y = 0; y < slots[0].length; y++)
				slots[x][y].update();
	}

	public void render() {
		for (int x = 0; x < slots.length; x++)
			for (int y = 0; y < slots[0].length; y++)
				slots[x][y].render();

	}

	public boolean add(GItem i) {
		for (int y = 0; y < slots[0].length; y++)
			for (int x = 0; x < slots.length; x++)
				if (slots[x][y].getItem() == null){
					slots[x][y].setItem(i);
					return true;
					}
		System.out.println("Inventory is full");
		return false;
	
	}
	
	public void restartButtons(){
		for (int y = 0; y < slots[0].length; y++)
			for (int x = 0; x < slots.length; x++)
				slots[x][y].restart(0);
	}
	public boolean loot(GItem i) { //returnerar om det gick eller inte
		if(add(i)){
			Main.game.world.deSpawn(i);
			return true;
		}
		else return false;
			
	}
	public void setPosition(float xPos, float yPos){
		this.xPos = xPos;
		this.yPos = yPos;
		for (int x = 0; x < slots.length; x++)
			for (int y = 0; y < slots[0].length; y++) {
				slots[x][y].setPosition(xPos - slotSize * slots.length / 2 + x
						* slotSize + slotSize / 2, yPos - slotSize * slots[0].length / 2
						+ y * slotSize + slotSize / 2);
			}
	
	}
	public float getTopBorderY(){
		return yPos - slots[0].length/2f * slotSize;
	}
	public float getBottomBorderY(){
		return yPos + slots[0].length/2f * slotSize;
	}
	public float getRightBorderX(){
		return xPos + slots.length/2f * slotSize  ;
	}
	public float getLeftBorderX(){
		return xPos - slots.length/2f * slotSize  ;
	}
}
