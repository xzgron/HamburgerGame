package game;

import items.GItem;

import java.util.ArrayList;

public class Inventory implements GamePart{
	GItem[] slots;
	
	public Inventory(int size){
		slots = new GItem[size];
	}
	
	@Override
	public void handleInput() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
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
