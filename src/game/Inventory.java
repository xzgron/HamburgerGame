package game;

import gameObjects.GItem;

import java.util.ArrayList;

public class Inventory {
	GItem[] slots;
	
	public Inventory(int size){
		slots = new GItem[size];
	}
	
	public void render(){
		
	}
	
	public void addItem(GItem go){
		for(int i = 0; i < slots.length; i++){
				if(slots[i] == null){
					slots.;
			}	
		}
	}
	
	public void addItem(GItem go, int i){
		//fixa s� att om det finns item d�r ska den 
		slots[i] = go;
	}

}
