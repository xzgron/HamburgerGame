package game;


import input.DefaultController;

import java.util.ArrayList;

import worldObjects.*;
import worldObjects.food.Hamburger;

public class GWorld {
	
	
	public ArrayList<GWorldObject> worldObjects = new ArrayList<GWorldObject>();
	
	public static Hamburger player  = new Hamburger(400,400,100); ;
	
	public GWorld() {
		addGO(player);
		player.setController(new DefaultController(player));
		addGO(new Tree(150,30,200,200));
		addGO(new Tree(600,70,200,200));
		addGO(new Tree(200,550,200,200));
		addGO(new Tree(75,400,200,200));
		
	}
	
	public void addGO(GWorldObject GO){
		worldObjects.add(GO);
	}
	
	public void removeGO(GWorldObject GO){
		worldObjects.remove(GO);
	}
	
	public void update() {
		for(GWorldObject go : worldObjects)
			go.update();
	}

	public void render() {
		sortObjects();
		for(GWorldObject go : worldObjects)
			go.render();
	}
	
	private void sortObjects(){
		boolean moved = true;
		GWorldObject temp;
		
		while(moved){
			moved = false;
			for(int i = 0; i < worldObjects.size()-1; i++){
				if(worldObjects.get(i).getFootPos()<worldObjects.get(i+1).getFootPos()){
					temp = worldObjects.get(i);
					worldObjects.set(i, worldObjects.get(i+1));
					worldObjects.set(i+1, temp);
					moved = true;
				}			
			}
		}
	}
}
