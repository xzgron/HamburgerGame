package game;


import input.DefaultController;

import java.util.ArrayList;

import worldObjects.*;
import worldObjects.food.*;

public class GWorld {
	
	
	public ArrayList<GWorldObject> worldObjects = new ArrayList<GWorldObject>();
	
	public static float gravity = 30;
	
	public static Hamburger player  = new Hamburger(400,400,100); ;
	
	public GWorld() {
		addGO(player);
		player.setController(new DefaultController(player));
		addGO(new Tree(150,30,500));		
		for(int i = 0; i < 1;i++)
			addGO(new BlueBerry((float)Math.random()*1000-150,(float)Math.random()*1000-150));
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
		handleCollision();
	}

	public void render() {
		sortObjects();
		for(GWorldObject go : worldObjects)
			go.render();
	}
	
	
	private void handleCollision(){
		for(int j = 0; j < worldObjects.size()-1; j++){
			for(int i = j; i < worldObjects.size()-1; i++){
				GPhysics.handleCollision(worldObjects.get(i),worldObjects.get(i+1));
	
			}
		}
	}
	
	private void sortObjects(){
		boolean moved = true;
		GWorldObject temp;
		
		while(moved){
			moved = false;
			for(int i = 0; i < worldObjects.size()-1; i++){
				if(worldObjects.get(i).getGroundPos()<worldObjects.get(i+1).getGroundPos()){
					temp = worldObjects.get(i);
					worldObjects.set(i, worldObjects.get(i+1));
					worldObjects.set(i+1, temp);
					moved = true;
				}			
			}
		}
		for(GWorldObject go: worldObjects){
			if(go != player && go.getGroundPos()< player.getGroundPos() && GPhysics.isPosWithinTex(player.getX(), player.getY()+ player.getZ(), go)){
				go.setTransparency(0.5f);
				}
			else
				go.setTransparency(1);
		}
	}
}
