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
		//fixa så att om det finns item där ska den 
		slots[i] = go;
	}

}
