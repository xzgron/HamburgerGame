package game;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;
import gameObjects.*;
import input.Input;

import java.util.ArrayList;

public class GWorld {
	
	
	public ArrayList<GObject> worldObjects = new ArrayList<GObject>();
	
	public static Human player  = new Human(400,400); ;
	
	public GWorld() {
		addGO(player);
		
		addGO(new Tree(150,30,200,200));
		addGO(new Tree(600,70,200,200));
		addGO(new Tree(200,550,200,200));
		addGO(new Tree(75,400,200,200));
		//addGO(new Rektangel(200));
		
	}
	
	public void addGO(GObject GO){
		worldObjects.add(GO);
	}
	
	public void removeGO(GObject GO){
		worldObjects.remove(GO);
	}
	
	public void update() {
		for(GObject go : worldObjects)
			go.update();
	}

	public void render() {
		for(GObject go : worldObjects)
			go.render();
	}
	

}
