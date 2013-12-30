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

import controllers.Controlls;

public class Inventory{
	
	InventorySlot[][] slots;

	public Inventory(float xPos, float yPos, int width, int height){
		float slotSize = 70;
		float itemSize = 60;
		
		slots =	new InventorySlot[width][height];
		for(int x = 0; x < slots.length; x++)
			for(int y = 0; y < slots[0].length; y++){
				slots[x][y] = new InventorySlot(xPos-slotSize*width/2+x*slotSize+slotSize/2, yPos-slotSize*height/2+y*slotSize+slotSize/2, slotSize, itemSize, "buttons/bars");
				slots[x][y].setColor(0.2f,0.2f,0.7f,0.9f);
			}
	}
	

 	public void update() {
		for(int x = 0; x < slots.length; x++)
			for(int y = 0; y < slots[0].length; y++)
				slots[x][y].update();
 	}
 
	public void render() {	
		for(int x = 0; x < slots.length; x++)
			for(int y = 0; y < slots[0].length; y++)
				slots[x][y].render();
		
		
 	}

/*	public void addItem(GItem go){
	
	public void addItem(GItem go){
 		for(int i = 0; i < slots.length; i){
 				if(slots[i] == null){
 					slots.;
 			}	
 		}
 	}

	public void addItem(GItem go, int i) {
(null)
	
	public void addItem(GItem go, int i){
(null)
 		slots[i] = go;
	}*/
	}

 
 