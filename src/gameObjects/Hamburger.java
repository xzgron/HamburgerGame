package gameObjects;

import java.util.ArrayList;

import game.GObject;

public class Hamburger extends GFood {

	ArrayList<GObject> parts = new ArrayList<GObject>();
	
	public Hamburger(float xPos, float yPos, float texSize) {
		super(xPos, yPos, texSize, texSize);
	}

	public void render() {
		
		for(int i = 0; i < parts.size(); i++){}
	}
	
	public void update() {
		
	}
	
	

}
