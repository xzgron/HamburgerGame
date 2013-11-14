package game.parts;

import static game.Game.setGameState;
import static org.lwjgl.input.Keyboard.*;

import game.GButton;
import game.GSprite;
import game.Game;
import game.GamePart;
import game.Main;
import game.Game.GStates;


import java.util.ArrayList;

import org.lwjgl.opengl.Display;

public class Inventory implements GamePart {
	//GItem[] slots;

	GSprite background = new GSprite(Display.getWidth() / 2,
			Display.getHeight() / 2, 600, 480, "buttons/inventory");

	

	public void InventoryMenu() {
		
	}

	public static boolean wasIDown;
	public void handleInput() {
		if(isKeyDown(KEY_I) && !wasIDown){
			setGameState(GStates.GAME);
			wasIDown=true;
			}
		else if(!isKeyDown(KEY_I))
			wasIDown = false;
		
	}

	public void Inventory(int size) {
	//	slots = new GItem[size];

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	public void render() {
		background.render();
	}

/*	public void addItem(GItem go){
		for(int i = 0; i < slots.length; i++){
				if(slots[i] == null){
					slots.;
			}	
		}
	}

	public void addItem(GItem go, int i) {
		// fixa så att om det finns item där ska den
		slots[i] = go;
	}*/

}
